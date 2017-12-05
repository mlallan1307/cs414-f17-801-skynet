UC15- Remove Customer
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager
**Stakeholders and Interests:**

- Manager: Wants to remove a customer from the system.

**Preconditions:** UC00 or UC01 has been completed and system knows the current user is a Manager.  
**Success Guarantee:** The customer entry is removed from the system.
**Main Success Scenario:**

1.	Manager selects to modify customer on system.
2.	Manager selects customer for system using **UC05.1**
3.	Manager selects the Remove button.
4.	System displays a confirmation to remove the selected customer.
5.	Manager selects yes.
6. System removes the customer entry and saves the change.
7. System updates the list of customers to reflect the removal of the customer.

**Extensions:**

*a. At any time, manager is able to cancel operation.

1. Manager selects close on the popup.
2. System does not remove customer.

5a. Manager selects no or close on the window.

1.	Confirmation is dismissed and customer is not removed.

**Special Requirements:**

- UI has remove button on select page

**Technology and Data Variations List:** None  
**Frequency of Occurrence:** Monthly
