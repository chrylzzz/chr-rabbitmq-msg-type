package com.chryl.e_topic;

import cn.hutool.core.thread.ThreadUtil;
import com.chryl.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 在controller中添加测试接口，调用该接口开始发送消息；
 */
@Api(tags = "TopicRabbitController", description = "RabbitMQ功能测试")
@Controller
@RequestMapping("/rabbit")
public class TopicRabbitController {
    //运行后结果如下，可以发现生产者往队列中发送包含不同路由键的消息，instance 1和instance 2分别获取到了匹配的消息。
    @Autowired
    private TopicSender topicSender;

    @ApiOperation("通配符模式")
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult topicTest() {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }
}
