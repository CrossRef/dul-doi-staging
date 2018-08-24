# Distributed Usage Logging DOI Staging

A very simple test server to allow Distributed Usage Logging (DUL) users to test end-to-end systems. Provides a mock DOI server which behaves the same as the DOI server, but patches in configurable link headers. 

## To contribute

Edit resources/mappings.json and provide an entry like this. The prefix of the DOI is used to look up the link response.

E.g.:

    {"10.5555", "http://example.com",
     "10.1016": "https://api.elsevier.com/content/usage/doi/"}

Then submit a pull request.

## To use

Until DNS is configured, add this to your hosts file:

    dul-doi-staging.eventdata.crossref.org:159.69.10.84

Then:

    curl --verbose https://dul-doi-staging.eventdata.crossref.org/10.5555/12345678

See the Location field:

    > GET /10.5555/12345678 HTTP/1.1
    > Host: localhost:9438
    > User-Agent: curl/7.49.1
    > Accept: */*
    >
    < HTTP/1.1 200 OK
    < Server: cloudflare
    < Content-Type: text/html;charset=utf-8
    < Content-Length: 0
    < Connection: keep-alive
    < Location: <http://example.com>; rel="dul"
    < Expires: Fri, 24 Aug 2018 11:18:48 GMT
    < Set-Cookie: __cfduid=ddbcccf7fb57189ecc736f0f6f8d8380a1535108311; expires=Sat, 24-Aug-19 10:58:31 GMT; path=/; domain=.doi.org; HttpOnly
    < Cf-Ray: 44f54de0a7a96a67-LHR
    < Date: Fri, 24 Aug 2018 10:58:31 GMT
    < Vary: Accept
    < Date: Fri, 24 Aug 2018 10:58:31 GMT

## To run for local development

    DOI_DUL_STAGING_PORT=9438 lein run

Then 

    curl --verbose http://localhost:9438/10.5555/12345678



## License

Copyright © 2018 Crossref

Distributed under the The MIT License (MIT).
