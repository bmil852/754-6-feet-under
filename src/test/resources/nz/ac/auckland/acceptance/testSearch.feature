Feature: Market Comprehension
The weighted keywords should be used as the input to a traditional search engine (e.g., Google Search or Bing), and a set of documents are expected to be returned.
	
	Scenario: User performs a search for their business idea
	Given user has a set of weighted keywords to search with
	When user performs a search
	Then a non-empty set of documents are returned