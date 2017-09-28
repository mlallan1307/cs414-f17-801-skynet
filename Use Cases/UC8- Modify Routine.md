UC8- Modify Routine
-------------------------------------

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to modify workout routine data and remove routines.

**Preconditions:** None  
**Success Guarantee:** An existing routine modified and saved.  
**Main Success Scenario:**

1.	Trainer selects to modify a routine on system.
2.	System fetches and displays the list of routines.
3.	Trainer selects a routine from the list.
4.	System displays a digital form containing routine name, workout names, and option to add workout.
5.	Trainer modifies routine name, if desired.

 *If Trainer only needs to add workouts to routine, skip to step 16.*
 
6.	Trainer selects to modify a workout.
7.	System displays a digital form (populated with selected workout data) containing fields for workout name, duration (time), number of sets, repetitions per set, and a list of the equipment in the inventory.
8.	Trainer edits workout name, if desired.
9.	Trainer edits the duration, or they can edit the number of sets and repetitions per set, if desired.
10. Trainer selects or changes a piece of equipment from the inventory, if needed.
11. Trainer selects save on the system when done.
12. System validates that the workout name is not empty.
13. System validates that the duration field is filled or one of the set fields are filled but not both.
14. System saves the updated workout.
15. System returns to show the list of workouts in routine and gives the option to add more.

 *Trainer repeats steps 6-15 until all workout modifications are complete*
 
16. Trainer selects to add workout, if desired.
17. System displays a digital form containing fields for workout name, duration (time), number of sets, repetitions per set, and a list of the equipment in the inventory.
18. Trainer enters workout name.
19. Trainer enters the duration, or they can enter the number of sets and repetitions per set
20. Trainer selects a piece of equipment from the inventory if needed.
21. Trainer selects add on the system.
22. System validates that the workout name is not empty.
23. System validates that the duration field is filled or one of the set fields are filled but not both.
24. System saves the new workout and associates it with the routine.
25. System returns to show the list of workouts added and gives the option to add more.

 *Trainer repeats steps 16-25 until all needed workouts are added to routine*
 
26. Trainer selects to save routine on system.
27. System validates that the routine name field is not empty.
28. System updates routine name on the modified routine and saves it.
29. System displays that the routine has been updated.

**Extensions:**

12,22a. Workout name is empty.

1.	System notifies trainer that the workout name can't be empty.

13,23a. Trainer enters value for duration and one or both of the set fields.

1. System notifies trainer that either the duration field or the set fields should be populated (but not both).

13,23b. Trainer does not enter a value for duration or either of the set fields.

1. System notifies trainer that either the duration field or the set fields should be populated (but not both).

13,23c. Trainer does not enter a value for duration but enters a value in only one of the two set fields.

1. System notifies trainer that only one of the two set fields is populated and that both need to be populated.

27a. Routine name field is empty.

1. System notifies trainer that the routine name can't be empty.

**Special Requirements:**

- UI has button to modify a workout routine

**Technology and Data Variations List:**

14,24,28a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Daily
