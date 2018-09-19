Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://xbg1f6yo1b.execute-api.us-east-1.amazonaws.com/dev/'

Scenario: Get Avenger by Id

Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
When method get
Then status 200

Scenario: Creates a new  Avenger

Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Steve Rogres'}
When method post
Then status 201