package com.chryl.b_work;

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
@Api(tags = "WorkRabbitController", description = "RabbitMQ功能测试")
@Controller
@RequestMapping("/rabbit")
public class WorkRabbitController {
    //运行后结果如下，可以发现生产者往队列中发送包含不同数量.号的消息，instance 1和instance 2消费者互相竞争，分别消费了一部分消息。
    @Autowired
    private WorkSender workSender;

    @ApiOperation("工作模式")
    @RequestMapping(value = "/work", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult workTest() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }
}
