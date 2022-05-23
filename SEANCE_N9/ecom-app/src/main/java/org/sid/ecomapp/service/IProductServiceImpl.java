package org.sid.ecomapp.service;

import org.sid.ecomapp.dtos.ProductDTO;
import org.sid.ecomapp.entities.Category;
import org.sid.ecomapp.entities.Product;
import org.sid.ecomapp.mappers.CatalogMappers;
import org.sid.ecomapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class IProductServiceImpl implements IProductService {
    @Autowired
    //injection des dep
    private ProductRepository productRepository;
    @Autowired
    private CatalogMappers catalogMappers;
    @Override
    public ProductDTO save(ProductDTO productDTO) {
       Product product =  catalogMappers.fromProductDTO(productDTO);
        product.setId(UUID.randomUUID().toString());
        Product savedProduct = productRepository.save(product);
       return catalogMappers.fromProduct(savedProduct);
    }

    @Override
    public List<ProductDTO> productsList() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream().map(
                p->catalogMappers.fromProduct(p)).collect(Collectors.toList());
        return productDTOS;
    }

    @Override
    public ProductDTO getProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Introuvable"));
        ProductDTO productDTO = catalogMappers.fromProduct(product);
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
       Product product = catalogMappers.fromProductDTO(productDTO);
       Product updatedProduct = productRepository.save(product);
       ProductDTO productDTO1 = catalogMappers.fromProduct(updatedProduct);
       return productDTO1;
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
