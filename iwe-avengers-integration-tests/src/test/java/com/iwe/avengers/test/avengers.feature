Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://xbg1f6yo1b.execute-api.us-east-1.amazonaws.com/dev/'

Scenario: Get Avenger by Id
Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
When method get
Then status 200
And match response == {id: '#string', name: '#string', secretIdentity: '#string'}

Scenario: Creates a new  Avenger
Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Steve Rogres'}
When method post
Then status 201
And match response == {id: '#string', name: '#string', secretIdentity: '#string'}


Scenario: Creates a new  Avenger without required data
Given path 'avengers'
And request {name: 'Captain America'}
When method post
Then status 400

Scenario: Delete a Avenger by ID
Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
When method delete
Then status 204

Scenario: Update a Avenger by ID
Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
And request {name: 'Captain America', secretIdentity: 'Steve Rogres'}
When method put
Then status 200
And match response == {id: '#string', name: '#string', secretIdentity: '#string'}

Scenario: Update a Avenger by ID
Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
And request {name: 'Captain America'}
When method put
Then status 400