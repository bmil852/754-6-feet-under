Feature: Market Comprehension
For each category, a brief summary describing the category briefly should be generated from its associated documents.

    Scenario: A user wants a summary of each category in the search results
    Given user has a set of weighted keywords to search with
    When A user performs a search
    Then each category in the search results will have an associated summary

    Scenario: A user wants a summary of each category in the search results
    Given user has a set of weighted keywords to search with
    When A user performs a search
    Then each category in the search results will have the correct summary