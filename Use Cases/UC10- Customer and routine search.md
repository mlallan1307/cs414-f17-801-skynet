UC10: Customer and routine search
---------------------------------

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Trainer  
**Stakeholders and Interests:**

- Trainer: Wants to look up a customer to see what workout routines are assigned to them. They also want to look up a workout routine to see what customers are assigned to it.

**Preconditions:** None  
**Success Guarantee:** Trainer is able to look up a customer and see assigned routines, or lookup a routine and see the customers assigned to it.  
**Main Success Scenario:**

1.	Trainer selects to perform routine search on system.
2.	System displays two lists. One containing customers and one containing routines.

 *If trainer wants to see customers assigned to a routine, skip to step 7.*

3.	Trainer selects a customer on the customer list.
4.	System displays the customer information, a list of assigned routines, and a back button.
5.	Trainer selects back button.
6.	System returns to step 2 display.

 *Trainer repeats steps 3-6 until done.*

7.	Trainer selects a routine on the routine list.
8.	System displays the routine information, a list of customers assigned to it, and a back button.
9.	Trainer selects back button.
10.	System returns to step 2 display.

 *Trainer repeats steps 7-10 until done.*

**Extensions:**

4a. If no assigned routines

1. System displays a message that this customer has no assigned routines.

8a. If no customers assigned to routine

1. System displays a message that there are no customers assigned to this routine.

**Special Requirements:**

- UI has button to perform routine search

**Technology and Data Variations List:** None  
**Frequency of Occurrence:** Daily
