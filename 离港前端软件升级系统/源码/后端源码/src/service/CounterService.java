package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CounterDao;
import po.Counter;

/**
 * 这是文件信息表的服务层，负责对表的业务模块进行设计
 * 里面主要定义的增 ，删 ，查 ， 改 的设计
 * @author LiJingHai , October. 1, 2020
 *
 */
@Service
public class CounterService {
	
	/**
	 * service层调用dao层
	 */
	@Autowired
	private CounterDao counterDao;
	/**
	 * 增加
	 * @param counter ： 增加一条柜台的信息
	 */
		public void addCounter(Counter counter) {
			counterDao.addCounter(counter);
		}
			
		/**
		 * 删除
		 * @param id : 根据id删除一条信息
		 */
		public void deleteCounter(Long id) {
			counterDao.deleteCounter(id);
		}
			
		/**
		 * 修改
		 * @param version : 修改指定柜台的信息
		 */
		public void updateCounter(Counter counter) {
			counterDao.updateCounter(counter);
		}
		
		/**
		 * 查询所有
		 * @return : 显示表内数据库里的所有信息
		 */
		public List<Counter> queryAllCounter() {
			return counterDao.queryAllCounter();
		}
}
