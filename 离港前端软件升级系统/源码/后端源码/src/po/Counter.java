package po;
/**
 * 柜台信息
 * 该类对应的是前端页面中的柜台信息页面，对应的是数据库里面t_counter这张表
 * id:是为了给t_counter表中所有信息的进行排序，在表中是默认自动增加的
 * cId:对应的是数据表中的c_id字段，代表柜台号的意思
 * name:对应的是数据表中的c_name字段，代表柜台名称的意思
 * cIp:对应的是数据表的c_ip字段，是每台柜台的ip地址的时间
 * @author LiJingHai , October. 1, 2020
 *
 */

public class Counter {
	//序号
	private Long id;
	//柜台号
	private Long cId;
	//柜台名称
	private String name;
	//ip地址
	private String cIp;
	
	/**
	 * gets/sets方法
	 * @return 对应的属性名称
	 */
	public Long getcId() {
		return cId;
	}
	public void setcId(Long cId) {
		this.cId = cId;
	}
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
	public String getcIp() {
		return cIp;
	}
	public void setcIp(String cIp) {
		this.cIp = cIp;
	}

	@Override
	public String toString() {
		return "Counter [id=" + id + ", cId=" + cId + ", name=" + name + ", cIp=" + cIp + ", version=" + "]";
	}
	
	
	
}
