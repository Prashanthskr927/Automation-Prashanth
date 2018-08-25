Feature: Validating Employee departments Match

@Departments
Scenario: Validating Employee departments Match
Given I navigate to Google website
And  I search and navigate "Branch" website
And  I assert Branch website
And  I scroll down to the footer section
And  I find and click on teams link

When I count the sum of employees in 'All' tab
And I count the sum of employees in other tabs

Then I Verify employee departments are listed correctly between All tab and Department tabs