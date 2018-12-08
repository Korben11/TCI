package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.dao.RoomDao;
import service.dao.StudentDao;
import service.models.Room;
import service.models.Student;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomDao dao;
    @Autowired
    private StudentDao studentDao;
    private static final String path = "/rooms";
    private static final String pathDetail = path + "/{id}";

    @GetMapping(path)
    public List getRooms(@RequestParam(value = "city", required = false) String city) {
        if (city != null) {
            return dao.get(city);
        }
        return dao.list();
    }

    @GetMapping(pathDetail + "/subscribe/{studentId}")
    public ResponseEntity reserveRoom(@PathVariable("id") Long id, @PathVariable("studentId") Long studentId) {
        Room room = dao.get(id);

        if (room == null) {
            return new ResponseEntity("No Room found for ID " + id, HttpStatus.NOT_FOUND);
        }
        if (room.getReserved()) {
            return new ResponseEntity("Room is already subscribed", HttpStatus.BAD_REQUEST);
        }
        Student student = studentDao.get(studentId);
        if (student == null) {
            return new ResponseEntity("No Student found for ID " + studentId, HttpStatus.NOT_FOUND);
        }
        if (student.getRoomId() != null){
            return new ResponseEntity("Student has already room", HttpStatus.BAD_REQUEST);
        }

        student.setRoomId(room.getId());
        room.setReserved(true);

        return new ResponseEntity(room, HttpStatus.OK);
    }

    @GetMapping(pathDetail + "/unsubscribe")
    public Room cancelReservation(@PathVariable("id") Long id) {
        Room room = dao.get(id);

        if (room.getReserved()) {
            room.setReserved(false);
        }

        Student student = studentDao.getByRoom(id);
        student.setRoomId(null);

        return room;
    }

    @GetMapping(pathDetail)
    public ResponseEntity getRooms(@PathVariable("id") Long id) {

        Room room = dao.get(id);
        if (room == null) {
            return new ResponseEntity("No Room found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(room, HttpStatus.OK);
    }

    @PostMapping(value = path)
    public ResponseEntity createRoom(@RequestBody Room room) {

        dao.create(room);

        return new ResponseEntity(room, HttpStatus.CREATED);
    }

    @DeleteMapping(pathDetail)
    public ResponseEntity deleteRoom(@PathVariable Long id) {
        Room room = dao.get(id);
        if (null == room) {
            return new ResponseEntity("No Room found for ID " + id, HttpStatus.NOT_FOUND);
        }

        if (room.getReserved()) {
            return new ResponseEntity("Room is still subscribed " + id, HttpStatus.BAD_REQUEST);
        }

        dao.delete(id);

        return new ResponseEntity(id, HttpStatus.NO_CONTENT);

    }

    @PutMapping(pathDetail)
    public ResponseEntity updateGreeting(@PathVariable Long id, @RequestBody Room room) {

        room = dao.update(id, room);

        if (null == room) {
            return new ResponseEntity("No Room found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(room, HttpStatus.OK);
    }
}
