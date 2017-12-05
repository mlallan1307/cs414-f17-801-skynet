UC17- Remove Exercise
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer
**Stakeholders and Interests:**

- Trainer: Wants to remove an exercise from the system.

**Preconditions:** UC02 and UC01 has been completed and system knows the current user is a Trainer.  
**Success Guarantee:** The exercise is removed from the system.
**Main Success Scenario:**

1.	Trainer selects to modify exercise on system.
2.	Trainer selects exercise for system using **UC09.1**
3.	Trainer selects the Remove button.
4.	System displays a confirmation to remove the selected exercise.
5.	Trainer selects yes.
6. System removes the exercise from any routines that contain this exercise.
7. System removes the exercise entry and saves the change.
8. System updates the list of exercises to reflect the removal of the exercise.

**Extensions:**

*a. At any time, Trainer is able to cancel operation.

1. Trainer selects close on the popup.
2. System does not remove exercise.

5a. Trainer selects no or close on the window.

1.	Confirmation is dismissed and exercise is not removed.

**Special Requirements:**

- UI has remove button on select page

**Technology and Data Variations List:** None  
**Frequency of Occurrence:** Monthly
