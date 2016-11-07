package com.upem.users.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Entity
@Table(name = "teacher")
@XmlRootElement
@DiscriminatorValue("teacher")
@PrimaryKeyJoinColumn(name="id")
public class Teacher extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserType getType() {
        return UserType.TEACHER;
    }

}
