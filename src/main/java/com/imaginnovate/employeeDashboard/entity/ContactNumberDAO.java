package com.imaginnovate.employeeDashboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Contact")
public class ContactNumberDAO {
    @Id
    private Long id;
    @Column(name = "phone_number")
    private Long phoneNumber;
}
