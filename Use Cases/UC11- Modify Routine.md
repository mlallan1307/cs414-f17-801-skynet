UC11- Modify Routine
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to modify a workout routine which may involve using equipment from the inventory.

**Preconditions:** UC01 has been completed and system knows the current user is a Trainer.   
**Success Guarantee:** A new routine is saved on system.   
**Main Success Scenario:**

1.	Trainer selects to modify routine on system.
2. Trainer selects a routine for the system using **UC11.1**
3.	System displays a digital form (populated using selected routine) containing a field for routine name, an option to add existing exercise, option create new exercise, and a list of the current exercises.
4.	Trainer may modify routine name on form.

 *Trainer can skip to step 8 if they only want to add existing exercises.*

5.	Trainer selects to create new exercise.
6. Trainer creates exercise using **UC08**
7. System returns to show the list of exercises in routine and gives the option to add more.

 *Trainer repeats steps 5-7 until all needed new exercises are added to routine.*
 
8. Trainer selects to add an existing exercise.
9. Trainer selects an existing exercise using **UC09.1**
10. System adds the selected exercise to the routine.
11. System returns to show the list of exercises in routine and gives the option to add more.

 *Trainer repeats steps 8-11 until all needed existing exercises are added to routine.*
 
 *Trainer can return to step 5 to add new exercises.*

12. Trainer selects to save routine on system.
13. System validates that the routine name field is not empty.
14. System validates that the routine name is not associated with another routine.
15. System updates and saves modified routine.
16. System displays that the routine has been updated.

**Extensions:**

*a. At any time, Trainer is able to cancel operation.

1. Trainer selects a cancel button.
2. System returns to Trainer UI main page.

2a. Selection is not made.

1. Remain at step 2.

9a. Selection is not made.

1. Remain at step 9 or trainer can choose to go to step 5 or 12.

13a. Routine name field is empty.

1. System notifies trainer that the routine name can't be empty.

14a. Routine name is in use.

1. System notifies trainer that the routine name is in use.

**Special Requirements:**

- Trainer UI main page has button to modify routine.

**Technology and Data Variations List:**

15a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Daily
