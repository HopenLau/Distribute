package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import po.Log;
import service.LogService;
/**
 * 这是控制层通过调用增删改查的接口，
 * 来负责对增删改查这些业务的调控
 * @author LiJingHai , October. 1, 2020
 *
 */
@RestController
@RequestMapping("/log")
public class LogController {
	/**
	 * 调用service层
	 */
	@Autowired
	private LogService logService;
	
	/**
	 * 增加
	 * @param version ：增加一条信息,POST方法
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void add(@RequestBody Log log) {
		System.out.println("增加的Log:"+log);
		logService.addLog(log);
	}
		
	/**
	 * 删除 
	 * @param id :依据id删除指定信息
	 */
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public void deleteLog(@PathVariable("id") Long id) {
		System.out.println("删除的id:"+id);
		logService.deleteLog(id);
	}
			
	/**
	 * 修改
	 * @param version ： 修改指定一条信息的信息
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void updateLog(@RequestBody Log log){
		System.out.println("修改的log:"+log);
		logService.updateLog(log);
	}
	
	/**
	 * 查询
	 * @return ：返回所有的信息
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Log> list() {
		List<Log> list = logService.queryAllLog();
		System.out.println(list);
		return list;
}
}