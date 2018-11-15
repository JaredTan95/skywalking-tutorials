# 02-compile-build-run-skywalking-6.0.0

## 编译参考:
https://github.com/apache/incubator-skywalking/blob/master/docs/en/guides/How-to-build.md

## Elasticsearch配置
- [通过Docker快速启动](https://github.com/JaredTan95/skywalking-docker/blob/master/elasticsearch-Zone-Asia-SH/6.3.2/README.md)

`docker run -p 9200:9200 -p 9300:9300 -e cluster.name=elasticsearch -d wutang/elasticsearch-shanghai-zone:6.3.2`

注意：Skywalking 6.0.0使用端口：`9200`