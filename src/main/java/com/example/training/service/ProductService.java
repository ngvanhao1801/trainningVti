package com.example.training.service;

import com.example.training.dto.ProductDto;
import com.example.training.dto.StatisProductDto;
import com.example.training.input.FilterProductInput;
import com.example.training.model.Product;

import java.util.List;

public interface ProductService {

  int deleteProductById(int id);

  int insertProduct(ProductDto productDto);

  List<Product> selectAllProduct();

  Product selectProductById(int id);

  List<Product> findProductDetail(int productId);

  List<StatisProductDto> findStatisProduct(int month);

  int updateProduct(int id, ProductDto productDto);

  List<Product> findSearchProduct(FilterProductInput input);

}
