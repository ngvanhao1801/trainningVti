package com.example.training.controller;


import com.example.training.model.Role;
import com.example.training.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {
  private final RoleService roleService;

  @GetMapping
  public List<Role> getAllRole() {
    return roleService.getAllRoles();
  }

  @PostMapping
  public ResponseEntity<Object> insert(@RequestBody Role role) {
    int result = roleService.insert(role);

    if (result == 1) return ResponseEntity.ok("Insert successfully");
    else return ResponseEntity.ok("Insert fail");
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@RequestBody Role role, @PathVariable("id") Integer id) {
    role.setId(id);
    int result = roleService.update(role);

    if (result == 1) return ResponseEntity.ok("Update successfully");
    else return ResponseEntity.ok("Update fail");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
    int result = roleService.delete(id);
    if (result == 1) return ResponseEntity.ok("Delete successfully");
    else return ResponseEntity.ok("Delete fail");
  }

  @GetMapping("/roles/{id}")
  public ResponseEntity<Role> findById(@PathVariable("id") Integer id) {
    Role role = roleService.findById(id);
    return ResponseEntity.ok(role);
  }
}
