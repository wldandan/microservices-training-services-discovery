cd eureka-server
SPRING_PROFILES_ACTIVE=primary java -jar build/libs/eureka-server-0.0.1.jar &
SPRING_PROFILES_ACTIVE=secondary java -jar build/libs/eureka-server-0.0.1.jar &
SPRING_PROFILES_ACTIVE=tertiary java -jar build/libs/eureka-server-0.0.1.jar &
cd -