package service.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void getCity() {
        Room room = new Room();
        String hanoi = "Hanoi";
        room.setCity(hanoi);
        assertTrue(hanoi.equals(room.getCity()));
    }
}