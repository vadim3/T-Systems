package store.rest;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dto.ProductDTO;
import store.objects.TopProductsSet;
import store.services.interfaces.ProductService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@RestController
@RequestMapping("/api/products/")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TopProductsSet topProductsSet;


//    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
//    public @JsonRawValue
//    @ResponseBody
//    String getTopTenProducts() {
//        Map<ProductDTO, Integer> products = productService.getTenBestSellersProduct();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
//        Gson gson = new Gson();
//
//        return gson.toJson(products);
//    }

//    @RequestMapping(value = "", method = RequestMethod.GET, produces="application/json")
//    public ResponseEntity<?> getTopTenProducts() {
//
//        class Product{
//            ProductDTO productDTO;
//            Integer integer;
//
//            public Product(ProductDTO productDTO, Integer integer) {
//                this.productDTO = productDTO;
//                this.integer = integer;
//            }
//
//            public ProductDTO getProductDTO() {
//                return productDTO;
//            }
//
//            public void setProductDTO(ProductDTO productDTO) {
//                this.productDTO = productDTO;
//            }
//
//            public Integer getInteger() {
//                return integer;
//            }
//
//            public void setInteger(Integer integer) {
//                this.integer = integer;
//            }
//        }
//        List<Product> productList = new ArrayList<>();
//
//        Map<ProductDTO, Integer> products = productService.getTenBestSellersProduct();
//
//        for (Map.Entry<ProductDTO, Integer> entry : products.entrySet())
//        {
//            productList.add(new Product(entry.getKey(), entry.getValue()));
//        }
//
//        if (products.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
//
//        return new ResponseEntity<List<Product>>(productList, headers, HttpStatus.OK);
//    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> getTopTenProducts() {
        Set<ProductDTO> productList = topProductsSet.getTopSet();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        return new ResponseEntity<>(productList, headers, HttpStatus.OK);
    }

//    @RequestMapping(value = "", method = RequestMethod.GET, produces="application/json")
//    public ResponseEntity<?> getTopTenProducts() {
//
//
//        Map<ProductDTO, Integer> products = productService.getTenBestSellersProduct();
//
//        if (products.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
//
//        return new ResponseEntity<Map<ProductDTO, Integer>>(products, headers, HttpStatus.OK);
//    }


//    @RequestMapping(value = "", method = RequestMethod.GET, produces= { "application/json", "application/xml" })
//    @ResponseBody
//    public ProductDTO getTopTenProducts() {
//
//
//        Map<ProductDTO, Integer> products = productService.getTenBestSellersProduct();
//
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setProductId(23);
//        productDTO.setVolume(45);
//
//        if (products.isEmpty()) {
//            return null;
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
//
//        return productDTO;
//    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Integer id) {


        ProductDTO product = productService.getEntityById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        return new ResponseEntity<ProductDTO>(product, headers, HttpStatus.OK);
    }
}
