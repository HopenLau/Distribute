package po;
/**
 * 日志信息
 * 该类对应的是前端页面中的日志信息页面，对应的是数据库里面t_log这张表
 * id:是为了给t_log表中所有信息的进行排序，在表中是默认自动增加的
 * who:对应的是数据表中的who字段，代表版本更新人员的意思
 * what:对应的是数据表中的what字段，代表操作描述的意思
 * data:对应的是数据表的when字段，是每次版本更新的时间
 * @author LiJingHai , October. 1, 2020
 *
 */
public class Log {
	//序号
	private Long id;
	//版本更新人员
	private Long who;
	//操作描述
	private String what;
	//更新时间
	private String date;
	
	/**
	 * gets/sets方法
	 * @return 对应的属性名称
	 */
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWho() {
		return who;
	}
	public void setWho(Long who) {
		this.who = who;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", who=" + who + ", what=" + what + "]";
	}
	
}
