package com.example.hello.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USERLIST")
@Getter
@Setter
@ToString
public class User {

    @Id
    private String userId;

    private String userNm;
    private String dsc;
    private String tel;
    private String email;
    private String accessAllowIp;
    private String dplctLginAllowYn;
    private String lginLmtCd;
    private String userSttsCd;

    @ManyToOne
    @JoinColumn(name="orgn_no")
    private Orgn orgn;

    @ManyToOne
    @JoinColumn(name="athrztn_no")
    private Athrztn athrztn;

    @Column(updatable = false)
    private String firstRgstrId;
    @Column(updatable = false)
    private Date firstRgstTm;

    private String lastUpdtrId;
    private Date lastUpdtTm;
}
