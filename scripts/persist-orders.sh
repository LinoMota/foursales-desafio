#!/bin/bash

LOGIN_URL="http://localhost:8080/auth/login"
PASSWORD="senhauser"

USERS=("normaluser" "joaosilva" "mariaoliveira" "pedroalves" "lucasrodrigues" "carolcastro" "andrebatista")

for USERNAME in "${USERS[@]}"; do
    echo "Autenticando usuário '$USERNAME'..."
    TOKEN=$(curl -s -X POST "$LOGIN_URL" \
        -H "Content-Type: application/json" \
        -d "{\"username\": \"$USERNAME\", \"password\": \"$PASSWORD\"}" | jq -r '.token')

    echo "Token obtido para $USERNAME: $TOKEN"

    ITEMS=$(shuf -i 1-50 -n 3 | awk '{print "{\"productId\":" $1 ", \"quantity\":" int(rand()*10)+1 "}"}' | jq -s '.')

    ORDER_JSON=$(jq -n --argjson items "$ITEMS" '{items: $items}')

    echo "Criando pedido para $USERNAME..."
    curl -s -X POST "http://localhost:8080/orders" \
        -H "Authorization: Bearer $TOKEN" \
        -H "Content-Type: application/json" \
        -d "$ORDER_JSON"

    echo $ORDER_JSON

    echo "Pedido criado para $USERNAME"
done