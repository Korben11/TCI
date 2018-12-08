package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.dao.StudentDao;
import service.models.Student;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentDao dao;
    private static final String path = "/students";
    private static final String pathDetail = path + "/{id}";

    @GetMapping(path)
    public List getStudents() { return dao.list(); }

    @GetMapping(pathDetail)
    public ResponseEntity getStudents(@PathVariable("id") Long id) {

        Student student = dao.get(id);
        if (student == null) {
            return new ResponseEntity("No Student found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(student, HttpStatus.OK);
    }

    @PostMapping(value = path)
    public ResponseEntity createStudent(@RequestBody Student student) {

        dao.create(student);

        return new ResponseEntity(student, HttpStatus.CREATED);
    }

    @DeleteMapping(pathDetail)
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        Student student = dao.get(id);
        if (null == student) {
            return new ResponseEntity("No Student found for ID " + id, HttpStatus.NOT_FOUND);
        }
        if (null != student.getRoomId()) {
            return new ResponseEntity("Student is still subscribed to room id " + student.getRoomId(), HttpStatus.FAILED_DEPENDENCY);
        }

        dao.delete(id);
        return new ResponseEntity(id, HttpStatus.NO_CONTENT);

    }

    @PutMapping(pathDetail)
    public ResponseEntity updateGreeting(@PathVariable Long id, @RequestBody Student student) {

        student = dao.update(id, student);

        if (null == student) {
            return new ResponseEntity("No Student found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(student, HttpStatus.OK);
    }
}
