package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import po.VersionFile;
import service.VersionFileService;
/**
 * 这是控制层通过调用增删改查的接口，
 * 来负责对增删改查这些业务的调控
 * @author LiJingHai , October. 1, 2020
 *
 */
@RestController
@RequestMapping("/versionFile")
public class VersionFileController {
	/**
	 * 调用service层
	 */
	@Autowired
	private VersionFileService versionFileService;
	
	/**
	 * 增加  和 实现文件上传的功能
	 * @param request  ： 继承Servlet类，向外发出请求
	 * @param versionFile ： 代表文件信息类，在这里作为参数传入
	 * @param uploadFiles : 存入集合类，来获取文件的全名称
	 * @throws Exception ：若指定文件夹不存在则抛出异常
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	public void add(
        ServletRequest request,
        VersionFile versionFile,
        List<MultipartFile> uploadFile) throws Exception
	{
	   versionFileService.addVersionFile(request, versionFile, uploadFile);
	}

	/**
	 * 删除
	 * @param id ：通过指定id来删除指定的文件信息
	 */
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public void deleteVersionFile(@PathVariable("id") Long id) {
		System.out.println("删除的versionId:"+id);
		versionFileService.deleteVersionFile(id);
	}
		
	/**
	 * 修改
	 * @param versionFile : 删除指定的文件信息
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void updateVersionFile(@RequestBody VersionFile versionFile){
		System.out.println("修改的versionFile:"+versionFile);
		versionFileService.updateVersionFile(versionFile);
	}
	/**
	 * 查询
	 * @param map ： 讲文件信息类存储在集合类中
	 * @return : 返回所有的文件内容
	 */
	@RequestMapping( method = RequestMethod.GET)
	public List<VersionFile> list(@RequestParam Map<String, Object> map) {
		List<VersionFile> list = versionFileService.getVersionFileDao(map);
		return list;
		}
	
	
}
