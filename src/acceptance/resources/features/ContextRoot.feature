Feature: Context Root of this API
  In order to use the API, it must be available

  Scenario: Root of the API
    Given the application is alive
    When I navigate to "http://predict.trevorism.com/api"
    Then then a link to the help page is displayed

  Scenario: Root of the API HTTPS
    Given the application is alive
    When I navigate to "https://predict.trevorism.com/api"
    Then then a link to the help page is displayed

  Scenario: Root of the API App engine
    Given the application is alive
    When I navigate to "https://trevorism-predict.appspot.com/api"
    Then then a link to the help page is displayed

  Scenario: Ping
    Given the application is alive
    When I ping the application deployed to "http://predict.trevorism.com/api"
    Then pong is returned, to indicate the service is alive

  Scenario: Ping https
    Given the application is alive
    When I ping the application deployed to "https://predict.trevorism.com/api"
    Then pong is returned, to indicate the service is alive

  Scenario: Ping on app engine
    Given the application is alive
    When I ping the application deployed to "https://trevorism-predict.appspot.com/api"
    Then pong is returned, to indicate the service is alive