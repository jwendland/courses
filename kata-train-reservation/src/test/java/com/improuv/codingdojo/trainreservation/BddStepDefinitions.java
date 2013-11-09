package com.improuv.codingdojo.trainreservation;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BddStepDefinitions {

    @Given("^the \"([^\"]*)\" train exists$")
    public void the_train_exists(String trainName) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Given("^it has carriage (\\d+) with (\\d+) of (\\d+) seats reserved$")
    public void it_has_carriage_with_of_seats_reserved(int carriageNumber, int seatsReserved, int seatsTotal) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @When("^I make a reservation for (\\d+) persons$")
    public void I_make_a_reservation_for_persons(int numberOfPersons) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^I get the reservation receipt$")
    public void I_get_the_reservation_receipt(String receipt) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
