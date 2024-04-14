FROM maven:3.9-eclipse-temurin-8

RUN mkdir -p /home/ubuntu/ui-tests

WORKDIR /home/ubuntu/ui-tests

COPY . /home/ubuntu/ui-tests

ENTRYPOINT ["/bin/bash", "entrypoint.sh"]