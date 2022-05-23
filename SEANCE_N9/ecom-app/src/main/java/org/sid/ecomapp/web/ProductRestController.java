package org.sid.ecomapp.web;

import org.sid.ecomapp.dtos.ProductDTO;
import org.sid.ecomapp.entities.Product;
import org.sid.ecomapp.repositories.ProductRepository;
import org.sid.ecomapp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IProductService iProductService;
    //j'attend une requete http avec get : client
    @GetMapping(path = "/products" )
    /*public List<Product> productsList(){
        return productRepository.findAll();
    }*/
    public List<ProductDTO> productsList(){
        return iProductService.productsList();
    }

    @GetMapping(path = "/products/{id}")
    // tu prend la valeur de ce paramaetre qui vient dans ce path et l'affecter a cet variable pour les id et identifiant on met path param variable
    /*public Product getProduct(@PathVariable(name = "id") String id){
        return productRepository.findById(id).get();
    }
*/
    public ProductDTO getProduct(@PathVariable(name = "id") String id){
        return iProductService.getProduct(id);
    }

    @PostMapping(path = "/products")
    //RequestBody regarder les corps de la requete vous allez trouver des infos
    /*public Product saveProduct(@RequestBody Product p){
        p.setId(UUID.randomUUID().toString());
        return productRepository.save(p);
    }*/
    public ProductDTO saveProduct(@RequestBody ProductDTO p){
        return iProductService.save(p);
    }


    @PutMapping(path = "/products/{id}")
    /*public Product updateProduct(@RequestBody Product p,@PathVariable(name = "id") String id){
        p.setId(id);
        return productRepository.save(p);
    }*/
    public ProductDTO updateProduct(@RequestBody ProductDTO p,@PathVariable(name = "id") String id){
        p.setId(id);
        return iProductService.updateProduct(p);
    }

    @DeleteMapping(path = "/products/{id}")
   /* public void deleteProduct(@PathVariable(name = "id") String id){
        productRepository.deleteById(id);
    }*/
    public void deleteProduct(@PathVariable(name = "id") String id){
        iProductService.deleteProduct(id);
    }

}
