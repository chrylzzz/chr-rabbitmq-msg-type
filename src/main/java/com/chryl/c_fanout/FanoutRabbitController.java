package com.chryl.c_fanout;

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
@Api(tags = "FanoutRabbitController", description = "RabbitMQ功能测试")
@Controller
@RequestMapping("/rabbit")
public class FanoutRabbitController {
    //运行后结果如下，可以发现生产者往队列中发送包含不同数量.号的消息，instance 1和instance 2同时获取并消费了消息。
    @Autowired
    private FanoutSender fanoutSender;

    @ApiOperation("发布/订阅模式")
    @RequestMapping(value = "/fanout", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult fanoutTest() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }
}

