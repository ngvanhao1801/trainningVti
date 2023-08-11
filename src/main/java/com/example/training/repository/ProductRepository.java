package com.example.training.repository;

import com.example.training.dto.StatisProductDto;
import com.example.training.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {

  int insertProduct(Product product);

  int updateProduct(Product product);

  Product selectProductById(int id);

  List<Product> findProductDetail(int productId);

  List<StatisProductDto> findStatisProduct(int month);

  List<Product> selectAllProduct();

  int deleteProductById(int id);

  boolean isExistsByName(String name);

  boolean isExistsById(int id);

  List<Product> findSearchProduct(String keyword, Integer limit, Integer offset, String sortBy, String sortType);

}
