package com.maxrocky.train.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maxrocky.train.domain.type.Sex;
import com.maxrocky.train.domain.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String username;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String userAddress;

    private Sex sex;

    private UserStatus status;

}
