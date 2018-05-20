package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductCategoryDAO;
import store.dto.ProductCategoryDTO;
import store.entities.ProductCategory;
import store.exceptions.DAOException;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.ProductCategoryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    EntityDTOMapper entityDTOMapper;


    @Override
    @Transactional
    public void createEntity(ProductCategoryDTO productCategoryDTO) throws DAOException {
        ProductCategory productCategory = (productCategoryDTO.getProductCategoryId() != 0) ?
                productCategoryDAO.read(productCategoryDTO.getProductCategoryId()) : new ProductCategory();
        entityDTOMapper.mapProductCategoryFromDTO(productCategory, productCategoryDTO);
        productCategoryDAO.create(productCategory);
    }

    @Override
    @Transactional
    public ProductCategoryDTO getEntityById(Integer id) throws DAOException {
        return entityDTOMapper.mapDTOFromProductCategory(productCategoryDAO.read(id));
    }

    @Override
    @Transactional
    public void updateEntity(ProductCategoryDTO productCategoryDTO) throws DAOException {
        ProductCategory productCategory = (productCategoryDTO.getProductCategoryId() != 0) ?
                productCategoryDAO.read(productCategoryDTO.getProductCategoryId()) : new ProductCategory();
        entityDTOMapper.mapProductCategoryFromDTO(productCategory, productCategoryDTO);
        productCategoryDAO.update(productCategory);
    }

    @Override
    @Transactional
    public void deleteEntity(ProductCategoryDTO productCategoryDTO) throws DAOException {
        ProductCategory productCategory = (productCategoryDTO.getProductCategoryId() != 0) ?
                productCategoryDAO.read(productCategoryDTO.getProductCategoryId()) : new ProductCategory();
        entityDTOMapper.mapProductCategoryFromDTO(productCategory, productCategoryDTO);
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
