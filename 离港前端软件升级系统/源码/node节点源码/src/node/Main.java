package node;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Properties;

import abu.callback.Call;
import abu.callback.CallBack;
import abu.log.AbuLogger;
import abu.mqtt.Mqtt;
import abu.pool.net.http.UrlConnection;
import abu.systemutil.FileCompress;
import abu.systemutil.SystemUtil;

/**
 * 将该程序打成jar包在每个柜台的服务中运行，
 * 并实现，将配置文件下发到各个柜台上
 *  @author LiJingHai , October. 1, 2020
 *
 */
public class Main
{
    public static void main(String[] args) throws Exception
    {
        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = java.net.URLDecoder.decode(path,"utf-8"); 
        int pos = path.lastIndexOf(".jar");
        if (pos >= 0)
        {
            pos = path.lastIndexOf("/");
            path = path.substring(0, pos);
        }
        
        Properties prop = new Properties();
        prop.load(new FileReader(path + "/global.properties"));
        final String myDevNo = prop.getProperty("devNo");
        final String versionUrl = prop.getProperty("versionUrl");
        final String appLocation = prop.getProperty("appLocation");
        prop.clear();
        System.out.println("devNo=" + myDevNo);
        System.out.println("versionUrl=" + versionUrl);
        System.out.println("appLocation=" + appLocation);
        Mqtt client =  new Mqtt(
            "tcp://120.25.25.117:1883",
            "DEV" + myDevNo,
            "admin",
            "admin")
        {
            {
                // 定义订阅回调
                c = new CallBack()
                {
                    @Override
                    public Object call(Object... o)
                    {
                    	//topic == deploy/#{ }请求
                        String topic = (String)o[0];
//                        int qos = (Integer)o[1];
                        //修改配置文件
                        byte[] payload = (byte[])o[2];
                        String payloadStr = SystemUtil.bin2String(payload);
                        // 解析主题
                        String[] segs = topic.split("/");
                        if (segs == null || segs.length == 0)
                        {
                            AbuLogger.log.error("无效消息，直接丢弃");
                            return null;
                        }
                        callMap.get(segs[0]).call(segs[1], payloadStr);
                        return null;
                    }
                };
                // 定义丢链回调
                e = new CallBack()
                {
                    @Override
                    public Boolean call(Object... o)
                    {
                        Throwable t = (Throwable)o[0];
                        AbuLogger.log.error(null, t);
                        try
                        {
                            Thread.sleep(300);
                        }
                        catch (Exception e) {}
                        // 返回true表示重新连接
                        return true;
                    }
                };
                // 定义发布回调
                d = new CallBack()
                {
                    @Override
                    public Object call(Object... o)
                    {
                        return null;
                    }
                };
            }
            
            private HashMap<String, Call> callMap = new HashMap<String, Call>();
            {
                // 部署
                callMap.put(
                    "deploy",
                    (Object... o) -> {
                        String version = (String)o[0];
                        // 找服务器要版本号
                        String urlStr = versionUrl + version + ".zip";
                        String filePath = appLocation + version + ".zip";
                        try
                        {
                            // 下载版本
                            UrlConnection.download(urlStr, filePath);
                            // 解压缩
                            FileCompress.unzip(filePath, appLocation);
                            File f = new File(filePath);
                            f.delete();
                        }
                        catch (Exception e)
                        {
                            AbuLogger.log.error(null, e);
                        }
                    }
                );
                // 修改配置
                callMap.put(
                    "config",
                    (Object... o) -> {
                        // devNo就是myDevNo，不需要获取
//                        String devNo = (String)o[0];
                        // 得到配置内容
                        String content = (String)o[1];
                        // 把负载看成是配置内容，复写到本地的配置文件中
                        try (FileWriter fw = new FileWriter(appLocation + File.separator + "config.ini"))
                        {
                            fw.write(content);
                        }
                        catch (Exception e)
                        {
                            AbuLogger.log.error(null, e);
                        }
                    }
                );
            };
        };
        
        // 连接MQTT Broker
        new Thread(
            () -> {
                try
                {
                    client.open();
                    //订阅了所有以deploy开头的请求信息
                    client.subscribe("deploy/#", 2);
                    client.subscribe("config/" + myDevNo, 2);
                    AbuLogger.log.info("MQTT Client启动成功");
                }
                catch (Exception e)
                {
                    AbuLogger.log.error(null, e);
                }
            }
        ).start();
    }
}
