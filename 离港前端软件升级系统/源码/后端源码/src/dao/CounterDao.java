package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Counter;
/**
 * 这个是dao层的接口类
 * 接口里面的方法分别对应着 增 ，删 ，改 ，查的信息
 * 与该接口的xml文件相对应
 * @author LiJingHai , October. 1, 2020
 *
 */
public interface CounterDao {
	/**
	 * 增加一条信息
	 * @param counter ： 代表将柜台这个类作为参数传入
	 */
	void addCounter(Counter counter);
	
	/**
	 * 删除一条信息
	 * @param id ：代表柜台的序号
	 */
	void deleteCounter(@Param("id") Long id);
	
	/**
	 * 修改一条数据
	 * @param version : 代表柜台这个类作为参数
	 */
	void updateCounter(Counter counter);
	
	/**
	 * 查询所有
	 * @return : 柜台里面所有的信息
	 */
	List<Counter> queryAllCounter();
}
