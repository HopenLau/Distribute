package po;

import java.util.List;
/**
 * 该类对应的是前端页面中的版本信息页面，对应的是数据库里面t_version这张表
 * id:是为了给t_version表中所有信息的进行排序，在表中是默认自动增加的
 * name:对应的是数据表中的v_name字段，代表版本名称的意思
 * des:对应的是数据表中的v_describe字段，代表版本描述的意思
 * number:对应的是数据表的v_number字段，代表版本号的意思
 * data:对应的是数据表的v_update字段，是每次修改版本时的时间
 * @author LiJingHai , October. 1, 2020
 *
 */

public class Version {         
	private Long id;           //序号
	private String name;       //版本名称
	private String des;        //版本描述
	private String number;     //版本号
	private String date;       //更新时间
	
	/**
	   * 组合  版本信息表与文件信息表的关联
	 */
	
	private List<VersionFile> versionFile;  

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<VersionFile> getVersionFile() {
		return versionFile;
	}

	public void setVersionFile(List<VersionFile> versionFile) {
		this.versionFile = versionFile;
	}


	

	
}
