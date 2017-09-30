UC00- Initialize System
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**  

- Manager: Wants to be able to log into the system when it is first initialized

**Preconditions:** None  
**Success Guarantee:** Manager login is created and saved.  
**Main Success Scenario:**

1. System is initialized and finds that there are no manager accounts
2. System displays a digital form with fields including: First name, Last name, Drivers license number, desired username, and password.
3. Manager fills out digital form fields with their information.
4. Manager submits form.
5. System validates that all required fields are populated.
6. System saves manager account with the given information.
7. System notifies manager that their account was created.
8. System performs login for new manager.

**Extensions:**

5a. Required fields are empty.

1. System notifies manager what required fields must be completed.

**Special Requirements:** None

**Technology and Data Variations List:**

6a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Once
