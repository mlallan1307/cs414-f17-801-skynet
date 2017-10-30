System Test Cases
=======================


###Overview

This document gives the steps that the customer can use to validate the system's functionality

----------

###1. System Initialization

1. With a no stored system data, run the Skynet Gym program
2. The program should display a digital form with fields including: First name, Last name, address, phone, email, Drivers license number, health insurance provider name, desired username, and password.
3. If any fields are blank, the system should notify you that there are empty fields upon submission
4. Assuming the role of the manager, enter your information in the form and click submit
5. The system should then transition you to the Manager screen (as a logged in manager)
6. Close the program
7. Reopen Skynet Gym
8. You should see the login screen
9. Validate that the provided manager username and password allow you to login successfully
10. You should then see the Manager Screen again

###2. Login

1. With an initialized system, run the Skynet Gym program
2. The first thing you should see is the Login screen
3. Login as a manager and you should be presented with the manager screen, or login as a trainer and you should be presented with the trainer screen.
4. If either field is empty or not associated with a valid account then the login should fail

###3. Recover Username

1. With an initialized system, run the Skynet Gym program
2. The first thing you should see is the Login screen with a "Recover Username" button
3. Click the "Recover Username" button
4. You should see a form with fields for First name, Last name, and Drivers license number
5. Empty fields should result in a failure message
6. If all fields do not match a valid account the result should be a failure message
7. Try entering valid account information and a message should display the corresponding username

###4. Reset Password

1. With an initialized system, run the Skynet Gym program
2. The first thing you should see is the Login screen with a "Reset Password" button
3. Click the "Reset Password" button
4. You should see a form with fields for First name, Last name, Drivers license number, username and new password
5. Empty fields should result in a failure message
6. If all fields (except new password) do not match a valid account the result should be a failure message
7. Try entering valid account information and a message should notify you that the password has been set to the new password

###5. Manager: Create Trainer

1. Log in as the manager
2. Select the Create Trainer Tab
3. You should be presented with a form containing fields for First name, Last name, address, phone, email, Drivers license number, health insurance provider name, work hours, qualifications, username, and password.
4. Empty fields should result in a failure message
5. If the entered Drivers license number or username correspond to an existing trainer then submission should fail
6. Validate that work hours entered must be valid periods of time that do not span more than one day.
7. Fill out the form with valid information and submission should be successful
8. Close the Skynet Gym program and reopen it
9. On the login screen, enter the username and password of the new trainer
10. Validate that login succeeds and that you are given the trainer screen

###6. Manager: Modify Trainer Data

1. Log in as the manager
2. Select the Modify Trainer Tab
3. You should be presented with a form to select a trainer to modify. It should contain fields searching (First name, Last name, phone, email, and username) as well as a selectable list of matching (initially all) trainers, and a button to modify the selected trainer.
4. Clicking the modify button without selecting a trainer should not do anything
5. Try reducing the trainer list by filling out one or more of the search fields and clicking search
6. Select a trainer in the list and click the Modify button
7. You should then be presented with a form very similar to the Create Trainer form. There should be no password field, and the rest of the form should be populated with the selected trainers information.
8. If the entered Drivers license number or username correspond to a different trainer then submission should fail
6. Validate that work hours entered must be valid periods of time that do not span more than one day.
7. Fill out the form with valid information and submission should be successful
8. Close the Skynet Gym program and reopen it
9. On the login screen, enter the username and password of the new trainer
10. Validate that login succeeds and that you are given the trainer screen

###7. Manager: Create Customer

1. Log in as the manager
2. Select the Create Customer Tab
3. You should be presented with a form containing fields for First name, Last name, address, phone, email, Driver's license number, and health insurance provider name.
4. Empty fields should result in a failure message
5. If the entered Drivers license number corresponds to an existing customer then submission should fail
6. Fill out the form with valid information and submission should be successful
7. To validate that the customer has been created. Follow the Modify Customer Data test and use the new customer's information

###8. Manager: Modify Customer Data

1. Log in as the manager
2. Select the Modify Customer Tab
3. You should be presented with a form to select a customer to modify. It should contain fields searching (First name, Last name, phone, email) as well as a selectable list of matching (initially all) customers, and a button to modify the selected customer.
4. Clicking the modify button without selecting a customer should not do anything
5. Try reducing the customer list by filling out one or more of the search fields and clicking search
6. Select a customer in the list and click the Modify button
7. You should then be presented with a form very similar to the Create Customer form. The form should be populated with the selected customers information.
8. If the entered Drivers license number correspond to a different customer then submission should fail
9. Fill out the form with valid information and submission should be successful
10. To validate that the information has been updated, you can repeat the steps to select the customer and the form should come up again with the newly updated information

###9. Manager: Create Equipment

1. Log in as the manager
2. Select the Create Equipment Tab
3. You should be presented with a form containing fields for Name, picture, and quantity.
4. Empty fields should result in a failure message
5. If the entered name corresponds to an existing equipment entry then submission should fail
6. Fill out the form with valid information and submission should be successful
7. To validate that the equipment has been created. Follow the Modify Equipment test and use the new equipment's information

###10. Manager: Modify Equipment

1. Log in as the manager
2. Select the Modify Equipment Tab
3. You should be presented with a form to select an equipment entry to modify. It should contain the fields for  searching (Name and quantity) as well as a selectable list of matching (initially all) equipment, and a button to modify the selected equipment entry.
4. Clicking the modify button without selecting an entry should not do anything
5. Try reducing the equipment list by filling out one or more of the search fields and clicking search
6. Select an equipment entry in the list and click the Modify button
7. You should then be presented with a form very similar to the Create Equipment form. The form should be populated with the selected equipment's information.
8. If the name is modified and it corresponds to a different equipment entry then submission should fail
9. Fill out the form with valid information and submission should be successful
10. To validate that the information has been updated, you can repeat the steps to select the equipment and the form should come up again with the newly updated information

###11. Trainer: Create Exercise

1. Log in as a trainer
2. Select the Create Exercise Tab
3. You should be presented with a form containing fields for exercise name, duration (time), number of sets, repetitions per set, and an option to select equipment
4. An empty name field should result in a failure message
5. If none of the following fields are filled out then submission should fail: duration (time), number of sets, repetitions per set.
6. If duration is filled out in addition to number of sets or repetitions per set, then submission should fail
7. You should have an equipment selection form similar to the one used to select equipment in the "Modify Equipment" step.
8. You can use it to select an equipment entry for the exercise, but selecting an equipment entry is not required for submission.
9. If the entered name corresponds to an existing exercise entry then submission should fail
10. Fill out the form with valid information and submission should be successful
11. To validate that the exercise has been created. Follow the Modify Exercise test and use the new exercise's name

###12. Trainer: Modify Exercise

1. Log in as a Trainer
2. Select the Modify Exercise Tab
3. You should be presented with a form to select an exercise to modify. It should contain the fields for  searching (Just a Name field) as well as a selectable list of matching (initially all) exercises, and a button to modify the selected exercise.
4. Clicking the modify button without selecting an entry should not do anything
5. Try reducing the exercise list by filling out a partial or full name in the search field and clicking search
6. Select an exercise in the list and click the Modify button
7. You should then be presented with a form very similar to the Create Exercise form. The form should be populated with the selected exercise's information.
8. If the name is modified and it corresponds to a different exercise entry then submission should fail
9. Fill out the form with valid information and submission should be successful
10. To validate that the information has been updated, you can repeat the steps to select the exercise and the form should come up again with the newly updated information

###13. Trainer: Create Routine

1. Log in as a trainer
2. Select the Create Routine Tab
3. You should be presented with a form containing fields for routine name, an option to add existing exercises, and an option create new exercise.
4. Try creating a new exercise, you should be presented with the created exercise form and be able to do it the same way you did in the "Create Exercise" test.
5. Now try adding one or more existing exercises to this routine by selecting them in the available selection list and selecting add.
6. An empty name field should result in a failure message
7. Submitting a routine with no exercises should result in a failure message
8. If the entered name corresponds to an existing routine then submission should fail
9. Fill out the form with valid information and submission should be successful
10. To validate that the routine has been created. Follow the Modify Routine test and use the new routine's name

###14. Trainer: Modify Routine

1. Log in as a Trainer
2. Select the Modify Routine Tab
3. You should be presented with a form to select a routine to modify. It should contain the fields for searching (Just a Name field) as well as a selectable list of matching (initially all) routines, and a button to modify the selected routine.
4. Clicking the modify button without selecting an entry should not do anything
5. Try reducing the routine list by filling out a partial or full name in the search field and clicking search
6. Select a routine in the list and click the Modify button
7. You should then be presented with a form very similar to the Create Routine form. The form should be populated with the selected routine's information.
8. If the name is modified and it corresponds to a different routine entry then submission should fail
9. Submitting a routine with no exercises should result in a failure message
10. Fill out the form with valid information and submission should be successful
11. To validate that the information has been updated, you can repeat the steps to select the routine and the form should come up again with the newly updated information

###15. Trainer: Assign Routine

1. Log in as a Trainer
2. Select the Assign Routine Tab
3. You should be presented with the customer selection form as seen in the "Modify Customer" test.
4. Select a customer using the interface and you should be presented with a list of the customer assigned routines, a list of unassigned routines for this customer, a button to assign an unassigned routine, and a button to unassign an assigned routine.
5. Clicking the assign or unassign buttons without selecting a routine should not do anything
6. Try selecting an unassigned routine, and clicking the assign button. The selected routine should be added to this customer's Assigned Routine list.
7. Try selecting an assigned routine, and clicking the unassign button. The selected routine should be added to this customer's Unassigned Routine list.
8. Select the save button to finalize the changes and you should be a success message
9. To validate the changes to this Customer's assigned routines has been saved, you can repeat the steps to select the customer and view the assigned and unassigned routine lists.