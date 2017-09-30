UC09- Modify Excercise
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to modify an existing excercise which may involve using equipment.

**Preconditions:** UC01 has been completed and system knows the current user is a Trainer.   
**Success Guarantee:** An existing excercise is modified and saved on system.   
**Main Success Scenario:**

1.	Trainer selects to modify excercise.
2. Trainer selects excercise for system using **UC09.1**
3.	System displays a digital form (populated by selected excercise) containing fields for excercise name, duration (time), number of sets, repetitions per set, and an option to select equipment.
4.	Trainer may modify excercise name.
5.	Trainer may enter the duration, or they may enter the number of sets and repetitions per set
6.	Trainer may select a piece of equipment using **UC07.1**.
7.	Trainer submits form when done.
8. System validates that the excercise name is not empty.
9. System validates that the duration field is filled or one of the set fields are filled but not both.
10. System validates that the given excercise name is not associated with another excercise.
11. System updates and saves existing excercise.
12. System notifies trainer that excercise has been modified.

**Extensions:**

*a. At any time, trainer is able to cancel operation.

1. Trainer selects a cancel button.
2. System returns to Trainer UI main page.

2a. Selection is not made.

1. Remain at step 2.

8a. Excercise name is empty.

1.	System notifies trainer that the excercise name can't be empty.

9a. Trainer enters value for duration and one or both of the set fields.

1. System notifies trainer that either the duration field or the set fields should be populated (but not both).

9b. Trainer does not enter a value for duration or either of the set fields.

1. System notifies trainer that either the duration field or the set fields should be populated (but not both).

9c. Trainer does not enter a value for duration but enters a value in only one of the two set fields.

1. System notifies trainer that only one of the two set fields is populated and that both need to be populated.

10a. Excercise name is in use.

1.	System notifies trainer that this excercise name is in use.

**Special Requirements:**

- Trainer UI main page has button to create an excercise.

**Technology and Data Variations List:**

11a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Daily
