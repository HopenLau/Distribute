package po;
/**
 * 文件信息
 * 该类对应的是前端页面中的文件信息页面，对应的是数据库里面t_version_file这张表
 * id:是为了给t_version-file表中所有信息的进行排序，在表中是默认自动增加的
 * versionId:对应的是数据表中的version_id字段，代表版本号的意思
 * fName:对应的是数据表的f_name字段，代表版本名称的意思
 * fSize:对应的是数据表的f_size字段，是每次修改版本时对版本的描述
 * @author LiJingHai , October. 1, 2020
 *
 */

public class VersionFile {
	//序号
	private Long id;
	//文件号
	private Long versionId;
	//文件名称
	private String fName;
	//文件大小
	private Long fSize;
	
	/**
	 * gets/sets方法
	 * @return 对应的属性名称
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	public Long getfSize() {
		return fSize;
	}
	public void setfSize(Long fSize) {
		this.fSize = fSize;
	}
	@Override
	public String toString() {
		return "VersionFile [id=" + id + ", versionId=" + versionId + ", fName=" + fName + ", fSize=" + fSize + "]";
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	
}
