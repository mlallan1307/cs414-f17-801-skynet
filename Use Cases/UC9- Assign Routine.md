UC9- Assign Routine
-------------------------------

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to assign one or more routines to a customer.

**Preconditions:** None  
**Success Guarantee:** A customer has one or more routines assigned to them and the system saves the association.  
**Main Success Scenario:**

1.	Trainer selects to assign customer workout routines.
2.	System fetches and displays a list of customers.
3.	Trainer selects a customer from the list.
4.	System displays that customer's assigned routines and a list of all routines that are not assigned to them.
5.	Trainer selects an unassigned routine.
6.	System moves item from unassigned list to assigned list.

 *Trainer repeats steps 5-6 for as many assignments as needed.*

7.	Trainer selects to save routine list.
8.	System saves new routine associations for customer and customer associations with routine.
9.	System notifies trainer that update is complete.

**Extensions:**

2a. There are no customer entries.

1. System displays a message that there are no customers.

4a. Customer has no assigned routines.

1. System displays a message that this customer has no routines.

4b. All available routines are assigned to this customer.

1. System displays an empty list for the unassigned routines.

**Special Requirements:**

- UI has button to assign customer workout routines

**Technology and Data Variations List:**

8a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** Daily
