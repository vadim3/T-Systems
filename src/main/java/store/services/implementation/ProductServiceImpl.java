package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductDAO;
import store.entities.Product;
import store.exceptions.DAOException;
import store.services.interfaces.ProductService;

import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public Product getProductByName(String name) {
        return productDAO.getProductByName(name);
    }

    @Override
    public List<Product> getProductByCategory(String name) {
        return productDAO.getAllProductByCategory(name);
    }

    @Override
    public List<Product> getProductByVendor(String name) {
        return productDAO.getAllProductByVendor(name);
    }

    @Override
    @Transactional
    public void createEntity(Product product) throws DAOException {
        productDAO.create(product);
    }

    @Override
    @Transactional
    public Product getEntityById(Integer id) throws DAOException {
        return productDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(Product product) throws DAOException {
        productDAO.update(product);
    }

    @Override
    @Transactional
    public void deleteEntity(Product product) throws DAOException {
        productDAO.delete(product);
    }

    @Override
    @Transactional
    public List<Product> getAll() throws DAOException {
        return productDAO.getAll();
    }
}
