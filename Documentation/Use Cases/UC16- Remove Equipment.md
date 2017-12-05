UC16- Remove Equipment
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager
**Stakeholders and Interests:**

- Manager: Wants to remove equipment from the system.

**Preconditions:** UC00 or UC01 has been completed and system knows the current user is a Manager.  
**Success Guarantee:** The equipment entry is removed from the system.
**Main Success Scenario:**

1.	Manager selects to modify equipment on system.
2.	Manager selects equipment for system using **UC07.1**
3.	Manager selects the Remove button.
4.	System displays a confirmation to remove the selected equipment.
5.	Manager selects yes.
6. System removes the equipment from any exercises that contain this equipment.
7. System removes the equipment entry and saves the change.
8. System updates the list of equipment to reflect the removal of the equipment.

**Extensions:**

*a. At any time, manager is able to cancel operation.

1. Manager selects close on the popup.
2. System does not remove the equipment.

5a. Manager selects no or close on the window.

1.	Confirmation is dismissed and equipment is not removed.

**Special Requirements:**

- UI has remove button on select page

**Technology and Data Variations List:** None  
**Frequency of Occurrence:** Monthly
