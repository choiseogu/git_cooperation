package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public List<UserEntity> findUser(Model model) {
        List<UserEntity> user = userService.findAllUser();

        return user;
    }

    @GetMapping("/user/{id}")
    public String showUserIdx(@PathVariable("id") String id, Model model) {
        UserEntity Id = userService.findOne(id);
        model.addAttribute("user", Id);
        return "home";
    }

    @GetMapping("/userjson/{id}")
    @ResponseBody
    public UserEntity findUserIdx(@PathVariable("id") String id, Model model) {
        UserEntity Id = userService.findOne(id);
        return Id;
    }

    @PutMapping("/create")
    @ResponseBody
    public String createUser(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "pw") String pw,
            @RequestParam(value = "nn") String nickname,
            @RequestParam(value = "addr") String address
    ) {
        String code = UUID.randomUUID().toString();
        String date = LocalDateTime.now().toString();

        UserEntity user = new UserEntity();
        user.setUser_code(code);
        user.setId(id);
        user.setPw(pw);
        user.setNickname(nickname);
        user.setAddress(address);
        user.setCreated_date(date);
        userService.saveUser(user);

        return "data save complete";
    }

    @PutMapping("/update_addr")
    @ResponseBody
    public String update_addr(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "new_addr") String new_addr
    ){

        userService.update_addr(id, new_addr);

        return "update_addr complete";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return "delete complete";
    }
}
