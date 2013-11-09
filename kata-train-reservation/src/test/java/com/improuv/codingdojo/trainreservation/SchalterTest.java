package com.improuv.codingdojo.trainreservation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SchalterTest {

    private Schalter schalter;
    private Train grindelwald;
    private Train london;

    @Before
    public void setup() {
        schalter = new Schalter();
        london = mock(Train.class);
        when(london.getName()).thenReturn("London");
        grindelwald = mock(Train.class);
        when(grindelwald.getName()).thenReturn("Grindelwald");
        List<Train> trains = new ArrayList<Train>() {{
            add(grindelwald);
            add(london);
        }};
        schalter.setTrains(trains);
    }

    @Test
    public void givenTrainWith1EmptySeat_seatIsReserved() {
        String receipt = schalter.reserve("Grindelwald", 1);
        verify(grindelwald).reserve(1);
        assertThat(receipt, is("Train: ICE Grindelwald\nCarriage 1 - Seat 1"));
    }

    @Test
    public void givenTrainWith2EmptySeat_seatsAreReserved() {
        String receipt = schalter.reserve("Grindelwald", 2);
        verify(grindelwald).reserve(2);
        assertThat(receipt, is("Train: ICE Grindelwald\nCarriage 1 - Seat 1\n" +
                "Carriage 1 - Seat 2"));

    }

    @Test
    public void givenNonExistantTrain_reserveFails() {
        String receipt = schalter.reserve("Giesing", 1);
        verify(grindelwald, times(0)).reserve(1);
        verify(london, times(0)).reserve(1);
        //TODO: implement assertion
        //assertThat(receipt, is("Train: ICE Giesing\nNo such train"));
    }

    @Test
    public void givenAnotherTrainWith1EmptySeat_seatIsReserved() {
        String receipt = schalter.reserve("London", 1);
        verify(london).reserve(1);
        assertThat(receipt, is("Train: ICE London\nCarriage 1 - Seat 1"));
    }
}
