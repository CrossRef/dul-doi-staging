# DOI DUL Staging

FROM clojure
MAINTAINER Joe Wass jwass@crossref.org

COPY src /usr/src/app/src
COPY resources /usr/src/app/resources
COPY project.clj /usr/src/app/project.clj

WORKDIR /usr/src/app