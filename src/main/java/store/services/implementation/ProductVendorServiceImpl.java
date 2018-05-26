package store.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductVendorDAO;
import store.dto.ProductVendorDTO;
import store.entities.ProductVendor;
import store.exceptions.DAOException;
import store.exceptions.DuplicateProductVendorException;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.ProductVendorService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Slf4j
@Service("productVendorService")
public class ProductVendorServiceImpl implements ProductVendorService {

    @Autowired
    private ProductVendorDAO productVendorDAO;

    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @Override
    @Transactional
    public void createEntity(ProductVendorDTO productVendorDTO) throws DAOException {
        if (productVendorDAO.getProductVendorByName(productVendorDTO.getName())!= null){
            DAOException ex = new DAOException("Vendor with such name is already exists");
            log.error("error",ex);
            throw ex;
        }
        ProductVendor productVendor = new ProductVendor();
        entityDTOMapper.mapProductVendorFromDTO(productVendor ,productVendorDTO);
        productVendorDAO.create(productVendor);
        log.info("Creating new Vendor: " + productVendor);
    }

    @Override
    @Transactional
    public ProductVendorDTO getEntityById(Integer id) throws DAOException {
        return entityDTOMapper.mapDTOFromProductVendor(productVendorDAO.read(id));
    }

    @Override
    @Transactional
    public void updateEntity(ProductVendorDTO productVendorDTO) throws DuplicateProductVendorException {
        ProductVendor productVendor = productVendorDAO.read(productVendorDTO.getProductVendorId());
        if (productVendorDAO.getProductVendorByName(productVendorDTO.getName())!= null &&
                !productVendor.getName().equals(productVendorDTO.getName())){
            DuplicateProductVendorException ex = new DuplicateProductVendorException("Vendor with such name is already exists");
            log.error("error", ex.getMessage());
            throw ex;
        }
        entityDTOMapper.mapProductVendorFromDTO(productVendor ,productVendorDTO);
        productVendorDAO.update(productVendor);
    }

    @Override
    @Transactional
    public void deleteEntity(ProductVendorDTO productVendorDTO) throws DAOException {
        ProductVendor productVendor = productVendorDAO.getProductVendorByName(productVendorDTO.getName());
        entityDTOMapper.mapProductVendorFromDTO(productVendor, productVendorDTO);
        productVendorDAO.delete(productVendor);
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
