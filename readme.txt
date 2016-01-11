This project can be imported into an IDE (Eclipse or IntelliJ) as a maven project

This is a spring boot application. To execute the restful service, use the following command:
mvn spring-boot:run

The following URLs can be used to generate prime numbers:
Trial Division algorithm:
http://localhost:8080/trialDivision?limit=10 (specify required limit)
http://localhost:8080/cache (no limit paramater default to a limit of 100)

Sieve of Eratosthenes
http://localhost:8080/sieve?limit=10 (specify required limit)
http://localhost:8080/sieve (no limit paramater default to a limit of 100)

Cache (Pre-computed primes up to a limit of one million)
http://localhost:8080/cache?limit=10 (specify required limit)
http://localhost:8080/cache (no limit paramater default to a limit of 100)

The maximum limit for trial division and sieve algorithms is one billion