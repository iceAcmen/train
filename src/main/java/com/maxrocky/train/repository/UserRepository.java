package com.maxrocky.train.repository;

import com.maxrocky.train.domain.User;
import com.maxrocky.train.dto.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 */
public interface UserRepository {

    @Insert("INSERT INTO `user` (username, age, birthday, user_address, sex, status) " +
            "VALUES (#{username}, #{age}, #{birthday}, #{userAddress}, #{sex}, 'NORMAL')")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(UserDTO userDTO);

    //@Delete("DELETE FROM `user` WHERE id = #{id}")
    @Update("UPDATE `user` SET status = 'REMOVED' WHERE id = #{id}")
    int remove(@Param(value = "id") Integer id);

    @Update("<script>" +
            "UPDATE `USER` " +
            "<set>" +
            "<if test = 'null != username'> username = #{username}, </if>" +
            "<if test = 'null != age'> age = #{age}, </if>" +
            "<if test = 'null != birthday'> birthday = #{birthday}, </if>" +
            "<if test = 'null != userAddress'> user_address = #{userAddress}, </if>" +
            "<if test = 'null != sex'> sex = #{sex}, </if>" +
            "<if test = 'null != status'> status = #{status}, </if>" +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(UserDTO userDTO);

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User getById(Integer id);

    @Select("SELECT * FROM `user`")
    List<User> listUser();

    @Select("<script>" +
            "SELECT * FROM `user` " +
            "<where>" +
            "<if test = 'null != id'> AND id = #{id} </if>" +
            "<if test = 'null != username'> " +
                "<bind name = 'username' value = \"'%' + username + '%'\"/>" +
                " AND username like  #{username} " +
            "</if>" +
            "<if test = 'null != age'> AND age = #{age} </if>" +
            "<if test = 'null != birthday'> AND birthday = #{birthday} </if>" +
            "<if test = 'null != userAddress'>" +
                "<bind name='userAddress' value = \"'%' + userAddress + '%'\"/>" +
                " AND user_address like #{userAddress} " +
            "</if>" +
            "<if test = 'null != sex'>AND sex = #{sex} </if>" +
            "<if test = 'null != status'>AND status = #{status} </if>" +
            "</where>" +
            "</script>")
    List<User> queryUser(UserDTO userDTO);

    @Select("<script>" +
            "SELECT * FROM `user` WHERE id in " +
            "<foreach item = 'item' collection = 'list' open = '(' close = ')' separator = ','>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<User> listUserByIds(@Param(value = "list") List<String> ids);

    @Select("<script>" +
            "SELECT * FROM `user` " +
            "<where>" +
            "<choose>" +
            "<when test = 'null != id'> id = #{id} </when>" +
            "<when test = 'null != username'> username = #{username} </when>" +
            "<otherwise> AND status = 'NORMAL' </otherwise>" +
            "</choose>" +
            "</where>" +
            "</script>")
    User getByIdOrByUsername(@Param(value = "id") Integer id, @Param(value = "username") String username);
}
