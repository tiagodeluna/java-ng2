# **SkipTheDishes - Challenge**

Build the future of food delivery app that scales to millions of users Important to focus on performance and scale.

## Team

* [Diego Santos](https://www.behance.net/santosdiego)
* [Igor Harã](https://github.com/igorhara)
* [Leonardo Dias](https://github.com/leonardodias76)
* [Tiago Luna](https://github.com/tiagodeluna/)

## Technologies
* Angular 2
* JDK 1.8
* Maven
* Spring Boot
* Mongo DB

## Specifications
All the sites that have been searched, both in Brazil and in Canada, follow the same philosophy, that is, filters by:
* Kind of food
* Location
* Lowest or highest price
* Free shipping or not
* The best evaluated
* User Reviews

One thing that has been observed that in the sites that has been visited, none has the philosophy of cashback.
As the proposal was creative, was implemented something a bit innovative in the issue of delivery.
So we created DishCoins.

## What is DishCoins? 
DishCoins is a currency of SkipTheDishes that can be used to purchase dishes within the food delivery platform.


## How to Accumulate DishCoins?

At each request made by the user in the application, points are accumulated that can be used to purchase food within SkipTheDishes. 
These points are called Dish Coins. The score is calculated over the value of each order. Ex. Every $ 1.00 spent = 1 Point.

This score belongs to the user, points obtained by the purchase in a restaurant X can be used to redeem in the restaurant Y.

## How to Pay with DishCoins?

Each dish has its value in cash and also a value on DishCoins - calculated by the system. The user views the dish price in both cash and points.
When making an order, if the user has enough points, the option "Pay with Dish Coins" is then enabled.

## How to execute?
To execute the project, run the commands bellow:
```shell
mvn package
cd target
java -jar skipthedishes.jar
```