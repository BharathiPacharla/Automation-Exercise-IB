Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    Scenario: Change password successfully
      Given I am logged in as "<current_username>"
      And I am on the change password screen
      When I enter my current password "<current_password>"
      And I enter a new password "<new_password>"
      And I confirm the new password "<new_password>"
      And I click on the change password button
      Then I should see a success message confirming my password has been changed

    Scenario Outline: Invalid password change attempts
      Given I am logged in as "<current_username>"
      And I am on the change password screen
      When I enter my current password "<current_password>"
      And I enter a new password "<new_password>"
      And I confirm the new password "<confirm_password>"
      And I click on the change password button
      Then I should see an error message "<error_message>"

      Examples:
        | current_username | current_password | new_password | confirm_password | error_message                             |
        | "user123"        | "oldPassword"    | "newPass123" | "newPass123"     | ""                                         | # Successful password change
        | "user123"        | "oldPassword"    | "newPass123" | "differentPass"  | "Passwords do not match. Please try again." |
        | "user123"        | "wrongPassword"  | "newPass123" | "newPass123"     | "Current password is incorrect."           |

    Scenario Outline: Password change with invalid new password formats
      Given I am logged in as "<current_username>"
      And I am on the change password screen
      When I enter my current password "<current_password>"
      And I enter a new password "<new_password>"
      And I confirm the new password "<confirm_password>"
      And I click on the change password button
      Then I should see an error message "<error_message>"

      Examples:
        | current_username | current_password | new_password | confirm_password | error_message                           |
        | "user123"        | "oldPassword"    | "short"      | "short"          | "Password must be at least 6 characters." |
        | "user123"        | "oldPassword"    | "password123"| "password123"    | "Password must contain at least one special character." |
        | "user123"        | "oldPassword"    | "ValidPass!" | "ValidPass!"     | "Password must contain at least one number." |

