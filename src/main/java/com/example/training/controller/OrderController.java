package com.example.training.controller;

import com.example.training.customAnnotation.CurrentUser;
import com.example.training.dto.CartDto;
import com.example.training.dto.OrderDetailDto;
import com.example.training.security.UserPrincipal;
import com.example.training.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping("/cart")
  public ResponseEntity<String> addProductToCart(@RequestBody OrderDetailDto orderDetailDto, @CurrentUser UserPrincipal userPrincipal) {
    int result = orderService.addProductToCart(orderDetailDto, userPrincipal.getId());
    if (result == 1) return ResponseEntity.ok("Add product successfully");
    else return ResponseEntity.badRequest().body("Add product fail");
  }

  @DeleteMapping("/cart/{orderId}/{productId}")
  public ResponseEntity<String> deleteProductFromCart(@PathVariable("orderId") int orderId, @PathVariable("productId") int productId) {
    int result = orderService.deleteProductFromCart(productId, orderId);
    if (result == 1) return ResponseEntity.ok("Delete product successfully");
    else return ResponseEntity.badRequest().body("Delete product fail");
  }
@GetMapping("/cart")
  public ResponseEntity<CartDto> getCart(@CurrentUser UserPrincipal userPrincipal) {
    CartDto cartDto = orderService.getCart(userPrincipal.getId());
    return ResponseEntity.ok(cartDto);
  }
}
