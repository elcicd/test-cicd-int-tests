FROM eclipse-temurin:11-jdk-ubi9-minimal

VOLUME /tmp

USER root

ENV MAVEN_HOME /usr/share/maven
ENV JAVA_HOME /opt/java/openjdk
ENV PATH $JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

RUN microdnf update -y && \
    microdnf install -y maven git
    
copy . /opt/tests

EXPOSE 8080

USER 1001