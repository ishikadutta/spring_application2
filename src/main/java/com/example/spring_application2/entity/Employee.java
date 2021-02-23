package com.example.spring_application2.entity;

import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
@Getter
@Setter
@Document
public class Employee {
//    @Id
//    @GenericGenerator(name="employee_id_seq", strategy="increment")
//    @GeneratedValue(generator = "employee_id_seq",strategy = GenerationType.AUTO)
    @MongoId
    private Long id;
    private String name;
    private String departmentName;
}
