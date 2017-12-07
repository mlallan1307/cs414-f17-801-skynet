Refactoring List
=======================

----------

###1. Package Changes

Reorganized most packages in order to provide better class grouping and avoid domain specific logic in other packages. Some packages that only contained one class were grouped differently (Customer, Manager, Trainer). Data about people was separated into the data package and its sub-packages. 

Original Package Structure

 * Domain
	 * Control
	 * Data
		 * Equipment
		 * Exercise
		 * Routine
	 * People
		 * Customer
		 * Info
		 * Manager
		 * Trainer
 * UI
	 * Swing
		 * Manager
		 * Trainer
 * Utility
	 * Data
	 * File


New Package Structure

* UI
	* Swing
		* Common
		* Start
		* Manager
		* Trainer
* Domain
	* Control
	* Utilities
	* Data
		* Objects
		* People
	* People
		* User
		* Other
* Services
	* Store

----------

###2. Code Refactoring

* Moved functionality of (class)ToString in UI classes into their respective base classes via a toStringShort method.
* Split UI "select" functionality/framework to abstract classes as the selects are meant to be utility use cases that may be used by other UI classes in the future. Some selects only derived by one class presently but giving all select UI classes an Abstract parent provides consistency.
* CustomerAbs, EquipmentAbs, ExerciseAbs, RoutineAbs, TrainerAbs were all created to minimize code duplication of their respective Create and Modify UI classes. Each abstract class has two derived classes which are primarily responsible for implementing a submitPressed method to define what to do with the form on submission. Since the create and modify forms are so similar, these abstract classes define all the fields and the layout (Lower coupling).
* Created AccountManager class in domain.utilities so that the UI classes are not responsible for checking all user types (Manager & Trainer) when validating login information, recovering username, or resetting password. Using a new UserType enum, it can return the account type if the login information is correct (Lower coupling).
	* Login and recover username were removed from the Manager and Trainer controllers.
* A class designed for system initialization was created, InitializeSystem. This class calls all the controller initialize methods which were called by the UI launcher.
* Split the create* functionality into build* and add*, which build the object and add the object to the static list (respectively). Updated UI methods that used this functionality. This change allows the long parameter list to be in the build* method and taken out of the create* and replace* methods (In the controllers), as well as reducing code duplication.

----------

###3. Other Changes

* Project converted to Maven for easier dependency resolution and the ability to run CircleCI integrated builds and testing
	* Moved test suites to a new project src root that starts with "test" live maven expects and updated main test suite name to end in "Test" so that it will be run by CircleCI
* Created enum classes for UserType and ExerciseType