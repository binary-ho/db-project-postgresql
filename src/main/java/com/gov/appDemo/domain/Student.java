package com.gov.appDemo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Student {

    @Id
    String email;
    String name;
    String graduation;
    String degree;
}
