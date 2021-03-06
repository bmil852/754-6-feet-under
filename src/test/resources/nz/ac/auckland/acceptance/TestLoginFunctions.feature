Feature: Account Management - Login Functions
	Two types of roles - Users and Administrators - can sign up, in, and off.

	Scenario: A User signs up to the system with valid details
		Given a new User wants to sign up to the system
		When the User provides valid details for signing up
		Then the User is successfully registered with the system

	Scenario: An Administrator signs up to the system with valid details
		Given a new Administrator wants to sign up to the system
		When the Administrator provides valid details for signing up
		Then the Administrator is successfully registered with the system

	Scenario: A User signs up to the system with invalid details
		Given a new User wants to sign up to the system
		When the User provides invalid details for signing up
		Then the User is not successfully registered with the system

	Scenario: An Administrator signs up to the system with invalid details
		Given a new Administrator wants to sign up to the system
		When the Administrator provides invalid details for signing up
		Then the Administrator is not successfully registered with the system

    Scenario: A User signs out of the system
        Given a User is already signed into the system
        When the User signs out of the system
        Then the User is no longer signed in to the system

    Scenario: An Administrator signs out of the system
        Given an Administrator is already signed into the system
        When the Administrator signs out of the system
        Then the Administrator is no longer signed in to the system