#!/bin/bash

FILE="products.json"
URL="http://localhost:8080/products"
LOGIN_URL="http://localhost:8080/auth/login"

USERNAME="admin"
PASSWORD="senhaadmin"


echo "Autenticando usuário '$USERNAME'..."
TOKEN=$(curl -s -X POST "$LOGIN_URL" \
    -H "Content-Type: application/json" \
    -d "{\"username\": \"$USERNAME\", \"password\": \"$PASSWORD\"}" | jq -r '.token')


if [[ -z "$TOKEN" || "$TOKEN" == "null" ]]; then
    echo "Erro ao obter token de autenticação!"
    exit 1
fi

echo $TOKEN
echo "Token obtido com sucesso!"


if [[ ! -f "$FILE" ]]; then
    echo "Arquivo $FILE não encontrado!"
    exit 1
fi

jq -c '.[]' "$FILE" | while read -r product; do
    name=$(echo "$product" | jq -r '.name')
    category=$(echo "$product" | jq -r '.category')

    echo "Enviando produto: $name ($category)"

    response=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$URL" \
        -H "Content-Type: application/json" \
        -H "Authorization: Bearer $TOKEN" \
        -d "$product")

    if [[ "$response" -eq 201 || "$response" -eq 200 ]]; then
        echo "Produto '$name' da categoria '$category' cadastrado com sucesso!"
    else
        echo "Erro ao cadastrar produto '$name'. Código HTTP: $response"
    fi

    sleep 1
done
