package service.dao;

import org.springframework.stereotype.Component;
import service.models.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDao {

    private static List<Student> students;
    {
        students = new ArrayList<Student>();
        students.add(new Student(101L, "John", null));
        students.add(new Student(201L, "Russ", 201L));
        students.add(new Student(301L, "Kate", null));
    }

    public List list() { return students; }

    public Student get(Long id) {

        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Student getByRoom(Long id) {

        for (Student student : students) {
            if (student.getRoomId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Student create(Student student) {
        student.setId(System.currentTimeMillis());
        students.add(student);
        return student;
    }

    public Long delete(Long id) {

        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                return id;
            }
        }

        return null;
    }

    public Student update(Long id, Student studentNew) {

        for (Student student : students) {
            if (student.getId().equals(id)) {
                studentNew.setId(student.getId());
                students.remove(student);
                students.add(studentNew);
                return studentNew;
            }
        }

        return null;
    }
}
