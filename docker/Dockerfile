FROM tomcat:8.0-alpine
LABEL maintainer="deepak@softwareyoga.com"

ADD Java-Acapy-Controller.war /usr/local/tomcat/webapps/
ADD config.properties /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]