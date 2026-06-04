#!/bin/bash

cleanup() {
    echo "Exiting script..."
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

read -p "Enter the path to the frontend directory: " frontend_dir

echo "SELECT 'CREATE DATABASE eventmanager_product_jakifaizi' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'eventmanager_product_jakifaizi') \gexec" | psql "postgresql://:@localhost"
for file in sql/*.sql; do
    psql -a -f "$file" "postgresql://:@localhost/eventmanager_product_jakifaizi"
done

java -cp eventmanager.product.jakifaizi --module-path eventmanager.product.jakifaizi -m eventmanager.product.jakifaizi &

cd $frontend_dir && {
    npm install && {
        npm run json:server &
        npm run start &
    }
}

wait