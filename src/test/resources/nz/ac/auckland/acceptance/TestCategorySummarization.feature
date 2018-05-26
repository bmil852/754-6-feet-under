Feature: Market Comprehension

  Scenario: A user wants a summary of each category in the search results
    Given a User has a set of weighted keywords to search for
    When the User performs a search
    Then each category in the search results will have a summary