package com.example.training.service;

import com.example.training.dto.ProductDto;
import com.example.training.dto.StatisProductDto;
import com.example.training.exception.DuplicateException;
import com.example.training.exception.NotFoundException;
import com.example.training.input.FilterProductInput;
import com.example.training.mapper.ProductMapper;
import com.example.training.model.Product;
import com.example.training.repository.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final ProductMapper productMapper;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
    this.productMapper = Mappers.getMapper(ProductMapper.class);
  }

  public List<Product> selectAllProduct() {
    List<Product> product = productRepository.selectAllProduct();
    if (product.isEmpty()) {
      throw new NotFoundException("Product does not exist");
    }
    return productRepository.selectAllProduct();
  }

  public Product selectProductById(int id) {
    if (!productRepository.isExistsById(id)) {
      throw new NotFoundException("Can't find product with id: " + id);
    }
    return productRepository.selectProductById(id);
  }

  public List<Product> findProductDetail(int productId) {
    return productRepository.findProductDetail(productId);
  }

  public int insertProduct(ProductDto productDto) {
    if (productRepository.isExistsByName(productDto.getName())) {
      throw new DuplicateException("Name Product already exists");
    }
    Product product = productMapper.toProduct(productDto);
    return productRepository.insertProduct(product);
  }

  public int updateProduct(int id, ProductDto productDto) {
    if (!productRepository.isExistsById(id)) {
      throw new NotFoundException("Product does not exist");
    }
    Product product = productMapper.toProduct(productDto);
    product.setId(id);
    return productRepository.updateProduct(product);
  }

  public List<StatisProductDto> findStatisProduct(int month) {
    return productRepository.findStatisProduct(month);
  }

  public List<Product> findSearchProduct(FilterProductInput input) {
    String keyword = input.getKeyword();
    Integer limit = input.getLimit();
    int offset;
    String sortBy = input.getSortBy();
    String sortType = input.getSortType();

    if (input.getKeyword() == null) {
      keyword = "";
    }

    if (input.getPage() < 1) {
      input.setPage(1);
    }

    if (limit == null || limit < 0) {
      limit = 1;
    }
    offset = (input.getPage() - 1) * limit;

    if (sortBy.equals("name")) {
      sortBy = "name";
    } else if (sortBy.equals("short_des")) {
      sortBy = "short_des";
    } else if (sortBy.equals("description")) {
      sortBy = "description";
    } else {
      sortBy = "id";
    }

    if (sortType != null && sortType.equalsIgnoreCase("ASC")) {
      sortType = "ASC";
    } else {
      sortType = "DESC";
    }
    return productRepository.findSearchProduct(keyword, limit, offset, sortBy, sortType);
  }

  public int deleteProductById(int id) {
    if (productRepository.deleteProductById(id) == 0) {
      throw new NotFoundException("Can't find product with id: " + id);
    }
    return productRepository.deleteProductById(id);
  }
}
