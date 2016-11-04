cd event-service &&
SERVER_PORT=9000 java -jar build/libs/event-service-0.0.1.jar &&
cd -
cd review-service
SERVER_PORT=9010 java -jar build/libs/review-service-0.0.1.jar