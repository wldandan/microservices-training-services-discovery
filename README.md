## 使用Ribbon 客户端负载均衡

**Part 1, 运行Eureka Server和ReviewService

1.  启动EurekaServer

2.  启动两个ReviewService实例
  - run it by Gradle
    SERVER_PORT=9010 gradle bootRun
    SERVER_PORT=9011 gradle bootRun

  - run it by Jar file
    SERVER_PORT=9010 java -jar xxxxx.jar 
    SERVER_PORT=9111 java -jar xxxxx.jar 

3. 检查Eureka Server，查看注册的Review service

**Part 2, 实现EventService

1. 打开EventService项目

2. 在EventController使用LoadBalance Client调用Review Service

3. 运行EventService，
	- SERVER_PORT=9090 gradle bootRun
	- SERVER_PORT=9090 java -jar xxxxx.jar 

4. 访问localhost:9090/reviews