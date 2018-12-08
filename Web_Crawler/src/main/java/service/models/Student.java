package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    private Long id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long roomId;

    public Student() {
    }

    public Student(Long id, String name, Long roomId) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
