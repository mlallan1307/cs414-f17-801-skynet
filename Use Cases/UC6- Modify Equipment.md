UC6: Modification of equipment inventory
----------------------------------------

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**

- Manager: Wants to modify inventory equipment data.

**Preconditions:** None  
**Success Guarantee:** Inventory equipment data is modified and saved.  
**Main Success Scenario:**

1.	Manager selects to modify inventory equipment on system.
2.	System retrieves all equipment names.
3.	System displays a list of all the equipment names for manager.
4.	Manager selects an equipment entry from list.
5.	System displays the equipment inventory form with available fields populated.
6.	Manager modifies one or more fields on form.
7.	Manager submits changes.
8.	System validates that all required fields are populated.
9.	System validates that the given equipment name is not associated with another equipment entry.
10.	System saves modified equipment.
11.	System notifies manager that the update is complete.

**Extensions:**

2a. No equipment has been entered in the system

1.	Notify manager that there is no equipment in inventory

8a. Required fields are empty.

1. System notifies manager what required fields must be completed

9a. Equipment name is associated with another equipment entry

1.	System notifies manager that this equipment name is associated with another equipment entry.

**Special Requirements:**

- UI has button to modify inventory equipment entry

**Technology and Data Variations List:**

6a. An equipment image should be smaller than 10MB

10a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** A couple times per month
