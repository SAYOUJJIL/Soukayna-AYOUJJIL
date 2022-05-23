package org.sid.ecomapp.service;

import org.sid.ecomapp.dtos.ProductDTO;

import java.util.List;

public interface IProductService {
     ProductDTO save(ProductDTO productDTO);
     List<ProductDTO> productsList();
     ProductDTO getProduct(String id);
     ProductDTO updateProduct(ProductDTO productDTO);
     void deleteProduct(String id);
}
