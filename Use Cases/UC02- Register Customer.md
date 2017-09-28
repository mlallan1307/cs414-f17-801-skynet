UC02- Register Customer
----------------------------------

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** Manager  
**Stakeholders and Interests:**

- Manager: Wants to easily add a new customer's personal and membership information.
- Customer: Wants to provide only the information that is needed.

**Preconditions:** None  
**Success Guarantee:** New customer information is saved.  
**Main Success Scenario:**

1.	Manager selects to create new customer on system.
2.	System displays a digital form with fields including: First name, Last name, address, phone, email, Driver's license number, and health insurance provider name.
3.	Customer presents manager with required personal information.
4.	Manager enters new customer data into a digital form.
5.	Manager submits form.
6.	System validates that all required fields are populated.
7.	System validates that new customer customer's Driver's license number is not already associated with an existing customer.
8.	System saves new customer with the given information, and sets membership status to active. 
9.	System displays that new customer has been added.

**Extensions:**

6a. Required fields are empty.

1.	System notifies manager what required fields must be completed.

7a. Driver's license number is already associated with a customer.

1.	System notifies manager that this customer already exists and asks if the manager would like to go to **Modification of customers**.

 1a. If yes, switch to **Modification of customers**.

 1b. If no, dismiss notification.
 
**Special Requirements:**

- UI has button to create new customer.

**Technology and Data Variations List:**

8a. System stores data in non-volatile form for future state restoration.

**Frequency of Occurrence:** A couple times per day
