UC07- Create Routine
---------------------------------

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to create a workout routine which may involve using equipment from the inventory.

**Preconditions:** None  
**Success Guarantee:** A new routine is saved on system.  
**Main Success Scenario:**

1.	Trainer selects to create routine on system.
2.	System displays a digital form containing a field for routine name and an option to add a workout to routine.
3.	Trainer enters routine name on form.
4.	Trainer selects to add workout.
5.	System displays a digital form containing fields for workout name, duration (time), number of sets, repetitions per set, and a list of the equipment in the inventory.
6.	Trainer enters workout name.
7.	Trainer enters the duration, or they can enter the number of sets and repetitions per set
8.	Trainer selects a piece of equipment from the inventory if needed.
9.	Trainer selects add on the system when done.
10. System validates that the workout name is not empty.
11. System validates that the duration field is filled or one of the set fields are filled but not both.
12. System returns to show the list of workouts added and gives the option to add more.

 *Trainer repeats steps 4-12 until all needed workouts are added to routine.*

13. Trainer selects to save routine on system.
14. System validates that the routine name field is not empty.
15. System validates that the routine name is not associated with another routine.
16. System saves new routine with all the entered information.
17. System displays that the routine has been added.

**Extensions:**

10a. Workout name is empty

1.	System notifies trainer that the workout name can't be empty

11a. Trainer enters value for duration and one or both of the set fields.

1. System notifies trainer that either the duration field or the set fields should be populated (but not both).

11b. Trainer does not enter a value for duration or either of the set fields.

1. System notifies trainer that either the duration field or the set fields should be populated (but not both).

11c. Trainer does not enter a value for duration but enters a value in only one of the two set fields.

1. System notifies trainer that only one of the two set fields is populated and that both need to be populated.

14a. Routine name field is empty

1. System notifies trainer that the routine name can't be empty

15a. Routine name is not associated with another routine

1. System notifies trainer that the routine name is associated with another routine.

**Special Requirements:**

- UI has button to create a workout routine

**Technology and Data Variations List:**

16a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Daily
