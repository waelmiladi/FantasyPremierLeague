FROM ubuntu:14.04

FROM williamyeh/java8

RUN apt-get update && apt-get install -y --no-install-recommends software-properties-common && apt-get update

# Mongo
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927
RUN echo "deb http://repo.mongodb.org/apt/ubuntu trusty/mongodb-org/3.2 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.2.list
RUN apt-get update && apt-get install -y mongodb-org=3.2.10 mongodb-org-server=3.2.10 mongodb-org-shell=3.2.10 mongodb-org-mongos=3.2.10 mongodb-org-tools=3.2.10

RUN echo "mongodb-org hold" | sudo dpkg --set-selections
RUN echo "mongodb-org-server hold" | sudo dpkg --set-selections
RUN echo "mongodb-org-shell hold" | sudo dpkg --set-selections
RUN echo "mongodb-org-mongos hold" | sudo dpkg --set-selections
RUN echo "mongodb-org-tools hold" | sudo dpkg --set-selections

RUN mkdir -p /data/db

# Elasticsearch

#RUN groupadd -g 1000 elasticsearch && useradd elasticsearch -u 1000 -g 1000
#
#RUN apt-key adv --keyserver pgp.mit.edu --recv-keys 46095ACC8548582C1A2699A9D27D666CD88E42B4 && \
#    add-apt-repository -y "deb http://packages.elastic.co/elasticsearch/2.x/debian stable main" --keyserver https://pgp.mit.edu/ && \
#    apt-get update && \
#    apt-get install -y --no-install-recommends elasticsearch
#
#WORKDIR /usr/share/elasticsearch
#
#RUN set -ex && for path in data logs config config/scripts; do \
#        mkdir -p "$path"; \
#        chown -R elasticsearch:elasticsearch "$path"; \
#    done
#
#COPY logging.yml /usr/share/elasticsearch/config/
#COPY elasticsearch.yml /usr/share/elasticsearch/config/
#
#USER elasticsearch
#
#ENV PATH=$PATH:/usr/share/elasticsearch/bin

#EXPOSE 27017 28017 9200 9300
#ENTRYPOINT ["/usr/bin/mongod"]

# CMD ["elasticsearch"]