package com.improuv.codingdojo.trainreservation;

import java.util.List;

public class Schalter {
    private List<Train> trains;

    public String reserve(String trainName, int seatCount) {
        for(Train train: trains)  {
            if (train.getName().equals(trainName)) {
                train.reserve(seatCount);
            }
        }
        return getTicket(trainName, seatCount);
    }

    private String getTicket(String trainName, int seatCount) {
        String ticketText = "Train: ICE " + trainName;
        for (int i = 1; i <= seatCount; i++) {
            ticketText += "\nCarriage 1 - Seat " + i;
        }
        return ticketText;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

}
