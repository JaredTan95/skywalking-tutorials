# 01-通过Skywalking-5.x版本的源码构建并运行

## 参考：
- [官方编译指南](https://github.com/apache/incubator-skywalking/blob/5.x/docs/en/How-to-build.md)
- [芋道源码](http://www.iocoder.cn/SkyWalking/build-debugging-environment/)
- [JaredTan95](https://github.com/JaredTan95/JaredTan95.github.io/issues/11)

## 源码地址：https://github.com/apache/incubator-skywalking.git

## 从GitHub下载代码编译
### 在IntelliJ IDEA中编译工程
- 准备环境: git, jdk8，Maven
- `git clone https://github.com/apache/incubator-skywalking.git`
- `cd incubator-skywalking/`
- `git checkout -b 5.x`
- `git submodule init`
- `git submodule update`
- `mvn clean package -DskipTests`
- 将`/incubator-skywalking/apm-protocol/apm-network/target/generated-sources/protobuf`目录下面`grpc-java`和`java`目录右键设置为`Generated Rources Root`.
- 将`apm-collector/apm-collector-remote/apm-remote-grpc-provider/target/generated-sources/protobuf`目录下面`grpc-java`和`java`目录右键设置为`Generated Rources Root`.

## Elasticsearch 启动：
[https://github.com/JaredTan95/skywalking-docker/blob/master/elasticsearch-Zone-Asia-SH/5.6.10/README.md](https://github.com/JaredTan95/skywalking-docker/blob/master/elasticsearch-Zone-Asia-SH/5.6.10/README.md)

## Skywalking 相关配置说明
```
#cluster:
#  zookeeper:
#    hostPort: localhost:2181,localhost:2182 #zookeeper集群地址
#    sessionTimeout: 100000
```

```
configuration:
  default:
    #namespace: xxxxx
    # alarm threshold
    applicationApdexThreshold: 2000 #应用性能指数阀值，Apdex含义请参考如下
    serviceErrorRateThreshold: 10.00 #服务错误率阀值
    serviceAverageResponseTimeThreshold: 2000 #服务平均响应时间阀值
    instanceErrorRateThreshold: 10.00 #实例错误率阀值
    instanceAverageResponseTimeThreshold: 2000 #实例平均响应时间阀值
    applicationErrorRateThreshold: 10.00 #应用错误率阀值
    applicationAverageResponseTimeThreshold: 2000 #应用平均响应时间阀值
    # thermodynamic
    thermodynamicResponseTimeStep: 50 #热力图响应时间
    thermodynamicCountOfResponseTimeSteps: 40 #热力图的响应时间步长数量
    # max collection's size of worker cache collection, setting it smaller when collector OutOfMemory crashed.
    workerCacheMaxSize: 10000 #最大工作缓存数量
    
# Apdex
# 性能指数:
# Apdex(Application Performance Index)是一个国际通用标准，
# Apdex 是用户对应用性能满意度的量化值。它提供了一个统一的测量和报告用户体验的方法，
# 把最终用户的体验和应用性能作为一个完整的指标进行统一度量。
# 如何计算 Apdex:基于“响应性”，Apdex 定义了 3 个用户满意度区间( OneAPM 默认定义的 T 值为 0.5 秒):
# 满意：这样的响应时间让用户感到很愉快，响应时间少于 T 秒钟。
# 容忍：慢了一点，但还可以接受，继续这一应用过程，响应时间 T～4T 秒。
# 失望：太慢了，受不了了，用户决定放弃这个应用，响应时间超过 4T 秒。
```

```
storage:
  elasticsearch:
    clusterName: elasticsearch  #Elasticsearch集群名称，默认为elasticsearch
    clusterTransportSniffer: false
    clusterNodes: localhost:9300 #Elasticsearch连接，默认localhost:9300
    indexShardsNumber: 2
    indexReplicasNumber: 0
    highPerformanceMode: true
    # Batch process setting, refer to https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.5/java-docs-bulk-processor.html
    bulkActions: 2000 # Execute the bulk every 2000 requests
    bulkSize: 20 # flush the bulk every 20mb
    flushInterval: 10 # flush the bulk every 10 seconds whatever the number of requests
    concurrentRequests: 2 # the number of concurrent requests
    # Set a timeout on metric data. After the timeout has expired, the metric data will automatically be deleted.
    traceDataTTL: 90 #追踪数据滚动删除周期，默认90分钟
    minuteMetricDataTTL: 90 #分钟监控数据滚动删除周期，默认90分钟
    hourMetricDataTTL: 36 #小时监控数据滚动删除周期，默认36小时
    dayMetricDataTTL: 45 #天监控数据滚动删除周期，默认45天
    monthMetricDataTTL: 18 #月监控数据滚动删除周期，默认18个月
```







