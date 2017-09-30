UC08- Create Excercise
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to create an excercise which may involve using equipment.

**Preconditions:** UC01 has been completed and system knows the current user is a Trainer.   
**Success Guarantee:** A new excercise is saved on system.   
**Main Success Scenario:**

1.	Trainer selects to create new excercise.
2.	System displays a digital form containing fields for excercise name, duration (time), number of sets, repetitions per set, and an option to select equipment.
3.	Trainer enters excercise name.
4.	Trainer enters the duration, or they can enter the number of sets and repetitions per set
5.	Trainer may select a piece of equipment using **UC07.1**.
6.	Trainer selects add on the system when done.
7. System validates that the excercise name is not empty.
8. System validates that the duration field is filled or one of the set fields are filled but not both.
9. System validates that the given excercise name is not associated with another excercise.
10. System saves new excercise.
11. System notifies trainer that new excercise has been created.

**Extensions:**

*a. At any time, trainer is able to cancel operation.

1. Trainer selects a cancel button.
2. System returns to Trainer UI main page.

7a. Excercise name is empty.

1.	System notifies trainer that the excercise name can't be empty.

8a. Trainer enters value for duration and one or both of the set fields.

1. System notifies trainer that either the duration field or the set fields should be populated (but not both).

8b. Trainer does not enter a value for duration or either of the set fields.

1. System notifies trainer that either the duration field or the set fields should be populated (but not both).

8c. Trainer does not enter a value for duration but enters a value in only one of the two set fields.

1. System notifies trainer that only one of the two set fields is populated and that both need to be populated.

9a. Excercise name is in use.

1.	System notifies trainer that this excercise name is in use.

**Special Requirements:**

- Trainer UI main page has button to create an excercise.

**Technology and Data Variations List:**

10a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Daily
