## 使用Ribbon 客户端负载均衡

**Part 1, 运行Eureka Server和ReviewService

1.  启动EurekaServer

2.  启动两个ReviewService实例
  - run it by Gradle
    SERVER_PORT=9100 gradle bootRun
    SERVER_PORT=9101 gradle bootRun
  - run it by Jar file
    SERVER_PORT=9100 java -jar xxxxx.jar 
    SERVER_PORT=9101 java -jar xxxxx.jar 

3. 检查Eureka Server，查看注册的Review service

**Part 2, 使用FeignClient实现EventService

1. 打开EventService项目

2. 使用FeingClient调用Event Service

3. 运行EventService，
	- SERVER_PORT=9000 gradle bootRun
	或者
	- SERVER_PORT=9000 java -jar xxxxx.jar 

4. 访问localhost:9000/reviews