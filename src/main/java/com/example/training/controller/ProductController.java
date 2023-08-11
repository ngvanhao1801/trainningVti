package com.example.training.controller;

import com.example.training.dto.ProductDto;
import com.example.training.input.FilterProductInput;
import com.example.training.model.Product;
import com.example.training.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public ResponseEntity<?> selectAllProduct() {
    List<Product> product = productService.selectAllProduct();
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @GetMapping("/product/filters")
  public ResponseEntity<?> findSearchProduct(FilterProductInput input) {
    List<Product> productList = productService.findSearchProduct(input);
    return new ResponseEntity<>(productList, HttpStatus.OK);
  }

  @GetMapping("/products/details/{id}")
  public List<?> findProductDetail(@PathVariable("id") int productId) {
    return productService.findProductDetail(productId);
  }

  @GetMapping("/product")
  public List<?> findStatisProduct(@RequestParam("month") int month) {
    return productService.findStatisProduct(month);
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<?> selectProductById(@PathVariable("id") int id) {
    Product product = productService.selectProductById(id);
    return ResponseEntity.ok(product);
  }

  @PostMapping("/products")
  public ResponseEntity<?> insertProduct(@Valid @RequestBody ProductDto productDto) {
    int insertProduct = productService.insertProduct(productDto);
    return new ResponseEntity<>(insertProduct, HttpStatus.CREATED);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable("id") int id) {
    int updateProduct = productService.updateProduct(id, productDto);
    return new ResponseEntity<>(updateProduct, HttpStatus.OK);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable int id) {
    productService.deleteProductById(id);
    return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
  }
}
