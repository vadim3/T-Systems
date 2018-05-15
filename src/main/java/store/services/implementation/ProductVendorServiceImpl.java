package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductVendorDAO;
import store.dto.ProductVendorDTO;
import store.entities.ProductVendor;
import store.exceptions.DAOException;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.ProductVendorService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Service("productVendorService")
public class ProductVendorServiceImpl implements ProductVendorService {

    @Autowired
    private ProductVendorDAO productVendorDAO;

    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @Override
    @Transactional
    public void createEntity(ProductVendorDTO entity) throws DAOException {
        productVendorDAO.create(entityDTOMapper.mapProductVendorFromDTO(entity));
    }

    @Override
    @Transactional
    public ProductVendorDTO getEntityById(Integer id) throws DAOException {
        return entityDTOMapper.mapDTOFromProductVendor(productVendorDAO.read(id));
    }

    @Override
    @Transactional
    public void updateEntity(ProductVendorDTO entity) throws DAOException {
        productVendorDAO.update(entityDTOMapper.mapProductVendorFromDTO(entity));
    }

    @Override
    @Transactional
    public void deleteEntity(ProductVendorDTO entity) throws DAOException {
        productVendorDAO.delete(entityDTOMapper.mapProductVendorFromDTO(entity));
    }

    @Override
    @Transactional
    public List<ProductVendorDTO> getAll() throws DAOException {
        List<ProductVendor> productVendorList = productVendorDAO.getAll();
        return productVendorList.stream().map(productVendor -> entityDTOMapper.mapDTOFromProductVendor(productVendor)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductVendorDTO getProductVendorByName(String name) {
        return entityDTOMapper.mapDTOFromProductVendor(productVendorDAO.getProductVendorByName(name));
    }
}
