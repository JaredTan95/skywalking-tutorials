FROM openjdk:8-jre-alpine

LABEL maintainer="tanjian20150101@gmail.com"

ENV SW_APPLICATION_CODE=java-agent-demo \
	SW_COLLECTOR_SERVERS=localhost:11800

COPY skywalking-agent /apache-skywalking-apm-incubating/agent

COPY target/sky-demo-1.0-SNAPSHOT.jar /demo.jar

ENTRYPOINT java -javaagent:/apache-skywalking-apm-incubating/agent/skywalking-agent.jar -Dskywalking.collector.backend_service=${SW_COLLECTOR_SERVERS} \
-Dskywalking.agent.application_code=${SW_APPLICATION_CODE} -jar /demo.jar
