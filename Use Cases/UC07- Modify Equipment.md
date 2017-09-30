UC07- Modify Equipment
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**

- Manager: Wants to modify inventory equipment data.

**Preconditions:** UC00 or UC01 has been completed and system knows the current user is a Manager.  
**Success Guarantee:** Inventory equipment data is modified and saved.  
**Main Success Scenario:**

1.	Manager selects to modify inventory equipment on system.
2. Manager selects equipment for system using **UC07.1**
3.	System displays the equipment inventory form with available fields populated.
4.	Manager modifies one or more fields on form.
5.	Manager submits changes.
6. System validates that all required fields are populated.
7. System validates that the given equipment name is not associated with another equipment entry.
8. System saves modified equipment.
9. System notifies manager that the update is complete.

**Extensions:**

*a. At any time, Manager is able to cancel operation.

1. Manager selects a cancel button.
2. System returns to Manager UI main page.

2a. Selection is not made.

1. Remain at step 2.

6a. Required fields are empty.

1. System notifies manager what required fields must be completed.

7a. Equipment name is associated with another equipment entry.

1.	System notifies manager that this equipment name is associated with another equipment entry.

**Special Requirements:**

- UI has button to modify inventory equipment entry.

**Technology and Data Variations List:**

4a. An equipment image should be smaller than 10MB.

8a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** A couple times per month
