package com.gov.appDemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

    @Id 
    @Column(columnDefinition = "varchar(100)")
    String email;
    
    @Column(columnDefinition = "varchar(100)")
    String name;
    String graduation;
    String degree;
}
