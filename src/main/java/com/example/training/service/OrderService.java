package com.example.training.service;

import com.example.training.customAnnotation.CurrentUser;
import com.example.training.dto.CartDto;
import com.example.training.dto.OrderDetailDto;
import com.example.training.security.UserPrincipal;

public interface OrderService {
  int addProductToCart(OrderDetailDto data, int userId);

  int deleteProductFromCart(int productId, int orderId);
  CartDto getCart(int userId);
}
