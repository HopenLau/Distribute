package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Log;
/**
 * 这个是dao层的接口类
 * 接口里面的方法分别对应着 增 ，删 ，改 ，查的信息
 * 与该接口的xml文件相对应
 * @author LiJingHai , October. 1, 2020
 *
 */
public interface LogDao {
	/**
	 * 增加一条信息
	 * @param log ： 讲log类作为参数传入，增加一条日志信息
	 */
	void addLog(Log log);
	
	/**
	 * 删除一条信息
	 * @param id ： 通过指定id删除一条信息
	 */
	void deleteLog(@Param("id") Long id);
	
	/**
	 * 修改一条数据
	 * @param log ： 修改指定的一条信息
	 */
	void updateLog(Log log);
	
	/**
	 * 查询所有，按照时间顺序进行倒序排序
	 * @return ：通过集合类来储存返回的文件内容
	 */
	List<Log> queryAllLog();
}
