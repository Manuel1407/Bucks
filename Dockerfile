FROM openjdk:17
RUN touch /env.txt
RUN printenv > /env.txt
MAINTAINER PodA
COPY target/reward_your_teacher.jar reward_your_teacher.jar
ENTRYPOINT ["java","-jar","/reward_your_teacher.jar"]
