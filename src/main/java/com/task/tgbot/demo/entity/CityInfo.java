package com.task.tgbot.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "CITY_INFO")
public class CityInfo {
    @Id
    @Column
    @JsonIgnore
    /*
    * not all databases support uot-of-box case insensitive lookups. use technical field as PK for this purposes
     */
    private String id;
    @Column
    @NotNull
    @Size(min = 3, max = 63)
    @Pattern(regexp = "^[^\\s]+[\\p{L}\\s]+[^\\s]$")
    private String name;

    @Column
    @NotNull
    @Size(min = 3, max = 255)
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.id = name == null ? null : name.toLowerCase(); // id is name in lower case.
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityInfo cityInfo = (CityInfo) o;
        return Objects.equals(id, cityInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
