FROM openjdk:19-oracle

ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="symbol-counter"
ARG MAINTAINER="Arina St <voidcatwallker@ya.ru>"
LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

ADD ./target/symbol-counter-0.0.1-SNAPSHOT.jar symbol-counter.jar

EXPOSE 8080

ENTRYPOINT [ "/bin/sh", "-c", "java -jar symbol-counter.jar" ]