package com.example.sky.demo.agent.service;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String say(String words) {
        System.out.println(words);
        return "hello " + words + ",traceId:" + TraceContext.traceId();
    }

    public String errorCall(String string) {
        if (string.equals("hello")) {
            throw new RuntimeException("err");
        }
        return string;
    }
}
