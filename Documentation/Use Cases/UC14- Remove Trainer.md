UC14- Remove Trainer
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager
**Stakeholders and Interests:**

- Manager: Wants to remove a trainer from the system.

**Preconditions:** UC00 or UC01 has been completed and system knows the current user is a Manager.  
**Success Guarantee:** The trainer entry is removed from the system.
**Main Success Scenario:**

1.	Manager selects to modify trainer on system.
2.	Manager selects trainer for system using **UC03.1**
3.	Manager selects the Remove button.
4.	System displays a confirmation to remove the selected trainer.
5.	Manager selects yes.
6. System removes the trainer entry and saves the change.
7. System updates the list of trainers to reflect the removal of the trainer.

**Extensions:**

*a. At any time, manager is able to cancel operation.

1. Manager selects close on the popup.
2. System does not remove trainer.

5a. Manager selects no or close on the window.

1.	Confirmation is dismissed and trainer is not removed.

**Special Requirements:**

- UI has remove button on select page

**Technology and Data Variations List:** None  
**Frequency of Occurrence:** Monthly
