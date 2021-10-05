package com.example.access.access.service;

import com.example.access.access.entity.Product;
import com.example.access.access.entity.ProductStatus;
import com.example.access.access.repository.ProductStatusRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProductStatusService {
    @Autowired
    private ProductStatusRepository productStatusRepository;

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name="productService",fallbackMethod = "productFallBack")
    public ProductStatus getProduct(Long id) {

        Product product = restTemplate.getForObject("http://localhost:8085/"+id, Product.class);
        Product product1 = product;
        ProductStatus productStatus= new ProductStatus(new Long(product1.getId()) ,product1.getPname(), product1.getPrice(), false);
        return  productStatusRepository.save(productStatus);
    }
    public ProductStatus productFallBack(Exception e){

        return  new ProductStatus() ;
    }

    @CircuitBreaker(name="productService",fallbackMethod = "productFallBack")
    public ProductStatus updateProduct(Long id) {

        Optional<ProductStatus> pr=productStatusRepository.findById(id);
        ProductStatus e=pr.get();
        if(e!=null){
          e.setSold(true);
          return productStatusRepository.save(e);
        }
        else {
            Product product = restTemplate.getForObject("http://localhost:8085/" + id, Product.class);
            Product product1 = product;
            ProductStatus productStatus = new ProductStatus(new Long(product1.getId()), product1.getPname(), product1.getPrice(), true);
            return productStatusRepository.save(productStatus);
        }

    }
}
