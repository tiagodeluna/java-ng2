# **SkipTheDishes - Challenge**

Build the future of food delivery app that scales to millions of users Important to focus on performance and scale.

## Team

* [Diego Santos](https://www.behance.net/santosdiego) -  https://www.linkedin.com/in/osantosdiego/
* [Igor Harã](https://github.com/igorhara) - https://www.linkedin.com/in/igorhara/
* [Leonardo Dias](https://github.com/leonardodias76) - https://www.linkedin.com/in/leonardo-dias-oliveira/
* [Tiago Luna](https://github.com/tiagodeluna/) - https://www.linkedin.com/in/tiagodeluna/

## Technologies
* Angular 2
* JDK 1.8
* Maven
* Spring Boot
* Mongo DB

## Specifications
All the sites that have been searched, both in Brazil and in Canada, follow the same philosophy, that is, filters by:
* Kind of food (planned but not done)
* Location (planned but not done)
* Lowest or highest price (planned but not done)
* Free shipping or not (planned but not done)
* The best evaluated (partially implemented)
* User Reviews (partially implemented)
* Integrated search of dishes and restaurants (done)
* Rating of dish and restaurant (rating is implemented with random rating generation)

One thing that has been observed that in the sites that has been visited, none has the philosophy of cashback.
As the proposal was creative, was implemented something a bit innovative in the issue of delivery.
So we created DishCoins.

## What is DishCoins? 
DishCoins is a currency of SkipTheDishes that can be used to purchase dishes within the food delivery platform.


## How to Accumulate DishCoins?

At each request made by the user in the application, DishCoins are accumulated that can be used to purchase food within SkipTheDishes. 
The DishCoins are calculated over the value of each order. Ex. Every $ 1.00 spent = 1 DishCoins.

This DishCoins belongs to the user, DishCoins obtained by the purchase in a restaurant X can be used to redeem in the restaurant Y.

## How to Pay with DishCoins?

Each dish has its value in cash and also a value on DishCoins - calculated by the system. 
The user views the dish price in both cash and DishCoins  when making an order, if the user has enough DishCoins, the option "Pay with Dish Coins" is then enabled.

## How to execute?
To execute the project, run the commands bellow:
```shell
mvn package
cd target
java -jar skipthedishes.jar
```