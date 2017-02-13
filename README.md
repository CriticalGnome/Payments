#Payments project

> __Client__ have one or more __Credit Cards__. Any Card assign to some __Account__ in Payment System. Client can with your Account make __Payment__, block your Account and refund your Account. __Administrator__ can unblock any account.

Project use MySQL database. An example of a Database Dump is located in the _sql/payments.sql file_.

Sample Database have two test users:

|Login|Pass|Privileges|
|---|---|---|
|admin@test.com|123|Administrator|
|user@test.com|123|Default User|


In addition, new users can register themselves. Any new User gets own Payment Account and 1-3 (randomly) Credit Cards.

The project can works in two languages: Russian and English. Switching by special button at the navigation bar.

Project properties located in the _WebContent/WEB-INF/classes/config.properties_:

```properties
#Driver
dbdriver = com.mysql.jdbc.Driver
#Path to Database
dburl = jdbc:mysql://localhost:3306/payments?autoReconnect=true&useSSL=false
#DB user
dbuser = payments
#DB password
dbpassword = payments
#Max connections to DB in Connections Pool
dbmaxconnections = 100
#Max records for current User last Payments
paymentslistmaxrecords = 100
```

##Author
Sergey Kalinovsky

lord.skiminok@gmail.com
