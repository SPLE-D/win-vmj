#!/bin/bash

cleanup() {
    echo "Exiting script..."
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

read -p "Enter the path to the frontend directory: " frontend_dir

echo "SELECT 'CREATE DATABASE eventmanager_product_qrispayment' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'eventmanager_product_qrispayment') \gexec" | psql "postgresql://Finalsmash!102938:fathurrahman.kesuma@localhost"
for file in sql/*.sql; do
    psql -a -f "$file" "postgresql://Finalsmash!102938:fathurrahman.kesuma@localhost/eventmanager_product_qrispayment"
done

java -cp eventmanager.product.qrispayment --module-path eventmanager.product.qrispayment -m eventmanager.product.qrispayment &

cd $frontend_dir && {
    npm install && {
        npm run json:server &
        npm run start &
    }
}

wait