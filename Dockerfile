FROM registry.isspaas.com/library/java:8.0
#10.24.17.208

#RUN sed -i s#http.debian.net#mirrors.aliyun.com#g /etc/apt/sources.list \
#  	&& sed -i s#security.debian.org#mirrors.aliyun.com/debian-security#g /etc/apt/sources.list \
#    && apt-get update \
#    && apt-get install -y openssh-client

ENV spring.profiles.active="production"
ENV JAVA_OPTS="-Xmx1024m"

ADD *-provider/target/*.tar.gz /app
WORKDIR /app/bin
#RUN chmod 777 ssh-generetor.sh
ENTRYPOINT ["./startup.sh"]
CMD ["javaconfig"]

EXPOSE 8888


