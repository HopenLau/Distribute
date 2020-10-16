package controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.irandoo.ssm.exception.NotExistException;

import abu.systemutil.FileCompress;
import service.MqttClient;
/**
 * 该类在整体功能中实现的是讲已上传的文件在Tomcate路径中的static路径下打成压缩包的形式
 * 当Tomcat每次重新运行时会将Tomcat路径中的upload文件夹下的内容清除掉，
 * @author LiJingHai , October. 1, 2020
 *
 */
@RestController
@RequestMapping("/deploy")
public class DeployController
{
	/**
	  *将指定配置文件发往前端
     * @param request : ServletRequest - HTTP请求
	 * @param versionNo ： 代表版本号
	 * @throws Exception
	 */
    @RequestMapping(value="{versionNo}", method=RequestMethod.GET)
    public void deploy(
        ServletRequest request,
        @PathVariable("versionNo") String versionNo) throws Exception
    {
        ServletContext sc = request.getServletContext();
        /**
                     * 把指定版本的文件打成压缩包放到static目录下
                     * 将upload文件夹下的所有内容包括该文件夹打成压缩包并且存于static下
         */
        String sourcePath = sc.getRealPath("/upload/" + versionNo);
        File f = new File(sourcePath);
        if (!f.exists())
        {
            throw new NotExistException("无此版本");
        }
        String targetPath = sc.getRealPath("/static");
        FileCompress.zip(sourcePath, targetPath + File.separator + versionNo + ".zip");
        /**
                      * 通知指定柜台进行版本升级
         */
        MqttClient.client.publish("deploy/" + versionNo, versionNo);
    }
}
