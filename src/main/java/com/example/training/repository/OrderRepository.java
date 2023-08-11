package com.example.training.repository;
import com.example.training.dto.OrderDetailDto;
import com.example.training.dto.ProductCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderRepository {
  boolean isExistByUserAndStatus(int userId, int status);

  boolean isExistsById(int id);

  int insertOrder(int userId, String note);

  int addProductToCart(OrderDetailDto data);

  int findByUserAndStatus(int userId, int status);

  int deleteProductFromCart(int productId, int orderId);
  int findCartByUser(int userId);
  List<ProductCart> getProductsFromCart(int orderId);
}
