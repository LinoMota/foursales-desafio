#!/bin/bash

ES_HOST="http://localhost:9200"
SCRIPT_NAME="search-products"

SCRIPT_BODY='{
  "script": {
    "lang": "mustache",
    "source": "{ \"query\": { \"bool\": { \"must\": [ { \"fuzzy\": { \"name\": { \"value\": \"{{name}}\", \"fuzziness\": \"AUTO\" } } }, { \"range\": { \"price\": { \"gte\": \"{{min_price}}\", \"lte\": \"{{max_price}}\" } } } {{#category}}, { \"match\": { \"category\": \"{{category}}\" } }{{/category}} ], \"filter\": [ { \"range\": { \"stock\": { \"gt\": 0 } } } ] } }, \"from\": \"{{from}}\", \"size\": \"{{size}}\" }"
  }
}'


curl -X PUT "$ES_HOST/_scripts/$SCRIPT_NAME" \
     -H "Content-Type: application/json" \
     -d "$SCRIPT_BODY"