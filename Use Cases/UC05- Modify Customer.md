UC05- Modify Customer
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**

- Manager: Wants to modify customer data.

**Preconditions:** UC00 or UC01 has been completed and system knows the current user is a Manager.  
**Success Guarantee:** Customer data is modified and saved.  
**Main Success Scenario:**

1.	Manager selects to modify customer on system.
2. Manager selects customer for system using **UC05.1**
3.	System displays the selected customer's information in a form with available fields populated.
4.	Manager modifies one or more fields on form.
5.	Manager submits changes.
6.	System validates that all required fields are populated.
7.	System validates that customer's Driver's license number is not already associated with a different customer.
8. System saves modified customer.
9. System notifies manager that the update is complete.

**Extensions:**

*a. At any time, Manager is able to cancel operation.

1. Manager selects a cancel button.
2. System returns to Manager UI main page.

2a. Selection is not made.

1. Remain at step 2.

6a. Required fields are empty.

1.	System notifies manager what required fields must be completed.

7a. Driver's license number is associated with a different customer.

1.	System notifies manager that this Driver's license number is already associated with another customer.

**Special Requirements:**

- UI has button to modify customer entry.

**Technology and Data Variations List:**

8a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** A couple times per week
