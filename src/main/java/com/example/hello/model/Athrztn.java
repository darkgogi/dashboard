package com.example.hello.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Athrztn {

    @Id
    private Integer athrztnNo;
    private String athrztnNm;
    private String athrztnDsc;

    @Column(updatable = false)
    private LocalDateTime firstRgstTm;
}
