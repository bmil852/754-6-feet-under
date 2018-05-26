Feature: Market Comprehension

  Scenario: User wants a summary of each category in the search results
    Given user has a set of weighted keywords to search with
    When user performs a search
    Then each category in the search results will have a summary

  Scenario: User wants to know if summary of each category is correct
    Given user has a set of weighted keywords to search with
    When user performs a search
    Then each category in the search results will have the expected summary



