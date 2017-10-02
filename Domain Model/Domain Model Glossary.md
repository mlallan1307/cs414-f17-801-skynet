Domain Model Glossary
=======================

## Concepts

 1. Manager
 2. Trainer
 3. Customer
 4. Routine
 5. Exercise
 6. Equipment
 7. Addess
 8. Health-Insurance
 9. Person-Information
 10. User
 11. Qualification
 12. Membership
 13. Exercise-Type-Abs
 14. Time-Based-Exercise
 15. Set-Based-Exercise

----------

### 1. Manager

The manager initializes the system when it is first activated in order to create the first user account for herself. They hire trainers and use the system to create their user accounts. They enter the information of new customers into the system. They create the system entry for inventory equipment. Managers are also able to modify the system entry for trainers, customers, and equipment.

### 2. Trainer

Trainers are hired by the manager, and they each have a user account on the system. They have qualifications and working hours associated with their account. Trainers can create, modify, and assign workout routines. They also have the ability to create and modify exercises.

### 3. Customer

Customers first have their information entered into the system by a manager,  which can be modified at a later time by the manager. They have a membership associated with their account. They do not have a login for the system. Customers can be assigned to workout routines by a trainer.

### 4. Routine

Routines have a name and zero or more exercises. They are created by a trainer and can be modified by any trainer. A routine can be assigned to zero or more customers.

### 5. Exercise

An exercise has a name and can be part of zero or more workout routines. A trainer creates an exercise and can be modified by any trainer. An exercise may or may not use one piece of equipment. It can be either a time based exercise or a set based exercise. 

### 6. Equipment

Equipment may be something such as a workout machine and has a name, picture, and quantity associated with it. The manager is able to create an equipment entry in the system and modify the entry. Exercises can use equipment.

### 7. Address

An address entry contains the information necessary to identify a physical location. An address is associated with a person in the system.

### 8. Health-Insurance

A health insurance provider is associated with a person in the system. A person's health insurance provider information currently contains the name of the provider but may be expanded later.

### 9. Person-Information

A person in the system has information associated with them. This information includes: name, drivers license, phone, email, address, and health insurance. There is one entry per customer and system user (Trainer or Manager).

### 10. User

A user is a person who has access to the system via a user account. A user has a username, password, and user type. They also have Person-Information associated with their account. A manager is the first user. The manager creates and can modify the user information for trainers. Manager and trainer are the user types currently recognized. Users have the ability to login, recover their username, and reset their password.

### 11. Qualification

Trainers have zero or more qualifications associated with them. A qualification is typically defined as experience related to the trainer's job. A qualification has a description, but may be expanded to include more information later.

### 12. Membership

Customers have membership information associated with them. Membership information contains status to indicate if their membership is active or inactive. Membership may be expanded later to include different types of membership or other information.

### 13. Exercise-Type-Abs

An exercise has exactly one type of exercise associated with it. This abstract concept is implemented as either a time-based exercise or a set-based exercise. 

### 14. Time-Based-Exercise

Time-based exercise information may be associated with an exercise entry. This contains the duration of the exercise to be performed. 

### 15. Set-Based-Exercise

Set-based exercise information may be associated with an exercise entry. This contains the number of sets and the number of repetitions per set to perform.