package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irandoo.ssm.exception.NotExistException;

import abu.log.AbuLogger;
import dao.VersionDao;
import po.Version;

/**
 * 在文件下发的功能中，首先通过最近的版本号查询指定文件下有没有已上传的文件的压缩包，
 * 若有，则可以进行版本下发，
 * 若没有则下发失败，控制台打印输出无此版本,
 * 并且在读取已上传文件的同时获取文件的大小
 * @author LiJingHai , October. 1, 2020
 *
 */
@Service
public class ConfigService{
	
	 /**
		 * service层调用dao层
		 */
		@Autowired
		private VersionDao versionDao;
		
	/**
	 * 
	 * @param request : 继承Servlet类，向外发出请求 ServletRequest - HTTP请求
	 * @return
	 * @throws Exception
	 */
    public String getConfig(ServletRequest request) throws Exception{
        /**
                      * 得到最新的版本号
         */
        Version v = versionDao.getLastVersion();
        System.out.println("version=" + v.getNumber());
        String versionNo = v.getNumber();
        
        ServletContext sc = request.getServletContext();
        /**
                     * 把指定版本的文件打成压缩包放到static目录下
         */
        String sourcePath = sc.getRealPath("/upload/" + versionNo);
        File f = new File(sourcePath);
        if (!f.exists()){
            throw new NotExistException("无此版本");
        }
        /**
                     * 读取配置文件
         */
        String line = null;
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
            new FileReader(sourcePath + File.separator + "config.ini")))
        {
            while ((line = br.readLine()) != null)
            {
                content.append(line).append('\n');
            }
        }
        catch (Exception e)
        {
            AbuLogger.log.error(null, e);
            throw e;
        }
        return content.toString();
    }
    
}
