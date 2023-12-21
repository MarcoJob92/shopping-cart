This is a small project to implement a shopping cart.

The application was written using Java21 and not Kotlin just for simplicity as Java is my main programming language.

This application has two entities, **ShoppingCart** and **Item**, and a shoppingCart object contains a list of items.  
The *items* attribute in the ShoppingCart class was implemented as a **LinkedHashMap**, an enhanced version of HashMap
that maintains the insertion order.
I chose to use this data structure for two reasons, a Map makes removing and retrieving an object by key very easy,
and the receipt will show items in the insertion order.

The *price* attribute of the Item class is represented by a **double** data type, which can represent a maximum number
of around 2^1024, which is more than enough for most shopping carts or eCommerces but in a scenario where numbers with
more than two fraction digits are used, then another data type could be considered.

This is just a sample application, and the *model* classes contain also their related functions, in a commercial application
that exposes REST services those functions and the business logic should be moved to the Service layer.

*Lombok* library was used to generate setters, getters and constructors to avoid boilerplate code.

I wrote Unit Tests using the *JUnit* library. 


