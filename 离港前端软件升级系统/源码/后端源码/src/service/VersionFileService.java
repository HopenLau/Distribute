package service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.VersionDao;
import dao.VersionFileDao;
import po.Version;
import po.VersionFile;
/**
 * 这是文件信息表的服务层，负责对表的业务模块进行设计
 * 里面主要定义的增 ，删 ，查 ， 改 的设计
 * @author LiJingHai , October. 1, 2020
 *
 */
@Service
public class VersionFileService {
	/**
	 * service层调用dao层
	 */
	@Autowired
	private VersionFileDao versionFileDao;
	@Autowired
	private VersionDao versionDao;
	/**
	 * 增加  和 实现文件上传的功能
	 * @param request  ： 继承Servlet类，向外发出请求  ServletRequest - HTTP请求
	 * @param versionFile ： 代表文件信息类，在这里作为参数传入
	 * @param uploadFiles : 存入集合类，来获取文件的全名称
	 * @throws Exception ：若指定文件夹不存在则抛出异常
	 */
	public void addVersionFile(
        ServletRequest request,
        VersionFile versionFile,
        List<MultipartFile> uploadFiles) throws Exception {
	    /**
	            * 根据versionId获得version信息
	     */
	    Long versionId = versionFile.getVersionId();
	    System.out.println("versionId=" + versionId);
	    Version ver = versionDao.getVersionById(versionId);
	    /**
	            * 保存文件
	     */
        String path = request.getServletContext().getRealPath("/upload");
        File realPath = new File(path + File.separator + ver.getNumber());
        if(!realPath.exists()){
            realPath.mkdirs();
        }
        
        for (MultipartFile uploadFile : uploadFiles)
        {
            /**
                               * 从文件中获取文件名作为字段fName的内容、获取文件大小作为字段fSize内容
             */
            String fName = uploadFile.getOriginalFilename();
            System.out.println("fName=" + fName);
            versionFile.setfName(fName);
            versionFile.setfSize(uploadFile.getSize());
            
            uploadFile.transferTo(new File(realPath + "/" + fName));
        }
        
		versionFileDao.addVersionFile(versionFile);
	}
		
	/**
	 * 删除
	 * @param id : 通过id删除指定信息
	 */
	public void deleteVersionFile(Long id) {
		versionFileDao.deleteVersionFile(id);
	}
		
	/**
	 * 修改
	 * @param versionFile : 修改指定文件信息
	 */
	public void updateVersionFile(VersionFile versionFile) {
		versionFileDao.updateVersionFile(versionFile);
	}
	/**
	 * 查询所有
	 * @param map ： 讲文件信息用集合类来储存
	 * @return ：返回表内所有的信息
	 */
	public List<VersionFile> getVersionFileDao(Map<String, Object> map) {
		return versionFileDao.getVersionFileDao(map);
	}
	
}
