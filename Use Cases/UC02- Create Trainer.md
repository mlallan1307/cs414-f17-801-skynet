UC02- Create Trainer
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**  

- Manager: Wants to easily add a new trainer's personal and work information to the system for future use.
- Trainer: Wants to provide only the information that is needed and receive a login account.

**Preconditions:** UC00 or UC01 has been completed and system knows the current user is a Manager.  
**Success Guarantee:** New trainer information is saved and user account created.  
**Main Success Scenario:**

1. Manager selects to create new trainer on system.
2. System displays new trainer digital form with fields including: First name, Last name, address, phone, email, Drivers license number, health insurance provider name, work hours, qualifications, username, and password.
3. Trainer presents manager with required personal and work information.
4. Manager enters new trainer data into a digital form and gives the trainer an inital password to use.
5. Manager submits form.
6. System validates that all required fields are populated.
7. System validates that new trainer's Drivers license number is not already associated with an existing trainer.
8. System validates that the desired username is not already in use.
9. System saves new trainer with the given information.
10. System notifies manager that the new trainer has been added.

**Extensions:**

*a. At any time, Manager is able to cancel operation.

1. Manager selects a cancel button.
2. System returns to Manager UI main page.

6a. Required fields are empty.

1. System notifies manager what required fields must be completed.

7a. Driver's license number is already associated with a trainer.

1. System notifies manager that this trainer already exists and asks if the manager would like to go to **UC03**.

 1a. If yes, switch to **UC03**.

 1b. If no, dismiss notification.

8a. Desired username is in use.

1. System notifies manager that the username is already in use.

**Special Requirements:**

- Manager UI main page has button to create new trainer.

**Technology and Data Variations List:**

9a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** A couple times per month