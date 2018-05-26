package store.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductCategoryDAO;
import store.dao.interfaces.ProductDAO;
import store.dto.ProductCategoryDTO;
import store.entities.Product;
import store.entities.ProductCategory;
import store.exceptions.DAOException;
import store.exceptions.DuplicateProductCategoryException;
import store.exceptions.DuplicateUserException;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.ProductCategoryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Slf4j
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    EntityDTOMapper entityDTOMapper;

    @Override
    @Transactional
    public void createEntity(ProductCategoryDTO productCategoryDTO) throws DAOException {

        if (productCategoryDAO.getProductCategoryByName(productCategoryDTO.getName()) != null){
            DAOException ex = new DAOException("Category with the same name is already exists");
            log.error("error",ex);
            throw ex;
        }
        ProductCategory productCategory = new ProductCategory();
        entityDTOMapper.mapProductCategoryFromDTO(productCategory, productCategoryDTO);
        productCategoryDAO.create(productCategory);
        log.info("Creating new Category " + productCategory);
    }

    @Override
    @Transactional
    public ProductCategoryDTO getEntityById(Integer id) throws DAOException {
        return entityDTOMapper.mapDTOFromProductCategory(productCategoryDAO.read(id));
    }

    @Override
    @Transactional
    public void updateEntity(ProductCategoryDTO productCategoryDTO) throws DuplicateProductCategoryException {
        ProductCategory productCategory = productCategoryDAO.read(productCategoryDTO.getProductCategoryId());
        if (productCategoryDAO.getProductCategoryByName(productCategoryDTO.getName()) != null &&
                !productCategory.getName().equals(productCategoryDTO.getName())){
            DuplicateProductCategoryException ex = new DuplicateProductCategoryException("Category with such name already exists");
            log.error("error", ex.getMessage());
            throw ex;
        }
        entityDTOMapper.mapProductCategoryFromDTO(productCategory, productCategoryDTO);
        productCategoryDAO.update(productCategory);
    }

    @Override
    @Transactional
    public void deleteEntity(ProductCategoryDTO productCategoryDTO) throws DAOException {
        ProductCategory productCategory = (productCategoryDTO.getProductCategoryId() != 0) ?
                productCategoryDAO.read(productCategoryDTO.getProductCategoryId()) : new ProductCategory();
        entityDTOMapper.mapProductCategoryFromDTO(productCategory, productCategoryDTO);
        List<Product> productList = productDAO.getAllProductByCategory(productCategoryDTO.getName());
        if (productList != null && !productList.isEmpty()){
            DAOException ex = new DAOException("This category contain products, delete or change there before");
            log.error("error", ex.getMessage());
            throw ex;
        }
        productCategoryDAO.delete(productCategory);
    }

    @Override
    @Transactional
    public List<ProductCategoryDTO> getAll() throws DAOException {
        List<ProductCategory> productCategoryList = productCategoryDAO.getAll();
        return productCategoryList.stream().map(productCategory -> entityDTOMapper.mapDTOFromProductCategory(productCategory)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductCategoryDTO getProductCategoryByName(String name) {
        return entityDTOMapper.mapDTOFromProductCategory(productCategoryDAO.getProductCategoryByName(name));
    }
}
