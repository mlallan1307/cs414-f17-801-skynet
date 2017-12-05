UC13- Logout User
=======================

**Scope:** "SkynetGym" Gym management Software  
**Level:** User goal  
**Primary Actor:** User (Manager or Trainer)  
**Stakeholders and Interests:**  

- Manager: Wants to be able to logout of the system.
- Trainer: Wants to be able to logout of the system.

**Preconditions:**

- UC00 (Initialize System) for manager login.
- UC02 (Create Trainer) for trainer login.

**Success Guarantee:** User is no longer logged in at returns to login (UC01).  
**Main Success Scenario:**

1. User selects the initial welcome tab while logged in.
2. User selects the logout button.
3. System returns user to login page (UC01).

**Extensions:** None

**Special Requirements:**

- Manager and Trainer welcome pages have a button to logout.

**Technology and Data Variations List:** None

**Frequency of Occurrence:** Daily