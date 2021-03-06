# Java EE Book Store

## Project Summary

This project consists of an Online Bookstore that has three different security roles. These roles can perform different actions and operate with the
database in different ways. The entities in my project consist of a Book, PurchaseHistory, Client, Retailer and Administrator as well as entities for
users and groups. These entities relate to each other in the following ways:

In the book entity, there is a One-to-One bidirectional relationship with purchase history, given that every book is unique so the same book me not
be purchased twice. The next relationship is a Many-to-One bidirectional relationship with the client entity, given that a client may have many
books but one book can only belong to one client. The final relationship for the book entity corresponds to a Many-to-One bidirectional
relationship with the retailer entity, given that a book can only belong to one retailer but a retailer can possess many books. The last relationship
corresponds to the purchase history entity and it is a Many-to-One bidirectional relationship with the client entity, as a client may have several
purchases but one specific purchase can only belong to one client.

Now I will explain the functionality available for each role:

Client: Clients can purchase available books (Don’t belong to any client), view the books that belong to them and update their profile information.
If time allows talk about view orders (purchased item along with purchase date).

Retailer: A retailer can publish/create new books to add to the online bookstore, as well as edit the books that they have published and haven’t
been purchased by a client. They can also view the books that they have published. Naturally retailers can update their profile information.

Administrator: The administrator has full functionality, which means he can create, update, read and delete books, regardless of who owns the
book or what retailer published the book to the site. The admin can also add users of any kind (Administrator, Client, Retailer). He can create
users that may belong to one, two or all three groups/roles. Naturally, administrators can delete users as well. Finally, admins can also update
their profile information.

## Design 

My application starts by showing the user a login page and when a successful log in occurs, the user is redirected to his portal. Administrators, Clients and Retailers share the same interface based on bootstrap portal template like the one used in class. All users have a welcome page, their own portal, a dropdown menu and a link that has their username that takes the user to their profile information. The main difference for users is the dropdown item in their portal, which offers different functionality depending on the user type.

 
An Administrator will have a welcome page, followed by an Administrator portal option that will show the admin all the books (owned by clients and for sale) and the admin has the option to view, update or delete any of this books. An administrator has four options in his dropdown menu. The first option gives the admin the possibility to log out. The second option allows the admin to create new users of any kind. The third option allows the admin to view all the clients and the number of purchases made by each and finally the last option allows the admin to press another link that will show all the retailers and the number of books they have published to the online store.

 
A Client will have a welcome page, followed by a Client portal option that will show the client all the books that he owns (has purchased in the past) and the client can only view each book, he can’t update or delete any of these books. At the top of the list there will be an option for the client to buy a book. This button will show a list of books that are available for sale, which means that they have no client assigned in the database. The dropdown menu will only allow the user to log out.

 
A Retailer will have a welcome page followed by a Retailer portal option that will show the retailer all the books that he has published to the site and he can only view these books individually. At the top of the list there will be an option for the Retailer to add a new book to the online bookstore. The retailer will have to fill a book form with all the book information correctly and save this book. The dropdown menu will only allow the user to log out.

 
A user may belong to one, two or three groups, so if a user is a client and a retailer, he can access the retailer portal and the client portal, and naturally he will be able to perform the action of both user types.

 
If the login is not successful, the user will be redirected to an error page that will have an error message. To go back to the login page, the user has a link available so he will just have to press it and he can retry the log in. When a user wants to navigate between the welcome page, their user portal, the dropdown menu or his profile info he can do it at any stage when logged in, given that these options are always listed at the top of the web page.

## Requirements (Installation, Compile, Runtime, Database, etc)

The first thing to do is to setup your database schema and create the connection that the application will use. 

1. Create a new MySQL database schema named itmd4515.    
   &nbsp;&nbsp;&nbsp;&nbsp;a. <img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/1.png" width="380">  
   &nbsp;&nbsp;&nbsp;&nbsp;b. Enter a schema name of itmd4515. Leave the default collation blank. Click apply.  
   &nbsp;&nbsp;&nbsp;&nbsp;c. Click apply on the subsequent confirmation window. This window displays the actual SQL commands used to    &nbsp;&nbsp;&nbsp;&nbsp;create the database.      
2. Create a MySQL user named itmd4515 with full rights to the database(s). The user must have a password of itmd4515. Do not use the  root user for connection pools. This user may already exist from your prior home work. If so, you do not need to re-create.  
    &emsp;a. If your Workbench is displaying Schemas, then click on Management to access the management options.   
    &emsp;b. <img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/2.png" height="280">    
    &emsp;c. Click on Users and Privileges.  
    &emsp;d. Click on Add Account.   
    &emsp;&emsp;&ensp;I. Give your new account a Login Name of itmd4515.  
    &emsp;&emsp;&ensp;II. Limit your new account to Hosts Matching localhost.  
    &emsp;&emsp;&ensp;III. Give your new account a password of itmd4515.  
    &emsp;&emsp;&ensp;IV. Click on Apply to create the account.  
    &emsp;&emsp;&ensp;V. Click on Schema Privileges, add your new itmd4515 schema, select "All" privileges, and click apply.    
3. Create a NetBeans database connection to the new MySQL database. This database connection will be by various tools within NetBeans.     
    &emsp;a. Click on the Services tab.  
    &emsp;b. <img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/3.png" height="180">  
    &emsp;c. Right-click on Databases and choose New Connection.  
    &emsp;d. Select the MySQL JDBC Driver and click Next.  
    &emsp;e. Change the database to itmd4515, enter your credentials, and test the connection.  
    &emsp;f. <img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/4.png" height="280">    
    &emsp;g. Click Finish.  

Now you will need to install your Netbeans application along side the Glassfish server. To do this go to the following link and download the program:

[Download Netbeans](https://netbeans.org/downloads/8.2/)

You will have to choose the Java EE pack that comes with the Glassfish server already installed. When installing Netbeans choose all the default options. Once everything is done you will need my zip folder called "bgonzalez2-fp.zip". In my case (Mac users) you will have to unzip the file and place it in your NetbeansProjects folder (/Users/Borja/NetBeansProjects/bgonzalez2-fp⁩). Then inside Netbeans you should click on Open Project in the File menu which is situated in the tip left corner of the application. Then you will be inside the NetbeansProjects folder and you just need to select the bgonzalez2-fp folder and click on open project. Clean and Build your project by right clicking on your project folder and clicking on Clean and Buid and after this process is done you can run your project by clicking on the green play button in the top-middle of your application (make sure your project folder is selected).

### Tools, Libraries and API's

```xml
<dependencies>
        <dependency>
            <groupId>org.eclipse.persistence</groupId>
            <artifactId>org.eclipse.persistence.jpa</artifactId>
            <version>2.7.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-web-api</artifactId>
            <version>8.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
 
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <compilerArguments>
                        <endorseddirs>${endorsed.dir}</endorseddirs>
                    </compilerArguments>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.3</version>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>2.6</version>
            </plugin>
        </plugins>
    </build>
```

## Screen Captures

### Administrator Captures

<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/6.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/7.png" height="250">
<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/8.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/9.png" height="250">
<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/10.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/11.png" height="250">
<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/12.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/13.png" height="250">
<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/14.png" height="250">

### Retailer Captures

<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/15.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/16.png" height="250">
<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/17.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/18.png" height="250">
<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/19.png" height="250">

### Client Captures

<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/20.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/21.png" height="250">
<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/22.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/23.png" height="250">
<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/24.png" height="250">

### Login Failed Capture

<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/25.png" height="250">

### Validation Error Captures

<img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/26.png" height="250"><img src="https://github.com/BorjaGD94/Book-Store/blob/master/screenshots/27.png" height="250">
