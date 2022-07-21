package ru.inside.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Message extends BaseEntity {

    private String message;

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return message;
    }
}
