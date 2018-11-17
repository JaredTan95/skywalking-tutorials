# 03 Java (Spring Boot)应用的接入
参考Skywalking Github：[Setup java agent](https://github.com/apache/incubator-skywalking/blob/master/docs/en/setup/service-agent/java-agent/README.md)

[@Trace注解的使用](https://github.com/apache/incubator-skywalking/blob/master/docs/en/setup/service-agent/java-agent/Application-toolkit-trace.md)

## 通过IDEA进行调试接入
- 更多agent 配置可以参考[agent config](https://github.com/apache/incubator-skywalking/blob/master/apm-sniffer/config/agent.config)
- vm options:

```bash
-javaagent:incubator-skywalking/skywalking-agent/skywalking-agent.jar
-Dskywalking.agent.application_code=hello-world-demo
-Dskywalking.collector.backend_service=localhost:11800
```

## 通过Jar包方式接入

```bash
java -javaagent:/apache-skywalking-apm-incubating/agent/skywalking-agent.jar -Dskywalking.collector.backend_service=localhost -Dskywalking.agent.application_code=hello-world-demo-0004 -jar target/sky-demo-1.0-SNAPSHOT.jar

```

## 通过容器接入
- Dockerfile

```bash
FROM openjdk:8-jre-alpine

LABEL maintainer="tanjian20150101@gmail.com"

ENV SW_APPLICATION_CODE=java-agent-demo \
	SW_COLLECTOR_SERVERS=localhost:11800

COPY skywalking-agent /apache-skywalking-apm-incubating/agent

COPY target/sky-demo-1.0-SNAPSHOT.jar /demo.jar

ENTRYPOINT java -javaagent:/apache-skywalking-apm-incubating/agent/skywalking-agent.jar -Dskywalking.collector.backend_service=${SW_COLLECTOR_SERVERS} \
-Dskywalking.agent.application_code=${SW_APPLICATION_CODE} -jar /demo.jar

```

- 构建并运行

```bash
docker build -t hello-demo .
docker run -p 10101:10101 -e SW_APPLICATION_CODE=hello-world-demo-005 -e SW_COLLECTOR_SERVERS=127.10.0.2:11800 hello-demo

```



