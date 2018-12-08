package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {
    private Long id;
    private String city;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean reserved = false;

    public Room() { }

    public Room(Long id, String city, Boolean reserved) {
        this.id = id;
        this.city = city;
        this.reserved = reserved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
}
