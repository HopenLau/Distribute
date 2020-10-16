package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import po.Version;
import service.VersionFileService;
import service.VersionService;
/**
 * 这是控制层通过调用增删改查的接口，
 * 来负责对增删改查这些业务的调控
 * @author LiJingHai , October. 1, 2020
 *
 */
@RestController
@RequestMapping("/version")
public class VersionController {
	/**
	 * 调用service层
	 */
	@Autowired
	private VersionService versionService;

	/**
	 * 增加
	 * @param version ：增加一条信息,POST方法
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void add(@RequestBody Version version) {
		System.out.println("增加的version:"+version);
		versionService.addVersion(version);
	}
	
	/**
	 * 删除 
	 * @param id :依据id删除指定信息
	 */
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		System.out.println("删除的id:"+id);
		versionService.deleteVersion(id);
	}
	
	/**
	 * 修改
	 * @param version ： 修改指定一条信息的信息
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Version version){
		System.out.println("修改的version:"+version);
		versionService.updateVersion(version);
	}
	
	/**
	 * 查询
	 * @return ：返回所有的信息
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Version> list() {
		List<Version> list = versionService.queryAllVersion();
		
		System.out.println(list);
		return list;
	}
}