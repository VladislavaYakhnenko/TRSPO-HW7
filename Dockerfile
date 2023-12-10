FROM openjdk:20
COPY ./out/production/trspo-hw7/ /app
WORKDIR /app
ENTRYPOINT ["java","Main"]
