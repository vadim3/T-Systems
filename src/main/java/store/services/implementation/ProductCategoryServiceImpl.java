package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductCategoryDAO;
import store.entities.ProductCategory;
import store.exceptions.DAOException;
import store.services.interfaces.ProductCategoryService;

import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;


    @Override
    @Transactional
    public void createEntity(ProductCategory entity) throws DAOException {
        productCategoryDAO.create(entity);
    }

    @Override
    @Transactional
    public ProductCategory getEntityById(Integer id) throws DAOException {
        return productCategoryDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(ProductCategory entity) throws DAOException {
        productCategoryDAO.update(entity);
    }

    @Override
    @Transactional
    public void deleteEntity(ProductCategory entity) throws DAOException {
        productCategoryDAO.delete(entity);
    }

    @Override
    @Transactional
    public List<ProductCategory> getAll() throws DAOException {
        return productCategoryDAO.getAll();
    }
}
