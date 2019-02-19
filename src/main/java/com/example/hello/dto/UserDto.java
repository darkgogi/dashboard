package com.example.hello.dto;

import com.example.hello.model.Athrztn;
import com.example.hello.model.Orgn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserDto {
    @NotNull
    private String userId;
    @NotNull
    private String userNm;
    private String dsc;
    @NotNull
    private String tel;
    @Email
    private String email;
    @NotEmpty
    private Orgn orgn;
    @NotEmpty
    private Athrztn athrztn;
    private String accessAllowIp;
    private String dplctLginAllowYn;
    private String lginLmtCd;
    private String userSttsCd;
    private String firstRgstrId;
    private String lastUpdtrId;
    private Date firstRgstTm;
    private Date lastUpdtTm;
}
