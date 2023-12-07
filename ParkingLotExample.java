package lld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
Parking Lot - Low Level Design

Requirements:

A parking lot has limited number of parking spots available.
The parking spot is based on the vehicle size. A small vehicle can be parked in a parking slot meant for big vehicle but not vice versa.
The system should be assigning the nearest parking space available which is specific to the vehicle size.
System should provide all the current used spots and the vehicle information.
System should provide all the current available spots.
System should assign a ticket once the vehicle is assigned a spot and mark the ticket as paid once the vehicle exits the spot.
 */
public class ParkingLotExample {


}


class ParkingLot {

    private Map<Slot, Vehicle> slotVehicleMapper;

    private Map<Vehicle, Ticket> ticketVehicleMapper = new HashMap<>();

    private List<Slot> availableSlotsList, occupiedSlotsList;

    public ParkingLot(int totalSlots) {
        init(totalSlots);
    }

    private void init(int totalSlots) {
        int slotsCount = totalSlots / 3;
        for (int i = 0; i < slotsCount; i++) {
            String slotNumber = String.valueOf(Math.random());
            availableSlotsList.add(new Slot(slotNumber, SlotType.SMALL));
        }
        for (int i = 0; i < slotsCount; i++) {
            String slotNumber = String.valueOf(Math.random());
            availableSlotsList.add(new Slot(slotNumber, SlotType.MEDIUM));
        }
        for (int i = 0; i < slotsCount; i++) {
            String slotNumber = String.valueOf(Math.random());
            availableSlotsList.add(new Slot(slotNumber, SlotType.BIG));
        }
    }


    // get all slots
    public int assignSlot(Vehicle vehicle) {

        // assign Ticket
        assignTicket(vehicle);
        // assign slot
        String slotNumber = String.valueOf(Math.random());

        //occupiedSlotsList.add()
        return -1;
    }

    public void assignTicket(Vehicle vehicle) {
        String ticketNumber = String.valueOf(Math.random());
        Ticket ticket = new Ticket(ticketNumber, TicketStatus.DUE);
        ticketVehicleMapper.put(vehicle, ticket);
    }


    public List<Slot> fetchCurrentInUseSlots() {

        return occupiedSlotsList;
    }

    public List<Slot> fetchAvailableSlots() {

        return availableSlotsList;
    }

}


class Slot {

    private String slotNumber;

    SlotType slotType;
    private Vehicle vehicle;

    public Slot(String slotNumber, SlotType slotType) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}


class Vehicle {
    private String vehicleNumber;

    private Ticket ticket;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

}

enum SlotType {
    BIG, MEDIUM, SMALL
}

enum SlotStatus {
    OCCUPIED, FREE
}


class Ticket {

    public Ticket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    private String ticketNumber;

    private TicketStatus ticketStatus;

    public Ticket(String ticketNumber, TicketStatus ticketStatus) {
        this.ticketNumber = ticketNumber;
        this.ticketStatus = ticketStatus;
    }
}

enum TicketStatus {
    PAID, DUE
}
