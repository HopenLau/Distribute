package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


import po.VersionFile;
/**
 * 这个是dao层的接口类
 * 接口里面的方法分别对应着 增 ，删 ，改 ，查的信息
 * 与该接口的xml文件相对应
 * @author LiJingHai , October. 1, 2020
 *
 */
public interface VersionFileDao {
	/**
	 * 增加一条信息
	 * @param versionFile :增加一条versionFile信息
	 */
	void addVersionFile(VersionFile versionFile);
	
	/**
	 * 删除一条信息
	 * @param id ： 根据id删除一条信息
	 */
	void deleteVersionFile(@Param("id") Long id);
	
	/**
	 * 修改一条数据
	 * @param versionFile : 修改指定文件的信息
	 */
	void updateVersionFile(VersionFile versionFile);
	
	/**
	 * 查询所有
	 * @param map : 使用集合类来储存文件信息
	 * @return ：返回表里所有的信息
	 */
	List<VersionFile> getVersionFileDao(Map<String, Object> map);
	
	
}
