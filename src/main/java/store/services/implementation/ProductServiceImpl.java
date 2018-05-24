package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductDAO;
import store.dto.OrderDTO;
import store.dto.ProductDTO;
import store.entities.Order;
import store.entities.Product;
import store.exceptions.DAOException;
import store.exceptions.OrderNotFoundException;
import store.exceptions.ProductNotFoundException;
import store.services.interfaces.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductVendorService productVendorService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @Override
    @Transactional
    public ProductDTO getProductByName(String name) {
        return entityDTOMapper.mapDTOFromProduct(productDAO.getProductByName(name));
    }

    @Override
    @Transactional
    public List<ProductDTO> getProductByComplex(String categoryName, String vendorName, String minPrice, String maxPrice, String page) {
        if (page == null) page = "1";
        List<Product> productList = productDAO.getAllProductByComplex(categoryName, vendorName, minPrice, maxPrice, page);
        return productList.stream().map(product -> entityDTOMapper.mapDTOFromProduct(product)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDTO> getProductByCategory(String name) {
        List<Product> productList = productDAO.getAllProductByCategory(name);
        return productList.stream().map(product -> entityDTOMapper.mapDTOFromProduct(product)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDTO> getProductByVendor(String name) {
        List<Product> productList = productDAO.getAllProductByVendor(name);
        return productList.stream().map(product -> entityDTOMapper.mapDTOFromProduct(product)).collect(Collectors.toList());
    }

//    @Override
//    @Transactional
//    public void updateAllFieldsProduct(String productId, String name, String price, String stockQuintity, String productCategory,
//                                       String productVendor, String description, String imagePath, String weight, String volume,
//                                       String color, String power) {
//        Product product = getEntityById(Integer.parseInt(productId));
//        product.setName(name);
//        product.setPrice(Double.parseDouble(price));
//        product.setStockQuantity(Integer.parseInt(stockQuintity));
//        product.setProductCategory(productCategoryService.getProductCategoryByName(productCategory));
//        product.setProductVendor(productVendorService.getProductVendorByName(productVendor));
//        product.setDescription(description);
//        product.setImagePath(imagePath);
//        product.setWeight(Double.parseDouble(weight));
//        product.setVolume(Double.parseDouble(volume));
//        product.setColor(color);
//        product.setPower(Double.parseDouble(power));
//        updateEntity(product);
//    }



//    @Override
//    @Transactional
//    public void createNewProduct(String name, String price, String stockQuintity, String productCategory, String productVendor, String description, String imagePath, String weight, String volume, String color, String power) {
//        Product product = new Product();
//        product.setName(name);
//        product.setPrice(Double.parseDouble(price));
//        product.setStockQuantity(Integer.parseInt(stockQuintity));
//        product.setProductCategory(productCategoryService.getProductCategoryByName(productCategory));
//        product.setProductVendor(productVendorService.getProductVendorByName(productVendor));
//        product.setDescription(description);
//        product.setImagePath(imagePath);
//        product.setWeight(Double.parseDouble(weight));
//        product.setVolume(Double.parseDouble(volume));
//        product.setColor(color);
//        product.setPower(Double.parseDouble(power));
//        createEntity(product);
//    }


    @Override
    @Transactional
    public Map<ProductDTO, Integer> getTenBestSellersProduct() throws OrderNotFoundException {
        Map<ProductDTO, Integer> map = new HashMap<>();
        Map<ProductDTO, Integer> resultMap = new LinkedHashMap<>();
        Map<ProductDTO, Integer> resultMapDTO = new LinkedHashMap<>();

        for (OrderDTO order : orderService.getAll()){
            for (Map.Entry<ProductDTO, Integer> entry : order.getProducts().entrySet()){
                if (map.containsKey(entry.getKey())){
                    map.put(entry.getKey(), map.get(entry.getKey()) + entry.getValue());
                } else {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }

        ProductDTO productChoice = new ProductDTO();
        for (int i = 0; i < 10; i++){
            int max = 0;
            if (map.isEmpty()) break;
            for (Map.Entry<ProductDTO, Integer> entry : map.entrySet()){
                if (entry.getValue() > max){
                    max = entry.getValue();
                    productChoice = entry.getKey();
                }
            }
            resultMap.put(productChoice, max);
            map.remove(productChoice);
        }
        for (Map.Entry<ProductDTO, Integer> entry : resultMap.entrySet())
        {
            resultMapDTO.put(entry.getKey(),entry.getValue());
        }
        return resultMapDTO;
    }

    @Override
    @Transactional
    public List<ProductDTO> getTenBestSellersProductList() {
        List<ProductDTO> resultList = new ArrayList<>();

        try {
            for (Map.Entry<ProductDTO, Integer> entry : getTenBestSellersProduct().entrySet())
            {
                resultList.add(entry.getKey());
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    @Transactional
    public int paginationPages(String categoryName, String vendorName, String minPrice, String maxPrice, String page) {
        try {
            return productDAO.paginationPages(categoryName, vendorName, minPrice, maxPrice, page);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Transactional
    public int itemsQuintity(String categoryName, String vendorName, String minPrice, String maxPrice, String page) {
        try {
            return productDAO.getAllProductByComplex(categoryName, vendorName, minPrice, maxPrice, null).size();
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Transactional
    public void createEntity(ProductDTO productDTO) throws DAOException {
        Product product = entityDTOMapper.mapProductFromDTO(productDTO);
        productDAO.create(product);
    }

    @Override
    @Transactional
    public ProductDTO getEntityById(Integer id) throws DAOException {
        return entityDTOMapper.mapDTOFromProduct(productDAO.read(id));
    }

    @Override
    @Transactional
    public void updateEntity(ProductDTO productDTO) throws DAOException {
        Product product = entityDTOMapper.mapProductFromDTO(productDTO);
        productDAO.update(product);
    }

    @Override
    @Transactional
    public void deleteEntity(ProductDTO productDTO) throws DAOException {
        productDAO.delete(entityDTOMapper.mapProductFromDTO(productDTO));
    }

    @Override
    @Transactional
    public List<ProductDTO> getAll() throws DAOException {
        List<Product> productList =  productDAO.getAll();
        return productList.stream().map(product -> entityDTOMapper.mapDTOFromProduct(product)).collect(Collectors.toList());
    }
}
