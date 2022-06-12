# Flat Manager

The project should be a self work, which should implement the basic concepts of interface, inheritance, classes etc. So the programm should be a small flat management project, which **adds, removes, lists, counts the flats inthere and other functionalities.** 

### 1. Abstract Class Flat
The abstract class Apartment is used to store information about the apartments of a property manager. Among other things, the following data should be recorded as private instance variables and the necessary public access methods (set... and get...) should be created:

• Id (property number - number, unique but not necessarily consecutive)

• Surface

• Number of rooms

• Floor (0 = ground floor)

• Construction year

• Address (zip code, street, house number, top)

A constructor is to be implemented that makes it possible to set the corresponding instance variables directly. Check whether these are plausible values. (e.g. the year of manufacture must not be in the future, etc.)
If conditions are not met, throw an IllegalArgumentException with a default error message (see point 7).
The method age() should calculate the age of an apartment.
In addition, there should be a totalCosts() method that calculates the monthly
Total cost of an apartment calculated (see point 2).

### 2. Class condominium and rental apartment
Two concrete subclasses Condominium and RentalApartment are to be derived from the abstract class Apartment. The condominium class has additional private instance variables for the operating costs (per m2) and the contribution to the repair reserve (per m2). The RentApartment class has additional instance variables for the monthly rental costs (per m2) and the number of tenants in an apartment.
The monthly total costs result from the monthly operating costs and the repair reserve plus a surcharge for condominiums and from the monthly rental costs plus a surcharge for rented apartments. In the case of a condominium, the monthly surcharge is calculated depending on the floor (a surcharge of 2% per floor; no surcharge on the ground floor). In the case of a rented apartment, the surcharge is calculated depending on the number of tenants - no surcharge for one tenant, 2.5% for each additional tenant - whereby the maximum possible surcharge is 10%.
The toString() method (inherited from Object) should be overwritten so that all apartment data is returned as a string according to the specified format (see point 8).

### 3. Interface property managementDAO
This interface defines methods for accessing the apartment data independently of the specific implementation of persistent data storage. (cf. Data Access Object).
The HausverwaltungDAO interface contains abstract methods for reading and saving apartment objects.
  - The getApartments() method returns all persisted apartment objects as a java.util.List.
  - The getApartmentbyId(int ...) method returns an apartment object based on the apartment number. If the apartment is not found,
should return null.
  - The method saveApartment(Apartment ...) should save an apartment object persistently. Make sure that when saving a new apartment, the id of an already saved apartment is not used. In this case, throw an IllegalArgumentException with an appropriate error message (see point 7).
  - The deleteApartment(int ...) method should delete an apartment from the persistent data source. If the apartment does not exist, an IllegalArgumentException should be thrown with a corresponding error message (see point 7).

### 4. Class Property ManagementSerializationDAO
The HausverwaltungSerializationDAO class implements the interface Property ManagementDAO. Implement the persistent storage of the apartment data in a file
using Java Object Serialization. A string with the name of the file should be passed to the class in the constructor.
In the event of errors from file operations, a one-line error message starting with the string
  - "Serialization failed:" or
  - "Deserialization failed:"
starts to be output and the program can be terminated with System.exit(1). 

### 5. Class Housekeeping
The property management class should implement the **business logic**. The class should have a private instance variable hausverwaltungDAO (of type HausverwaltungDAO) in order to be able to access the apartment data.
Methods that realize the following functionality.
  - Provide all data of all apartments
  - Provide all data of an apartment
  - Add new apartment
  - Delete existing apartment
  - Determine the total number of all apartments/condominiums/rental apartments
  - Determine the average monthly total costs of all apartments
  - Find out the ID(s) of the oldest apartment(s).


### 6. Frontend
Java program Property ManagementClient that provides the command line interface described below using the Property Management class.
The program supports calls in the following format:

<img width="618" alt="Screenshot 2022-06-12 at 13 25 47" src="https://user-images.githubusercontent.com/84074078/173230857-8bd1a672-4cc4-41ad-8020-fc8e8fb8ae74.png">

   * 'Datei': name of the file for serialization. If the file does not exist, it should be created.
   * 'Parameter': list, add, delete, count, meancosts, oldest. Only one of these parameters can be specified per call.
        #### 'list' parameters
  
<img width="805" alt="Screenshot 2022-06-12 at 13 12 44" src="https://user-images.githubusercontent.com/84074078/173230335-5f22c304-164d-4504-9e6b-033d8a94c6a3.png">

  
  #### 'list <id>' parameter
  
  <img width="587" alt="Screenshot 2022-06-12 at 13 14 39" src="https://user-images.githubusercontent.com/84074078/173230373-119cd7b7-108e-4db7-970a-de20d8c2e960.png">
      
  
  #### 'add EW <id> <flaeche> <zimmer> <stock> <baujahr> <plz><strasse> <hausnummer> <top> <betriebskosten/m2> <ruecklage/m2>' parameter
  
   <img width="806" alt="Screenshot 2022-06-12 at 13 15 31" src="https://user-images.githubusercontent.com/84074078/173230411-4a5f642c-7977-44fe-a669-dc9342b4e91b.png">
        
       
  #### 'add MW <id> <flaeche> <zimmer> <stock> <baujahr> <plz> <strasse> <hausnummer> <top> <miete/m2> <mieter>' parameter
  <img width="805" alt="Screenshot 2022-06-12 at 13 16 30" src="https://user-images.githubusercontent.com/84074078/173230481-8fe7de3a-42cf-48f9-a2dc-533cf255a423.png">

        
  #### 'delete <id>' parameter
  <img width="808" alt="Screenshot 2022-06-12 at 13 16 45" src="https://user-images.githubusercontent.com/84074078/173230489-193e4c75-8369-4e3a-92d1-8bd1668a96a7.png">
    
        
  #### 'count' parameter
  <img width="808" alt="Screenshot 2022-06-12 at 13 17 35" src="https://user-images.githubusercontent.com/84074078/173230511-faf96cf8-2650-460c-8937-69aa186abf44.png">

        
  #### 'count <type>' parameter
  <img width="806" alt="Screenshot 2022-06-12 at 13 18 20" src="https://user-images.githubusercontent.com/84074078/173230550-0555781d-7fc1-4b6c-9d3a-85598e4ddf97.png">

        
  #### ' meancosts' parameter
  <img width="803" alt="Screenshot 2022-06-12 at 13 18 44" src="https://user-images.githubusercontent.com/84074078/173230569-52d8e192-9f49-4cff-a9cd-b293841c6574.png">

       
  #### 'oldest' parameter
  <img width="802" alt="Screenshot 2022-06-12 at 13 18 49" src="https://user-images.githubusercontent.com/84074078/173230579-87503b95-1e98-4a04-ae8f-26307bec348b.png">

### 7. Error Messages
All exceptions thrown due to invalid or incorrect input must be caught and the program terminated with an error message. Only one of the following error messages may be output:
  - "Error: Invalid parameter."
  - "Error: Invalid year of manufacture."
  - "Error: Apartment already exists. (id=<id>)"
  - "Error: Apartment not available. (id=<id>)"
<img width="807" alt="Screenshot 2022-06-12 at 13 21 07" src="https://user-images.githubusercontent.com/84074078/173230690-ab0cfc28-4f2c-4858-bc76-719bc4f44bd2.png">
  
<img width="809" alt="Screenshot 2022-06-12 at 13 21 16" src="https://user-images.githubusercontent.com/84074078/173230691-1d059735-41a5-477b-8e1d-7b8ff60bdffe.png">

  
