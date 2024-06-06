# Active Cookie Finder

This project is a command-line application that processes a cookie log file and returns the most active cookie(s) for a specific date.

## Features

- Finds the most active cookie(s) for a specified date.
- Supports processing large log files efficiently.
- Caches results for quick subsequent access.
- Provides detailed error messages for invalid inputs.

## Requirements

- Java 17 or higher
- Maven 3.6.3 or higher

## Installation
1. **Clone the repository:**
    ```
    git clone https://github.com/BeileiTan/active-cookie-finder.git
    cd active-cookie-finder
   ```

2. **mvn build package:**
    ```
   ./mvnw clean package
    ```

3. **Run the application using the following command:**
    ```
   java -jar target/active-cookie-finder-0.0.1-SNAPSHOT.jar -f cookie_log.csv -d 2018-12-09
    ```
   
   _This command will read the cookie_log.csv file and print the most active cookie(s) for December 9, 2018._


## Project Structure
```
active-cookie-finder
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── springdemo
│   │   │           └── activecookiefinder
│   │   │               ├── cookie
│   │   │               │   ├── CookieService.java
│   │   │               │   └── CookieService.java
│   │   │               │── system    
│   │   │               │   ├── exception
│   │   │               │      ├── InvalidDateFormatException.java
│   │   │               │      ├── InvalidLogFormatException.java
│   │   │               │      ├── ObjectNotFoundException.java  
│   │   │               ├── ActiveCookieFinderApplication.java
│   ├── test
│   │   ├── java
│   │   │   └── com
│   │   │       └── springdemo
│   │   │           └── activecookiefinder
│   │   │               └── ActiveCookieFinderApplicationTests.java
├── pom.xml
├── cookie_log.csv
└── README.md
```

## Handling Errors
The application provides meaningful error messages for various scenarios:
 1. Invalid Date Format:
 2. File Not Found:
 3. Date Not Found in Log:

## Testing
Unit tests are used to ensure the correctness of the application and are integrated into a continuous integration pipeline using GitHub Actions, which are triggered on every pull request.

## External Library
Only picocli (a mighty tiny command line interface) is used in this application.