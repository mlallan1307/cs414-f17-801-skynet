UC1: Hiring of trainers
=======================

**Scope:**"SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**  

- Manager: Wants to easily add a new trainer&#39;s personal and work information to the system for future use.
- Trainer: Wants to provide only the information that is needed.  

**Preconditions:** None
**Success Guarantee:** New trainer information is saved  
**Main Success Scenario:**

1. Manager selects to create new trainer on system.
2. System displays new trainer digital form with fields including: First name, Last name, address, phone, email, Driver's license number, health insurance provider name, work hours, and qualifications.
3. Trainer presents manager with required personal and work information.
4. Manager enters new trainer data into a digital form.
5. Manager submits form.
6. System validates that all required fields are populated.
7. System validates that new trainer&#39;s Driver&#39;s license number is not already associated with an existing trainer.
8. System saves new trainer with the given information.
9. System notifies manager that new trainer has been added.

**Extensions:**

6a. Required fields are empty.

1. System notifies manager what required fields must be completed.

7a. Driver's license number is already associated with a trainer.

1. System notifies manager that this trainer already exists and asks if the manager would like to go to Modification of trainers.

 1a. If yes, switch to Modification of trainers.

 1b. If no, dismiss notification.

**Special Requirements:**

- UI has button to create new trainer.

**Technology and Data Variations List:**  
8a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** A couple times per month