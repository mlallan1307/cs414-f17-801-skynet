UC12- Assign Routine
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to assign one or more routines to a customer.

**Preconditions:** UC01 has been completed and system knows the current user is a Trainer.  
**Success Guarantee:** A customer has one or more routines assigned to them and the system saves the association.  
**Main Success Scenario:**

1.	Trainer selects to assign customer workout routines.
2.	Trainer selects a customer for the system using **UC05.1**
3.	System displays a list of the selected customer's assigned routines and an option to select another routine to assign.
4.	Trainer selects a routine to assign using **UC11.1**
5. System adds the selected routine to the assigned routines list for the customer.

 *Trainer repeats steps 3-5 for as many assignments as needed.*
 
 *Trainer may skip to step 10 if they are done*

6. Trainer may select an "assigned routine" from the shown "assigned routines" list if they want to unassign it.
7. System displays confirmation to unassign routine.
8. Trainer confirms to unassign routine.
9. System removes assigned routine.

 *Trainer repeats steps 6-9 for as many unassign operations are needed.*
 
10. Trainer selects to save routine list.
11. System updates assigned routines for customer. For assigned routines, those routine's list of customers is updated to include this customer. For unassgined routines, those routine's list of customers is updated to remove this customer.
12. System notifies trainer that update is complete.

**Extensions:**

*a. At any time, Trainer is able to cancel operation.

1. Trainer selects a cancel button.
2. System returns to Trainer UI main page.

2a. Selection is not made.

1. Remain at step 2.

3a. Customer has no assigned routines.

1. System displays a message that this customer has no routines.

4a. Selection not made.

1. Remain at step 4 or trainer may go to step 6 or 10

5a. Selected routine is already assigned to this customer.

1. System notifies trainer that the selected routine is already assigned to this customer and trainer returns to step 3.

8a. Trainer does not confirm to unassign.

1. Trainer may return to step 6 or skip to step 10.

**Special Requirements:**

- UI has button to assign customer workout routines.

**Technology and Data Variations List:**

11a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Daily
