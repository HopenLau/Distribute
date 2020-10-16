package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import po.Counter;
import po.Log;
import service.CounterService;
/**
 * 这是控制层通过调用增删改查的接口，
 * 来负责对增删改查这些业务的调控
 * @author LiJingHai , October. 1, 2020
 *
 */
@RestController
@RequestMapping("/counter")
public class CounterController {
	/**
	 * 调用service层
	 */
	@Autowired
	private CounterService counterService;
	
	/**
	 * 增加
	 * @param version ：增加一条信息,POST方法
	 */
		@RequestMapping(method = RequestMethod.POST)
		public void add(@RequestBody Counter counter) {
			System.out.println("增加的Counter:"+counter);
			counterService.addCounter(counter);
		}
			
		//删除
		@RequestMapping(value="{id}", method = RequestMethod.DELETE)
		public void deleteLog(@PathVariable("id") Long id) {
			System.out.println("删除的id:"+id);
			counterService.deleteCounter(id);
		}
				
		/**
		 * 删除 
		 * @param id :依据id删除指定信息
		 */
		@RequestMapping(method = RequestMethod.PUT)
		public void updateLog(@RequestBody Counter counter){
			System.out.println("修改的counter:"+counter);
			counterService.updateCounter(counter);
		}
		
		/**
		 * 查询
		 * @return ：返回所有的信息
		 */
		@RequestMapping(method = RequestMethod.GET)
		public List<Counter> list() {
			List<Counter> list = counterService.queryAllCounter();
			System.out.println(list);
			return list;
	}
}
