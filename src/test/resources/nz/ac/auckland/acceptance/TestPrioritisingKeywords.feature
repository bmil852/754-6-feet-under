Feature: Business Idea Keyword Extraction
		Users can prioritise the keywords by changing the weights of the keywords.

		Scenario: A User changes the weight of a specified keyword to an acceptable weight
				Given a User already has a number of keywords returned
				When the User requests to change the weight of keyword "dog" to 4
				Then the weight of keyword "dog" is updated to 4

		Scenario: A User changes the weight of a specified keyword to an invalid weight
				Given a User already has a number of keywords returned
				When the User requests to change the weight of keyword "walking" to 0
				Then the weight of keyword "walking" is not updated to 0