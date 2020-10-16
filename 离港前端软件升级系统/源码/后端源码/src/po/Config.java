package po;
/**
 * 在整体文件中实现的是文件下发的功能，
 * 首先获取已上传的文件信息，
 * 在检查指定文件件中是否有准备下发的文件，
 * 若有则下发成功，
 * 若没有则显示无此版本
 * @author LiJingHai , October. 1, 2020
 *
 */
public class Config{
	//已上传的文件内容
	private String content;
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
}
