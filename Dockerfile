FROM tomcat:9.0-jdk17

# ここにWARを配置
COPY JavaStockManagementSystem.war /usr/local/tomcat/webapps/JavaStockManagementSystem.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
