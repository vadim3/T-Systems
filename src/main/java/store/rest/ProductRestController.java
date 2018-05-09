package store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dto.ProductDTO;
import store.services.interfaces.ProductService;

import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@RestController
@RequestMapping("/api/products/")
public class ProductRestController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Map<ProductDTO, Integer>> getTopTenProducts() {


        Map<ProductDTO, Integer> products = productService.getTenBestSellersProduct();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        return new ResponseEntity<>(products, headers, HttpStatus.OK);
    }


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
}
