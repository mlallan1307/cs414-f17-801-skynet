Domain Model Glossary
=======================

## Concepts

 1. Address
 2. Customer
 3. Equipment
 4. Exercise
 5. Exercise-Type
 6. Health-Insurance
 7. Manager
 8. Membership
 9. Person-Information
 10. Qualification
 11. Routine
 12. Schedule
 13. Set-Based-Exercise
 14. Time-Based-Exercise
 15. Time-Period
 16. Trainer
 17. User


----------

### 1. Address

An address entry contains the information necessary to identify a physical location. An address is associated with a person in the system.
 
 * street1: Street address, P.O. box, or company name
 * street2: Apartment, suite, unit, building, floor, etc.
 * provOrState: State or Provence of address
 * city: City identification
 * zipCode: Zip or Postal code
 * type: Identification of home, work, or other

### 2. Customer

Customers first have their information entered into the system by a manager,  which can be modified at a later time by the manager. They have a membership associated with their account. They do not have a login for the system. Customers can be assigned to workout routines by a trainer.

### 3. Equipment

Equipment may be something such as a workout machine and has a name, picture, and quantity associated with it. The manager is able to create an equipment entry in the system and modify the entry. Exercises can use equipment.

 * name: What this equipment is called
 * picture: Picture of the equipment
 * quantity: How many there are

### 4. Exercise

An exercise has a name and can be part of zero or more workout routines. A trainer creates an exercise and can be modified by any trainer. An exercise may or may not use one piece of equipment. It can be either a time based exercise or a set based exercise. 

 * name: What this exercise is called

### 5. Exercise-Type

An exercise has exactly one type of exercise associated with it. This concept is implemented as either a time-based exercise or a set-based exercise. 

### 6. Health-Insurance

A health insurance provider is associated with a person in the system. A person's health insurance provider information currently contains the name of the provider but may be expanded later.

 * providerName: The name of the health insurance provider

### 7. Manager

The manager initializes the system when it is first activated in order to create the first user account for herself. They hire trainers and use the system to create their user accounts. They enter the information of new customers into the system. They create the system entry for inventory equipment. Managers are also able to modify the system entry for trainers, customers, and equipment.

### 8. Membership

Customers have membership information associated with them. Membership information contains status to indicate if their membership is active or inactive. Membership may be expanded later to include different types of membership or other information.

 * status: Indicates the current status of the membership such as active or inactive

### 9. Person-Information

A person in the system has information associated with them. This information includes: name, drivers license, phone, email, address, and health insurance. There is one entry per customer and system user (Trainer or Manager).

 * firstName: Person's first name
 * lastName: Person's last name
 * driversLicenseNumber: The drivers license number of the person
 * phone: The person's phone number 
 * email: An email for the person

### 10. Qualification

Trainers have zero or more qualifications associated with them. A qualification is typically defined as experience related to the trainer's job. A qualification has a description, but may be expanded to include more information later.

 * description: A text description of what the qualification is

### 11. Routine

Routines have a name and zero or more exercises. They are created by a trainer and can be modified by any trainer. A routine can be assigned to zero or more customers.

 * name: What this routine is called
 
### 12. Schedule

This can contain a collection of time periods that comprise a schedule. This is used in association with a Trainer to specify their work schedule.

### 13. Set-Based-Exercise

Set-based exercise information may be associated with an exercise entry. This contains the number of sets and the number of repetitions per set to perform.

 * numberOfSets: How many sets should be performed
 * numberOfRepetitions: How many repetitions of the exercise should be performed in each set

### 14. Time-Based-Exercise

Time-based exercise information may be associated with an exercise entry. This contains the duration of the exercise to be performed. 

 * duration: The duration of time the exercise should be performed such as how many minutes or seconds

### 15. Time-Period

This contains an identification of a period of time using day of the week, start and stop time. If the start and stop days are the same, then the stop time must be after the start time.

 * startDay: Starting day Sunday through Saturday
 * startTime: A time of day 00:00-23:59
 * stopDay: Stopping day Sunday through Saturday
 * stopTime: A time of day 00:00-23:59

### 16. Trainer

Trainers are hired by the manager, and they each have a user account on the system. They have qualifications and working hours associated with their account. Trainers can create, modify, and assign workout routines. They also have the ability to create and modify exercises.

### 17. User

A user is a person who has access to the system via a user account. A user has a username, password, and user type. They also have Person-Information associated with their account. A manager is the first user. The manager creates and can modify the user information for trainers. Manager and trainer are the user types currently recognized. Users have the ability to login, recover their username, and reset their password.

 * username: The username associated with this user that they will use to login
 * password: The password associated with this user that they will use to login
 * userType: The type of user that this is such as Manager or Trainer
