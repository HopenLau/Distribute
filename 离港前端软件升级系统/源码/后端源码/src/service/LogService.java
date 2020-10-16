package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LogDao;
import po.Log;

/**
 * 这是版本信息表的服务层，负责对表的业务模块进行设计
 * 里面主要定义的增 ，删 ，查 ， 改 的设计
 * @author LiJingHai , October. 11, 2020
 *
 */
@Service
public class LogService {
	/**
	 * service层调用dao层
	 */
	@Autowired
	private LogDao logDao;
	/**
	 * 增加
	 * @param log ：增加一条日志信息
	 */
		public void addLog(Log log) {
			logDao.addLog(log);
		}
			
		/**
		 * 删除
		 * @param id ： 通过指定id删除指定的日志信息
		 */
		public void deleteLog(Long id) {
			logDao.deleteLog(id);
		}
			
		/**
		 * 修改
		 * @param log ： 修改指定的日志信息
		 */
		public void updateLog(Log log) {
			logDao.updateLog(log);
		}
		/**
		 * 查询所有
		 * @return ：通过集合类来储存表类所有的内容
		 */
		public List<Log> queryAllLog() {
			return logDao.queryAllLog();
		}
}
