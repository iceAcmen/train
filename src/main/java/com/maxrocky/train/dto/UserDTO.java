package com.maxrocky.train.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maxrocky.train.domain.type.Sex;
import com.maxrocky.train.domain.User;
import com.maxrocky.train.domain.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    private String username;

    @Range(max = 200, min = 0, message = "年龄在0-200之间")
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String userAddress;

    private Sex sex;

    private UserStatus status;

    public UserDTO(User user) {
        if (null == user) {
            return;
        }
        this.setId(user.getId());
        this.setAge(user.getAge());
        this.setUsername(user.getUsername());
        this.setBirthday(user.getBirthday());
        this.setUserAddress(user.getUserAddress());
        this.setSex(user.getSex());
        this.setStatus(user.getStatus());
    }

}
