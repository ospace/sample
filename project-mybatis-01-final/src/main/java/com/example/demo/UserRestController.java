
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable("id")String id) {
        return userMapper.findById(id);
    }

    @RequestMapping("/save")
    public void addUser(@RequestParam(name="id")String id, @RequestParam(name="name")String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        userMapper.save(user);
    }
}
