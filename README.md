
# Spring Boot integration with Redis 

## Table of contents
* [General info](#general-info)
* [What is Redis](#what-is-redis)
* [Technologies](#technologies)
* [Project contents](#project-contents)
* [Setup](#setup)

## General info
Simple project with Spring Boot, integration Redis using Jedis

## What is REDIS
- Is a powerfull and extremely fast in memory database;
- Store data in key-value
- Persistence, Optionally, you can save the data to desk
	
## Technologies
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Redis](https://redis.io/)
* [Jedis - Java client to Redis](https://github.com/redis/jedis)
* [Swagger API](https://swagger.io/)

## Project contents
This project contains the mains operations from Redis template

- Value operations use opsForValue(): returns redis string (or value) operations.
- Hash Operations use opsForHash: returns redis hash operations.
- List Operations use opsForList(): return redis list operations.
- Set Operations use opsForSett(): returns redis set operation.

## Setup
To run this project:

```
$ git clone https://github.com/ximendes/redis-springboot.git
$ cd redis-springboot
```

Start redis with docker compose
```
$ docker-compose up -d
```

Run Application:
```
$ mvn spring-boot:run
```

Swagger api is available on  : http://localhost:8080/swagger-ui.html#/








