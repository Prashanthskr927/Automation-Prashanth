
Feature: Validating Employee's social profile Match

@SocialProfile
Scenario: Validating Employee's social profile Match
Given I navigate to Google website
And  I search and navigate "Branch" website
And  I assert Branch website
And  I scroll down to the footer section
And  I find and click on teams link

When I count the sum of employees in 'All' tab
And I count the sum of employees in other tabs

Then I Verify employee with social profiles listed between All tab and other tabs
