package com.example.access.access.controller;


import com.example.access.access.entity.ProductStatus;
import com.example.access.access.service.ProductStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductStatusController {

   private final ProductStatusService productStatusService;

    public ProductStatusController(ProductStatusService productStatusService) {
        this.productStatusService = productStatusService;
    }


    @GetMapping("/{id}")
      public ProductStatus getProduct(@PathVariable("id") Long id){

        return productStatusService.getProduct(id);
    }

    @GetMapping("/sold/{id}")
    public ProductStatus updateProduct(@PathVariable("id") Long id){
        return productStatusService.updateProduct(id);
    }


}
