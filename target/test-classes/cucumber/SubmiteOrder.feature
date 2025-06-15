
@tag
Feature: Purcahase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Positive test of Submitting the orderr
    Given Logged in with username <username> and password <password>
    When I add the product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username  									 | password 					| productName |
      | automationtest1@ggmail.com 	 | AutomationTest@24  | ZARA COAT 3	|
   		
