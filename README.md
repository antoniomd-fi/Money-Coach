# Money-Coach
This is a project to BEDU &amp; HSBC-Tech Academy


## Table of Contents
1. [Technologies](#technologies)
2. [Installation](#installation)
3. [Use (Money Coach)](#usage)
4. [Use (Money Coach Consumer)](#usage2)
5. [Author](#author)

<a name="technologies"></a>
## 1. Technologies

* [Java](https://docs.oracle.com/en/java/)
* [Maven](https://maven.apache.org/guides/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [H2](https://www.h2database.com/html/main.html)
* [RabbitMQ](https://www.rabbitmq.com/documentation.html)
* [Docker](https://docs.docker.com/)
* [Lombok](https://projectlombok.org/features/)
* [OpenPDF](https://github.com/LibrePDF/OpenPDF)
* [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
* [Spring Validation](https://www.baeldung.com/spring-boot-bean-validation)
* [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
* [Selenium](https://www.selenium.dev/documentation/)
* [Swagger](https://swagger.io/docs/)
* [Git](https://git-scm.com/)


<a name="installation"></a>
## 2. Installation and run instructions
Create a new folder and clone the repository.
Opend the terminal and type:
```bash
    https://github.com/antoniomd-fi/Money-Coach.git
```
In the terminal type:
```bash
    docker-compose up
```
Run both projects on IDE or terminal
```bash
    MoneyCoach
    MoneyCoachConsumer
```
## Note
If you wanna run the projects like a jar, you must add **"@Ignore"** to the [SeleniumTest](https://github.com/antoniomd-fi/Money-Coach/blob/main/Money-Coach/src/test/java/com/example/moneycoach/SeleniumTest.java) class 
<a name="usage"></a>
## 3. Use Money Coach

Exist two default users on Spring Security
* **amd**
* **test**

You must create those users on database in previous order then, amd will have id **1** and test will have **2**. Those users is to simulate real users. Both passwords are: **1234**

You will use the previous authenticaton in the next endpoints:

* /getUser/{id}
* /getTotalEntriesByUser/{id}
* /getTotalExitsByUser/{id}
* /getBalance/{id}

where **{id}** will must be change to the id user, remember:

* 1 to amd
* 2 to test

Each of the endpoint must will need the login. To others users, the login ain't necessary

The endpoints starting with **/admin** must will need login with 

* user: **admin**
* password **admin**


## Swagger

You can use 

**localhost:3000/swagger-ui/index.html**

to use the API esier, but you can use an app like **insomnia** or **postman** too.

### This is the swagger header

<img width="1100" asrc="https://github.com/antoniomd-fi/Money-Coach/blob/main/readmeAssets/swagger1.png">

### You can test the following services:

<img width="1100" src="https://github.com/antoniomd-fi/Money-Coach/blob/main/readmeAssets/swagger2.png">
<img width="1100" src="https://github.com/antoniomd-fi/Money-Coach/blob/main/readmeAssets/swagger3.png">
<img width="1100" src="https://github.com/antoniomd-fi/Money-Coach/blob/main/readmeAssets/swagger4.png">

**don't forget which of them need a login**

<a name="usage2"></a>
## 4. Use Money Coach Controller

Use **localhost:3000/admin/sendList** to send a report

<img width="1100" src="https://github.com/antoniomd-fi/Money-Coach/blob/main/readmeAssets/JMS1.png">

Use **localhost:4200/admin/donwloadList** to download the PDF file with all the actual users.

<img width="1100" src="https://github.com/antoniomd-fi/Money-Coach/blob/main/readmeAssets/JMS2.png">
<img width="1100" src="https://github.com/antoniomd-fi/Money-Coach/blob/main/readmeAssets/JMS3.png">


<a name="author"></a>
## 5. Author

- [Antonio Martin](https://github.com/antoniomd-fi)
