package com.example.training.controller;

import com.example.training.model.User;
import com.example.training.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> selectAllUser() {
    return userService.selectAllUser();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<?> selectUserById(@PathVariable("id") int id) {
    User user = userService.selectUserById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/users")
  public ResponseEntity<?> insertUser(@Valid @RequestBody User user) {
    int result = userService.insertUser(user);
    if (result == 1) return ResponseEntity.ok("Insert Successfully");
    else return ResponseEntity.ok("Insert Failed");
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
    user.setId(id);
    int result = userService.updateUser(user);

    if (result == 1) return ResponseEntity.ok("Update successfully");
    else return ResponseEntity.ok("Update Failed");
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable int id) {
    userService.deleteUserById(id);
    return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
  }
}
