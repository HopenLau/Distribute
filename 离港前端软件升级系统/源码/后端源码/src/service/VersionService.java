package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.VersionDao;
import dao.VersionFileDao;
import po.Version;
/**
 * 这是版本信息表的服务层，负责对表的业务模块进行设计
 * 里面主要定义的增 ，删 ，查 ， 改 的设计
 * @author LiJingHai , October. 1, 2020
 *
 */
@Service
public class VersionService {
	
	/**
	 * service层调用dao层数据
	 */
	@Autowired
	private VersionDao versionDao;
	//（version)主表 与 (versionFile)子表之间的关联
	@Autowired
	private VersionFileDao versionFileDao;
	
	/**
	 *  增加
	 * @param version ：增加一条version信息
	 */
	@Transactional
	public void addVersion(Version version) {
		versionDao.addVersion(version);
	}
	
	/**
	 * 删除
	 * @param id : 根据id删除一条信息
	 */
	public void deleteVersion(Long id) {
		 
		versionDao.deleteVersion(id);
	}
	
	/**
	 * 修改
	 * @param version : 修改指定版本的信息
	 */
	public void updateVersion(Version version) {
		versionDao.updateVersion(version);
	}
	
	/**
	 * 查询所有
	 * @return : 显示表内数据库里的所有信息
	 */
	public List<Version> queryAllVersion() {
		return versionDao.queryAllVersion();
		
	}
}
