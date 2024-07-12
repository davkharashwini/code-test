Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

 Scenario: User changes their password successfully
    Given the user is logged in with valid username "username" and password "Pwd"
    And the user navigates to the password change page
    When the user enters current password "current_password"
    And the user enters new password "new_password123"
    And the user confirms new password "new_password123"
    And submits the password change form
    Then the password should be changed successfully
    And the user should receive a confirmation message

  Scenario: User attempts to change password with incorrect current password
    Given the user is logged in with valid username "username" and password "Pwd"
    And the user navigates to the password change page
    When the user enters current password "incorrect_password"
    And the user enters new password "new_password123"
    And the user confirms new password "new_password123"
    And submits the password change form
    Then the password change should fail
    And the user should receive an error message indicating "Current password is incorrect"

  Scenario: User attempts to change password with mismatched new passwords
    Given the user is logged in with valid username "username" and password "Pwd"
    And the user navigates to the password change page
    When the user enters current password "current_password"
    And the user enters new password "new_password123"
    And the user confirms new password "different_password123"
    And submits the password change form
    Then the password change should fail
    And the user should receive an error message indicating "New password and confirmation do not match"

  Scenario: User attempts to change password with a plain text new password
    Given the user is logged in with valid username "username" and password "Pwd"
    And the user navigates to the password change page
    When the user enters current password "current_password"
    And the user enters new password with plain text "testpassword"
    And the user confirms new password "testpassword"
    And submits the password change form
    Then the password change should fail
    And the user should receive an error message indicating "New password does not meet the complexity requirements"

    Scenario: User attempts to change password with a password length less than eighth characters
      Given the user is logged in with valid username "username" and password "Pwd"
      And the user navigates to the password change page
      When the user enters current password "current_password"
      And the user enters new password with length of four characters "test"
      And the user confirms new password "test"
      And submits the password change form
      Then the password change should fail
      And the user should receive an error message indicating "New password does not meet the complexity requirements"
