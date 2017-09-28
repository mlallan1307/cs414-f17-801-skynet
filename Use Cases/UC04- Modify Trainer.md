UC04- Modify Trainer
-----------------------------

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**

- Manager: Wants to modify trainer data.

**Preconditions:** None  
**Success Guarantee:** Trainer data is modified and saved.  
**Main Success Scenario:**

1.	Manager selects to modify trainer on system.
2.	System retrieves all trainers names.
3.	System displays a list of all the trainers names for manager.
4.	Manager selects a trainer from list.
5.	System displays the trainer information form with available fields populated.
6.	Manager modifies one or more fields on form.
7.	Manager submits changes.
8.	System validates that all required fields are populated.
9.	System validates that trainer's Driver's license number is not already associated with a different trainer.
10. System saves modified trainer.
11. System notifies manager that the update is complete.

**Extensions:**

2a. No trainers have been entered in the system

1.	Notify manager that there are no trainers

8a. Required fields are empty.

1. System notifies manager what required fields must be completed.

9a. Driver's license number is associated with a different trainer.

1.	System notifies manager that this Driver's license number is already associated with another trainer.

**Special Requirements:**

- UI has button to modify trainer entry

**Technology and Data Variations List:**

10a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** A couple times per month
