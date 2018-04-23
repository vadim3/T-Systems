package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductVendorDAO;
import store.entities.ProductVendor;
import store.exceptions.DAOException;
import store.services.interfaces.ProductVendorService;

import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Service("productVendorService")
public class ProductVendorServiceImpl implements ProductVendorService {

    @Autowired

    private ProductVendorDAO productVendorDAO;

    @Override
    @Transactional
    public void createEntity(ProductVendor entity) throws DAOException {
        productVendorDAO.create(entity);
    }

    @Override
    @Transactional
    public ProductVendor getEntityById(Integer id) throws DAOException {
        return productVendorDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(ProductVendor entity) throws DAOException {
        productVendorDAO.update(entity);
    }

    @Override
    @Transactional
    public void deleteEntity(ProductVendor entity) throws DAOException {
        productVendorDAO.delete(entity);
    }

    @Override
    @Transactional
    public List<ProductVendor> getAll() throws DAOException {
        return productVendorDAO.getAll();
    }

    @Override
    @Transactional
    public ProductVendor getProductVendorByName(String name) {
        return productVendorDAO.getProductVendorByName(name);
    }
}
