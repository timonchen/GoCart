# COMP3350 G06 Iteration 1
## GoCart Architecture
Due: February 10th, 2023

Authors:  Benedict Agupitan, Hridai Mehta, Rishavjot Singh, Ryan Petrillo, Tim Chen

## Layers
| Presentation/UI     |      Logic/Business   	  	 |  Persistence/Data   |
|---------------------|--------------------------------|----------------------|
| Home page           | Access nearby stores	  	 | ProductStub Database |
| Find store by name  | Access Srores by name  	  	 | StoreStub Database   |
| Find nearby stores  | Store Distance calculator	 |                      |
|                     | location Distance calculator   |                      |
|                     | Access products by name		 |                      |
|                     | Access dietary restricted prod |                      |



## Diagram of Layers
```mermaid
classDiagram
    Main <|-- LoginValidator
    Main <|--|> business
    Main <|--|> objects
    Main <|--|> presistence
    Main <|--|> presentation
    DSO <|-- Store
    DSO <|-- Product

    class Main{
    }

 class business{
	+AccessProducts
	+AccessStrores
	+DistanceCalculator
	+DistaanceCalculatorAPI
	+DistanceCalculatorStub
	+CartManager

}

class objects{
+Product
+Store
}

class persistence{
+ProductPresistence
+StorePresistence
}

class presentation{
+ClosestStoreRecViewAdapter
+FindClosetStoreActivity
+FindStoreActivity
+FindStoreByNameActivity
+HomeActivity
+StoresRecViewAdapter
}
 class DSO{
    }

class Product{
 +String productName
+BigDecimal productPrice
+boolean peanutAllergy
+Product(final String newProductName, final BigDecimal newProductPrice, boolean peanutAllergy)
+String getProductName()
+BigDecimal getProductPrice()
+hasPeanutAllergy()
+boolean equals(Object other)
+toString()

}

class Store{
+String storeName
+String storeAddress
+double distToUser
+public Store(final String newStoreName, final String newStoreAddress)
+String getStoreName()
+String getStoreAddress()
+boolean equals(Object other) 
+setDistToUser(double dist)
+getDistToUser()
+compareTo(@NonNull Store other) 
+toString()
}
```