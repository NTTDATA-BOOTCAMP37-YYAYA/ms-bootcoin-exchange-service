FROM openjdk:11
EXPOSE 8082
ADD ./target/ms-bootcoin-wallet-0.0.1-SNAPSHOT.jar ms-bootcoin-wallet.jar
ENTRYPOINT ["java","-jar","/ms-bootcoin-wallet.jar"]