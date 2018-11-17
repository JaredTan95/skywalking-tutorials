package com.example.sky.demo.agent.controller;

import com.example.sky.demo.agent.service.HelloService;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    private HelloService helloService;

    public HelloWorldController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Trace
    @GetMapping("/hello/{words}")
    public String hello(@PathVariable("words") String words) {
        ActiveSpan.tag("hello,测试😄,你输入的是：", words);
        return helloService.say(words);
    }

    @Trace
    @GetMapping("/ok")
    public String ok() {
        //TraceContext.traceId();
        ActiveSpan.tag("ok,测试😁", "ok啦");
        log.info("{}", this);
        log.info("{}", TraceContext.traceId());
        return "ok,traceId:" + TraceContext.traceId();
    }

    @GetMapping("/err")
    public String err() {
        return helloService.errorCall("hello");
    }

    @Trace
    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        log.info("{}", TraceContext.traceId());
        ActiveSpan.tag("sleep,测试😁", "ok啦");
        Thread.sleep(6000l);
        return "sleep:6s,traceId:" + TraceContext.traceId();
    }
}
