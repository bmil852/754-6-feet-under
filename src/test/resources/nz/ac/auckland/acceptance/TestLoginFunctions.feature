Feature: Account Management - Login Functions
	Two types of roles - Users and Administrators - can sign up, in, and off.

	Scenario: A User signs up to the system with valid details
		Given a new User wants to sign up to the system
		When the User provides valid details for signing up
		Then the User is succesfully registered with the system

	Scenario: An Administrator signs up to the system with valid details
		Given a new Administrator wants to sign up to the system
		When the Administrator provides valid details for signing up
		Then the Administrator is succesfully registered with the system