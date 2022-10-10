FROM tomcat:8
ENV APP_ROOT /Java-Acapy-Controller
RUN apt-get update && apt-get install -y default-jdk

COPY Java-Acapy-Controller.war $CATALINA_BASE/webapps/Java-Acapy-Controller.war
WORKDIR $APP_ROOT