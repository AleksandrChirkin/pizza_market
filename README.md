# Pizza market

Test project for naumen java course

## How to start this application
### By CLI

Add environs for database
```bash
export DATABASE_URL="jdbc:postgresql://localhost:5432/PIZZA_DB"
export DATABASE_USER="mark"
export DATABASE_PASSWORD="password"
```
Launch application
```bash
java com.pizza_market.PizzaMarketApplication
```

### By docker
```bash
docker pull pizza_market
cd pizza_market
docker-compose up
```

Authors:
* Averchenko Mark
* Chirkin Alexander
