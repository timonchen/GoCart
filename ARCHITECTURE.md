# COMP3350 G06 Iteration 3
## GoCart Architecture
Due: April 10, 2023

### Packages
* Application
* Business(comp3350.GoCart.objects)
* Objects(comp3350.GoCart.objects)
* Persistence(comp3350.GoCart.persistence)
    * HSQLDB
* Presentation(comp3350.GoCart.presentation)
* Android Test(package comp3350.GoCart)
    * systemTests
* Test(comp3350.GoCart.test)
	* Business(comp3350.GoCart.tests.business)
	* Objects(comp3350.GoCart.tests.objects)

### Layers
| Presentation/UI     |      Logic/Business   	  	 |  Persistence/Data   		       |
|---------------------|------------------------------|---------------------------------|
| Home page           | Access nearby stores	  	 | OrderPersistenceHSQLDB 	       |
| Find store by name  | Access Srores by name  	  	 | ProductPersistenceHSQLDB        |
| Find nearby stores  | Store Distance calculator	 | StorePersistenceHSQLDB	       |
| Error Messages 	  | location Distance calculator | StoreProductPersistenceHSQLDB   |
| find products	      | Access products by name		 | UserPersistenceHSQLDB           |
| find products	      | Access Orders			     | OrderLinePersistenceHSQLDB      |
| find products	      | Access OrderLineItem		 | 	 PersistenceException          |
| Shopping cart       | Access users			     |                          	   |
| User activity       | Shopping cart			     |                                 |
| login/sign up	      |          					 |                                 |
| Category            |                              |                                 |
| get Deals           |                              |                                 |



### Diagram of Layers
```mermaid
classDiagram
    Main <|--|> objects
    Main <|--|> persistence 
	
    Main <|--|> business

    Main <|--|> presentation

    business --|> presentation
    persistence --|> business
    objects --|> persistence
    class Main{
    }

 class business{
    AccessOrderLines
    AccessOrders
	AccessProducts
	AccessStrores
    AccessStroreProduct
	DistanceCalculator
	DistaanceCalculatorAPIs
	DistanceCalculatorRandom
    ShoppingCart



}

class objects{
EmptyStore
Product
Store
StoreProduct
Order
OrderLineItem
User
}

class persistence{
    OrderLinePersistence
    OrderPersistence
ProductPersistence
StorePersistence
StoreProductPersistence
UserPersistence

}

class presentation{
ClosestStoreRecViewAdapter
DealsActivity
enterHomeAddress
FindClosetStoreActivity
FindStoreActivity
FindStoreByNameActivity
HomeActivity
Messages
ProductActivity
ProductsRecViewAdapter
ShoppingCartActivity
ShoppingCartAdapter
StoresRecViewAdapter
UserActivty

}



    DSO <|-- Store
    DSO <|-- Product
    DSO <|-- StoreProduct
    DSO <|-- Order
    DSO <|-- OrderLineItem
    DSO <|-- User
    DSO <|-- EmptyStore

class DSO{  
  }
class Product{
String productName
BigDecimal productPrice
boolean peanutAllergy
Product(newProductName, newProductPrice, boolean peanutAllergy)
String getProductName()
BigDecimal getProductPrice()
hasPeanutAllergy()
boolean equals(Object other)
toString()
getProductCAtegory()
ProductBuilder()

}
class Store{
String storeName
String storeAddress
double distToUser
public Store(final String newStoreName, final String newStoreAddress)
String getStoreName()
String getStoreAddress()
boolean equals(Object other) 
setDistToUser(double dist)
getDistToUser()
compareTo(@NonNull Store other) 
toString()
storeBuilder()
}
class StoreProduct{
Store store
Product product
BigDecimal price
public StoreProduct(final Store store, final ProyproducNametString storeAddressmal price)String getStoreId()
Store getStore()
String getProductID()
String getProductName()
BigDecimal getPrice()

}
class Order{
int orderID
int customerID
int storeID
public Order(int oID, int cID, int sID)String getStoreName()
int getOrderID()
int getCustomerID()setDistToUser(double dist)
boolean equal
OrderBuilder()
}
class OrderLineItem{
int orderID
int productID
BigDecimal price
public OrderLineItem(int orderID, int productID, BigDecimal price)int getOrderID()
int getOrderID()
int getProductID()
BigDecimal getPrice()
}
class User{
String firstName
String lastName
String address
String city
String province
String zipCode
int phone
String email
String password
public User(String firstName,String lastName,String address,String city,String province,String zipCode,int phone,String email,String password)
boolean verifyUser(String email, String password)
String getInitials()
UserBuilder()
}
```
### Tier 1 Presentation / User Interface
>This layer generates what the user sees and interacts with. There are currently 13 classes in the presentation layer, which implements our the UI of our app the features "find stores","user accounts","find products", "get cheapest order", "get deals" and "product category" features. Our UI implements these features by creating a tandem with activities and communicating with the logic layer to present it to the user.


### Tier 2 Business / Logic
>This layer perfroms the logic to find the store either by searching by name or by distance(nearest first).
#### AccessOrders
>This class is used to access the orders from the persistence layer to work the logic.
#### AccessProducts
>This class is used to access the products from the persistence layer to work the logic.
#### AccessStoreProduct
>This class is used to access the products of a Store from the persistence layer to work the logic.
#### AccessStores
>This class is used to access the Store from the persistence layer to work the logic.
#### AccessOrderlineItem
>This class is used to access the OrderLineItem from the persistence layer to work the logic.
#### CalculateCheapestStore
>This class is used to implement the user story "find the cheapest order" by finding the cheapest order accross all stores.
#### DistanceCalculator
>This class is used to calculate the distance from the users address to find the nearest stores from their address.
#### DistanceCalculatorAPI
>This class is uses a google API to calculate the distance from the users address to find the nearest stores from their address.
#### ShoppingCart
>This class is used to implement the logic for shooping cart to manage orders from the persistence layer.


## Tier 3 Data/ Persistence
>This layer has the HSQLDB database for all the objects respectively. The app runs database.script in assets directory to implement a HSQLDB that is ussed to handle our 


