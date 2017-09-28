UC12- Login User
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** User (Manager or Actor)
**Stakeholders and Interests:**  

- Manager: Wants to be able to log into the system using their username and password. They want to be identified as a manager.
- Trainer: Wants to be able to log into the system using their username and password. They want to be identified as a trainer.

**Preconditions:**

- UC01 (Hire Trainer) for trainer login
- UC11 (Initialize System) for manager login

**Success Guarantee:** User is logged in and identified as the correct user type.
**Main Success Scenario:**

1. System finds that there is currently not a logged in user.
2. System displays a digital form with fields including: username and password.
3. User fills out digital form fields with their information.
4. User submits form.
5. System validates that all required fields are populated.
6. System validates that the username field contains a known username.
7. System validates that the password matches the stored password for this username.
8. System logs user in as either manager or trainer depending of the type of user.

**Extensions:**

5a. Required fields are empty.

1. System notifies user what required fields must be completed.

6a. Entered username is not a known username.

1. System notifies user that the username is invalid.

7a. Entered password does not match the user account's stored password

1. System notifies user that the password is incorrect.

**Special Requirements:** None

**Technology and Data Variations List:** None

**Frequency of Occurrence:** Daily