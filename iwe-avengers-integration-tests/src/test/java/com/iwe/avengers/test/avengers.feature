Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://xbg1f6yo1b.execute-api.us-east-1.amazonaws.com/dev/'

Scenario: Avenger not found
Given path 'avengers', 'avenger-not-found'
When method get
Then status 404


Scenario: Creates a new Avenger
#Cria o avenger
Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {id: '#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}

* def savedAvenger = response

#Pesquisa pelo avenger para validar a criação do mesmo no BD
Given path 'avengers', savedAvenger.id
When method get
Then status 200
And match $ == savedAvenger

#comparando cada atributo
And match $.id == savedAvenger.id
And match $.name == savedAvenger.name


Scenario: Creates a new Avenger without the required data
Given path 'avengers'
And request {name: 'Captain America'}
When method post
Then status 400


Scenario: Delete Avenger by id
#Cria o avenger
Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {id: '#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}

* def savedAvenger = response

#Pesquisa pelo avenger para validar a criação do mesmo no BD 
Given path 'avengers', savedAvenger.id
When method get
Then status 200
And match $ == savedAvenger

#Exclui o avenger no BD
Given path 'avengers', savedAvenger.id
When method delete
Then status 204

#confirma se o avenger foi excluido
Given path 'avengers', savedAvenger.id
When method get
Then status 200

Scenario: Attempt to Delete a non-existent Avenger
Given path 'avengers', 'avenger-not-found'
When method delete
Then status 404


Scenario: Updates the Avenger data
#Cria o avenger
Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {id: '#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}

* def savedAvenger = response

#Altera o avenger no BD
Given path 'avengers', savedAvenger.id
And request {name: 'Captain America-010', secretIdentity: 'Steve Rogers-010'}
When method put
Then status 200
And match response ==  {id: '#string', name: 'Captain America-010', secretIdentity: 'Steve Rogers-010'}

* def updatedAvenger = response

#consulta para verificar se o recurso foi realmente alterado 
Given path 'avengers', updatedAvenger.id
When method get
Then status 200
And match $ == updatedAvenger

Scenario: Updates the avenger without the required data
Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
And request {name: 'Captain America'}
When method put
Then status 400

Scenario: Attempt to Update a non-existent Avenger
Given path 'avengers', 'avengerNotFound'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method put
Then status 404