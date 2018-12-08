package service.dao;

import org.springframework.stereotype.Component;
import service.models.Room;
import service.models.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomDao {

    private static List<Room> rooms;

    {
        rooms = new ArrayList<Room>();
        rooms.add(new Room(101L, "Eindhoven", false));
        rooms.add(new Room(201L, "Eindhoven", true));
        rooms.add(new Room(301L, "Eindhoven", false));
        rooms.add(new Room(401L, "Tilburg", false));
        rooms.add(new Room(501L, "Tilburg", false));
        rooms.add(new Room(601L, "Tilburg", false));
    }

    public List list() { return rooms; }

    public Room create(Room room) {
        room.setId(System.currentTimeMillis());
        rooms.add(room);
        return room;
    }

    public Room get(Long id) {

        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    public List get(String city) {

        List<Room> cityRooms = new ArrayList<Room>();
        for (Room room : rooms) {
            if (room.getCity().equals(city) && !room.getReserved()) {
                cityRooms.add(room);
            }
        }
        return cityRooms;
    }

    public Long delete(Long id) {

        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                rooms.remove(room);
                return id;
            }
        }

        return null;
    }

    public Room update(Long id, Room roomNew) {

        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                roomNew.setId(room.getId());
                rooms.remove(room);
                rooms.add(roomNew);
                return roomNew;
            }
        }

        return null;
    }
}