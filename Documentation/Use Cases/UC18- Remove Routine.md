UC18- Remove Routine
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer
**Stakeholders and Interests:**

- Trainer: Wants to remove a routine from the system.

**Preconditions:** UC02 and UC01 has been completed and system knows the current user is a Trainer.  
**Success Guarantee:** The routine is removed from the system.
**Main Success Scenario:**

1.	Trainer selects to modify routine on system.
2.	Trainer selects routine for system using **UC11.1**
3.	Trainer selects the Remove button.
4.	System displays a confirmation to remove the selected routine.
5.	Trainer selects yes.
6. System removes the routine from any customers that were assigned to this routine.
7. System removes the routine entry and saves the change.
8. System updates the list of routines to reflect the removal of the routine.

**Extensions:**

*a. At any time, Trainer is able to cancel operation.

1. Trainer selects close on the popup.
2. System does not remove routine.

5a. Trainer selects no or close on the window.

1.	Confirmation is dismissed and routine is not removed.

**Special Requirements:**

- UI has remove button on select page

**Technology and Data Variations List:** None  
**Frequency of Occurrence:** Monthly
