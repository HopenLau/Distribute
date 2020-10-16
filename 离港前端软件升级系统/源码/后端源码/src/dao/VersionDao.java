package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Version;
/**
 * 这个是dao层的接口类
 * 接口里面的方法分别对应着 增 ，删 ，改 ，查的信息
 * 与该接口的xml文件相对应
 * @author LiJingHai , October. 1, 2020
 *
 */
public interface VersionDao {
	/**
	 * 增加一条信息
	 * @param version :代表版本信息这个类
	 */
	void addVersion(Version version);
	
	/**
	 * 删除一条信息
	 * @param id ：代表版本信息的序号
	 */
	void deleteVersion(@Param("id") Long id);
	
	/**
	 * 修改一条数据
	 * @param version : 代表版本信息这个类作为参数
	 */
	void updateVersion(Version version);
	
	/**
	 * 查询所有
	 * @return : 版本信息里面所有的信息
	 */
	List<Version> queryAllVersion();
	
	/**
	 * 根据id查询指定信息， 安时间顺序倒序排列 
	 * @param id : 代表指定的某个版本的序号
	 * @return ： 返回指定版本的一条信息
	 */
	Version getVersionById(Long id);
	
	/**
	 * 获取最新版本
	 * @return : 获取最近添加的版本信息
	 */
	Version getLastVersion();
}
