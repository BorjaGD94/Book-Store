# Java EE Book Store

## Project Summary

My project consists of an Online Bookstore that has three different security roles. These roles can perform different actions and operate with the
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

