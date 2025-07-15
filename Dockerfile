FROM tomcat:10.1-jdk21

# ここにWARを配置
COPY JavaStockManagementSystem.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
