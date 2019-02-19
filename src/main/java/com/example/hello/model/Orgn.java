package com.example.hello.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Orgn {

    @Id
    private Integer orgnNo;
    private String orgnId;
    private String orgnNm;
    private String orgnDsc;
}
