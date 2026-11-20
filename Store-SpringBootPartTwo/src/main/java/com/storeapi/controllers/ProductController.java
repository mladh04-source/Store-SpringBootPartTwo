package com.storeapi.controllers;

import com.storeapi.dtos.ProductDto;
import com.storeapi.entities.Product;
import com.storeapi.mappers.ProductMapper;
import com.storeapi.repositories.CategoryRepository;
import com.storeapi.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;
@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(required = false, name = "categoryId") Byte categoryId
    ){
        List<Product> products;
        if(categoryId != null){
            products = productRepository.findByCategoryId(categoryId);
        }else {
            products = productRepository.findAll();
        }

    return products.stream().map(product -> productMapper.toDto(product)).toList();
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody  ProductDto productDto,
                                                    UriComponentsBuilder uriBuilder){
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null){
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);

    }



}
