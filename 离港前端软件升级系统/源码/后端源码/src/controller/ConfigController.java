package controller;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import po.Config;
import service.ConfigService;
import service.MqttClient;

/**
 * 这是文件下发的最后一步，
 * 可以通过Servlect请求讲指定的版本配置文件下发到指定的节点柜台，
 * 可以通过修改配置文件的功能，在已下发的文件柜台上修改配置文件内容，，
 * 并进行实时更新已修改的配置文件的内容
 *  @author LiJingHai , October. 1, 2020
 *
 */
@RestController
@RequestMapping("/config")
public class ConfigController{
	

    @Autowired
    private ConfigService configService;
    /**
            * 将指定版本的配置文件发往前端
     * @param request : ServletRequest - HTTP请求
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.GET)
    public String getConfig(ServletRequest request) throws Exception{
        return configService.getConfig(request);
    }
    
    /**
            * 将修改的配置内容发往指定节点更新
     * @param nodeId : Long - 节点编号（C_ID）
     * @param config : Config - 修改的配置内容
     * @throws Exception
     */
    @RequestMapping(value="{nodeId}", method=RequestMethod.PUT)
    public void pushConfig(
        @PathVariable("nodeId") Long nodeId,
        @RequestBody Config config) throws Exception{
        /**
                     * 通知指定的节点更新配置内容
         */
        MqttClient.client.publish("config/" + nodeId, config.getContent());
    }
}
