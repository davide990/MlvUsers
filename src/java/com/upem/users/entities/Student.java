package com.upem.users.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Entity
@Table(name = "student")
@XmlRootElement
@DiscriminatorValue("student")
public class Student extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserType getType() {
        return UserType.STUDENT;
    }
}
