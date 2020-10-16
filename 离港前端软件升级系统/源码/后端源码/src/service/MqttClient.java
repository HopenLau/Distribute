package service;

import org.springframework.stereotype.Service;

import abu.callback.CallBack;
import abu.log.AbuLogger;
import abu.mqtt.Mqtt;
import abu.systemutil.SystemUtil;
/**
 * 连接远端的mqtt请求
 * 
 *  @author LiJingHai , October. 1, 2020
 *
 */
@Service
public class MqttClient
{
	//远端ip地址
    private final static String MQTT_URL = "tcp://120.25.25.117:1883";
    //登录名
    private final static String MQTT_USER = "admin";
    //登录密码
    private final static String MQTT_PWD = "admin";
    
    public static Mqtt client =  new Mqtt(
        MQTT_URL,
        SystemUtil.getUID(),
        MQTT_USER,
        MQTT_PWD)
    {
        {
            // 定义订阅回调
            c = new CallBack()
            {
                @Override
                public Object call(Object... o)
                {
//                    String topic = (String)o[0];
//                    int qos = (Integer)o[1];
//                    String payload = (String)o[2];
                    // topic由三部分组成：项目识别码/arduino/控件编码
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
                    t.printStackTrace();
//                    System.out.println("连接断开, 延时0.3s, 重新连接...");
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
//                    System.out.println("delivery completed!");
                    return null;
                }
            };
        }
    };
    
    static {
        // 连接MQTT Broker
        new Thread(
            () -> {
                try
                {
                    client.open();
//                    client.subscribe("deploy/#", 2);
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
