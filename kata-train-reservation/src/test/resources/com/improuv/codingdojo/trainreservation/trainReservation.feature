Feature: TrainReservation

  Background:
    Given the "ICE Grindelwald" train exists

  Scenario: Making a successful Reservation
    And it has carriage 1 with 0 of 1 seats reserved
    When I make a reservation for 1 persons
    Then I get the reservation receipt
    """
      Train: ICE Grindelwald
      Carriage 1 - Seat 1
      """

  Scenario: Reservation unsuccessful when train already fully booked
    And it has carriage 1 with 0 of 1 seats reserved
    When I make a reservation for 2 persons
    Then I get the reservation receipt
    """
      Train: ICE Grindelwald
      Reservation unsuccessful
      """

  Scenario: A group should preferrably not be split
    And it has carriage 1 with 19 of 20 seats reserved
    And it has carriage 2 with 18 of 20 seats reserved
    And it has carriage 3 with 17 of 20 seats reserved
    When I make a reservation for 3 persons
    Then I get the reservation receipt
    """
      Train: ICE Grindelwald
      Carriage 3 - Seat 18
      Carriage 3 - Seat 19
      Carriage 3 - Seat 20
      """

  Scenario: A group will be split if necessary
    And it has carriage 1 with 19 of 20 seats reserved
    And it has carriage 2 with 18 of 20 seats reserved
    When I make a reservation for 3 persons
    Then I get the reservation receipt
    """
      Train: ICE Grindelwald
      Carriage 1 - Seat 20
      Carriage 2 - Seat 19
      Carriage 2 - Seat 20
      """