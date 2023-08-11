package com.example.training.service;

import com.example.training.dto.CartDto;
import com.example.training.dto.OrderDetailDto;
import com.example.training.dto.ProductCart;
import com.example.training.dto.ProductDto;
import com.example.training.exception.InvalidException;
import com.example.training.exception.NotFoundException;
import com.example.training.repository.OrderRepository;
import com.example.training.repository.ProductRepository;
import com.example.training.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;


  public int addProductToCart(OrderDetailDto data, int userId) {
    if (!orderRepository.isExistByUserAndStatus(userId, 0)) {
      orderRepository.insertOrder(userId, "create Cart");
    }

    int orderId = orderRepository.findByUserAndStatus(userId, 0);
    data.setOrderId(orderId);

    if (!productRepository.isExistsById(data.getProductId())) throw new NotFoundException("Product does not exist");
    if (data.getQuantity() <= 0) throw new InvalidException("Quantity must be greater than 0");

    return orderRepository.addProductToCart(data);
  }

  public int deleteProductFromCart(int productId, int orderId) {
    if (!productRepository.isExistsById(productId)) throw new NotFoundException("Product does not exist");
    if (!orderRepository.isExistsById(orderId)) throw new NotFoundException("Order does not exist");
    return orderRepository.deleteProductFromCart(productId, orderId);
  }

  @Override
  public CartDto getCart(int userId) {
    CartDto cartDto = new CartDto();
    if (!userRepository.isExistsById(userId)) throw new NotFoundException("User does not exist!");

    int orderId = orderRepository.findCartByUser(userId);
    List<ProductCart> productCarts = orderRepository.getProductsFromCart(orderId);

    double total = productCarts.stream().mapToDouble(x -> x.getQuantity() * x.getPrice()).sum();

    cartDto.setOrderId(orderId);
    cartDto.setTotal(total);
    cartDto.setProducts(productCarts);

    return cartDto;
  }
}
