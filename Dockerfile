FROM jenkins/jenkins:2.361.4-lts-jdk11
USER root
RUN apt-get update && apt-get install wget
USER jenkins