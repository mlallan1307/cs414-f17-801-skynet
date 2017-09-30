UC03- Modify Trainer
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**

- Manager: Wants to modify trainer data.

**Preconditions:** UC00 or UC01 has been completed and system knows the current user is a Manager.  
**Success Guarantee:** Trainer data is modified and saved.  
**Main Success Scenario:**

1.	Manager selects to modify trainer on system.
2.	Manager selects trainer for system using **UC03.1**
3.	System displays the selected trainer's information in a form with available fields populated.
4.	Manager modifies one or more fields on form.
5.	Manager submits changes.
6. System validates that all required fields are populated.
7. System validates that trainer's Drivers license number is not already associated with a different trainer.
8. System validates that the username is not already associated with a different trainer.
9. System saves modified trainer.
10. System notifies manager that the update is complete.

**Extensions:**

*a. At any time, Manager is able to cancel operation.

1. Manager selects a cancel button.
2. System returns to Manager UI main page.

2a. Selection is not made.

1. Remain at step 2.

6a. Required fields are empty.

1. System notifies manager what required fields must be completed.

7a. Drivers license number is associated with a different trainer.

1.	System notifies manager that this Drivers license number is already associated with another trainer.

8a. Username is associated with a different trainer.

1.	System notifies manager that this username is already in use.

**Special Requirements:**

- Manager UI main page has button to modify trainer.
- Manager can not see or modify the trainer's password.

**Technology and Data Variations List:**

9a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** A couple times per month
