# DOI DUL Staging

FROM crossref/event-data-docker-base-build
MAINTAINER Joe Wass jwass@crossref.org

COPY src /usr/src/app/src
COPY test /usr/src/app/test
COPY project.clj /usr/src/app/project.clj

WORKDIR /usr/src/app