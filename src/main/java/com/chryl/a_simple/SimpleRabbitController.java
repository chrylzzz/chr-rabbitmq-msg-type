package com.chryl.a_simple;

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
@Api(tags = "SimpleRabbitController", description = "RabbitMQ功能测试")
@Controller
@RequestMapping("/rabbit")
public class SimpleRabbitController {
    //运行后结果如下，可以发现生产者往队列中发送消息，消费者从队列中获取消息并消费。
    @Autowired
    private SimpleSender simpleSender;

    @ApiOperation("简单模式")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult simpleTest() {
        for (int i = 0; i < 10; i++) {
            simpleSender.send();
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }
}
