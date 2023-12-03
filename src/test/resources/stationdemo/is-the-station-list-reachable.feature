Feature: Is there a list of stations?

  Scenario: stationdemo pod delivers a list of stations
    Given the stationdemo app returns http code 200
    When we call the stations url
    Then we get a json list of stations