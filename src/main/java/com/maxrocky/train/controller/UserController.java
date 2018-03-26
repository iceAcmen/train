package com.maxrocky.train.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maxrocky.train.repository.UserRepository;
import com.maxrocky.train.domain.User;
import com.maxrocky.train.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

    @Resource
    UserRepository userRepository;

    @PostMapping
    public UserDTO save(@RequestBody @Validated UserDTO userDTO){
        int line = userRepository.save(userDTO);
        return userDTO;
    }

    @DeleteMapping(value = "/{id}")
    public String remove(@PathVariable @NotNull(message = "用户ID不能为空") Integer id) {
        int line =  userRepository.remove(id);
        return "success";
    }

    @PutMapping
    public String update(@RequestBody @Validated UserDTO userDTO){
       int line = userRepository.update(userDTO);
        return "success";
    }

    @GetMapping(value = "/{id}")
    public UserDTO getById(@NotNull(message = "用户ID不能为空") Integer id) {
        User user = userRepository.getById(id);
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    @GetMapping(value = "/listUser")
    public List<UserDTO> listUser(@RequestParam @NotNull(message = "请输入页码") Integer pageNum,
                                  @RequestParam @NotNull(message = "请输入每页最大显示记录数") Integer pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userRepository.listUser();
        List<UserDTO> userDTOList = userList.stream()
                .map(p -> new UserDTO(p))
                .collect(Collectors.toList());
        return userDTOList;
    }

    @PostMapping("/queryByCondition")
    public List<UserDTO> queryUser(@RequestBody @Validated UserDTO userDTO) {
        List<User> queryResult = userRepository.queryUser(userDTO);
        List<UserDTO> userDTOList = queryResult.stream()
                .map(p -> new UserDTO(p))
                .collect(Collectors.toList());
        return userDTOList;
    }

    @GetMapping(value = "/listUserByIds")
    public List<UserDTO> listUserByIds(@RequestParam @NotNull(message = "用户ID不能为空") List<String> ids) {
        List<User> queryResult = userRepository.listUserByIds(ids);
        List<UserDTO> userDTOList = queryResult.stream()
                .map(p -> new UserDTO(p)).collect(Collectors.toList());
        return userDTOList;
    }

    @GetMapping(value = "/getByIdOrByUsername")
    public UserDTO getByIdOrByUsername(@RequestParam(required = false) Integer id,
                                       @RequestParam(required = false) String username) {
        User queryResult = userRepository.getByIdOrByUsername(id, username);
        UserDTO userDTO = new UserDTO(queryResult);
        return userDTO;
    }
}
