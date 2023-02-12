# COMP3350 G06 Iteration 1
## GoCart Architecture
Due: February 12th, 2023

### Packages
* Application
* Business(comp3350.GoCart.objects)
* Objects(comp3350.GoCart.objects)
* Persistence(comp3350.GoCart.persistence)
    * stubs
* Presentation(comp3350.GoCart.presentation)
* Test(comp3350.GoCart.test)
	* Business(comp3350.GoCart.tests.business)
	* Objects(ss(comp3350.GoCart.tests.objects)

### Layers
| Presentation/UI     |      Logic/Business   	  	 |  Persistence/Data   |
|---------------------|--------------------------------|----------------------|
| Home page           | Access nearby stores	  	 | ProductStub Database |
| Find store by name  | Access Srores by name  	  	 | StoreStub Database   |
| Find nearby stores  | Store Distance calculator	 | UserStub Database    |
| Checkout page       | location Distance calculator   |                      |
| payment processor   | Access products by name		 |                      |
|                     | Access dietary restricted prod |                      |



### Diagram of Layers
#### Note:
Our class names and their functionalities will be changed to more generic in the next milestone.
```mermaid
classDiagram
   Data-->>
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
	AccessProducts
	AccessStrores
	DistanceCalculator
	DistaanceCalculatorAPIs
	DistanceCalculatorRandom
CartManager


}

class objects{
Product
Store
}

class persistence{
ProductPersistence
StorePersistence
UserPersistence
}

class presentation{
ClosestStoreRecViewAdapter
FindClosetStoreActivity
FindStoreActivity
FindStoreByNameActivity
HomeActivity
StoresRecViewAdapter
UserAccountActivity
CheckoutActivity

}



    DSO <|-- Store
    DSO <|-- Product

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
}
```