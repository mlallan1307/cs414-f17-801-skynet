UC10- Create Routine
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to create a workout routine which may involve using equipment from the inventory.

**Preconditions:** UC01 has been completed and system knows the current user is a Trainer.   
**Success Guarantee:** A new routine is saved on system.   
**Main Success Scenario:**

1.	Trainer selects to create routine on system.
2.	System displays a digital form containing a field for routine name, an option to add existing exercises, and an option create new exercise.
3.	Trainer enters routine name on form.

 *Trainer can skip to step 7 if they only want to add existing exercises.*

4.	Trainer selects to create new exercise.
5. Trainer creates exercise using **UC08**
6. System returns to show the list of exercises in routine and gives the option to add more.

 *Trainer repeats steps 4-6 until all needed new exercises are added to routine.*
 
7. Trainer selects to add an existing exercise.
8. Trainer selects an existing exercise using **UC09.1**
9. System adds the selected exercise to the routine.
10. System returns to show the list of exercises in routine and gives the option to add more.

 *Trainer repeats steps 7-10 until all needed existing exercises are added to routine.*
 
 *Trainer can return to step 4 to add new exercises.*

11. Trainer selects to save routine on system.
12. System validates that the routine name field is not empty.
13. System validates that the routine name is not associated with another routine.
14. System saves new routine with all the entered information.
15. System displays that the routine has been added.

**Extensions:**

*a. At any time, Trainer is able to cancel operation.

1. Trainer selects a cancel button.
2. System returns to Trainer UI main page.

8a. Selection is not made.

1. Remain at step 8 or trainer can choose to go to step 4 or 11.

12a. Routine name field is empty.

1. System notifies trainer that the routine name can't be empty.

13a. Routine name is in use.

1. System notifies trainer that the routine name is in use.

**Special Requirements:**

- Trainer UI main page has button to create routine.

**Technology and Data Variations List:**

14a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Daily
