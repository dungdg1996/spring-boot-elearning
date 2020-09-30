package com.myclass.elearning.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "order_table")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "course_id")
    private int courseId;
    private int orderStatus;
    private Date creatDate;
    private Date lastUpdate;
}
