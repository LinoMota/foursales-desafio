#!/bin/bash

BASE_URL="http://localhost:8080"
LOGIN_URL="$BASE_URL/auth/login"
ORDERS_URL="$BASE_URL/orders"
PAY_ORDER_URL="$BASE_URL/pay-order"

USERS=("normaluser" "joaosilva" "mariaoliveira" "pedroalves" "lucasrodrigues" "carolcastro" "andrebatista")
PASSWORD="senhauser"

for USER in "${USERS[@]}"; do
    RESPONSE=$(curl -s -X POST "$LOGIN_URL" \
        -H "Content-Type: application/json" \
        -d "{\"username\":\"$USER\", \"password\":\"$PASSWORD\"}")

    JWT_TOKEN=$(echo "$RESPONSE" | jq -r '.token')

    if [[ "$JWT_TOKEN" == "null" ]]; then
        continue
    fi

    ORDERS_RESPONSE=$(curl -s -X GET "$ORDERS_URL" \
        -H "Authorization: Bearer $JWT_TOKEN")

    ORDER_IDS=$(echo "$ORDERS_RESPONSE" | jq -r '.[].id')

    for ORDER_ID in $ORDER_IDS; do
        PAY_ORDER_RESPONSE=$(curl -s -w "%{http_code}" -o /tmp/pay_order_response.json -X POST "$PAY_ORDER_URL/$ORDER_ID" \
            -H "Authorization: Bearer $JWT_TOKEN")

        STATUS_CODE=$(tail -n 1 <<< "$PAY_ORDER_RESPONSE")

        if [[ "$STATUS_CODE" -ne 200 ]]; then
            echo "Failed to pay order $ORDER_ID with status code $STATUS_CODE"
            echo "Response: $(cat /tmp/pay_order_response.json)"
        else
            echo "Order $ORDER_ID paid successfully"
        fi
    done
done