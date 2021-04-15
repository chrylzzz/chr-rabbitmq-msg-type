package com.chryl.d_direct;

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
@Api(tags = "DirectRabbitController", description = "RabbitMQ功能测试")
@Controller
@RequestMapping("/rabbit")
public class DirectRabbitController {
    //运行后结果如下，可以发现生产者往队列中发送包含不同路由键的消息，instance 1获取到了orange和black消息，instance 2获取到了green和black消息。
    @Autowired
    private DirectSender directSender;

    @ApiOperation("路由模式")
    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult directTest() {
        for (int i = 0; i < 10; i++) {
            directSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }
}
