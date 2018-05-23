package store.objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import store.dto.ProductDTO;
import store.services.interfaces.ProductService;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Component
@Scope("singleton")
public class TopProductsSet {

    @Autowired
    private ProductService productService;

    private Set<ProductDTO> topList;

    public Set<ProductDTO> getTopSet() {
        return topList;
    }

    public void setTopSet(Set<ProductDTO> topList) {
        this.topList = topList;
    }

    @PostConstruct
    public void postConstruct() {
        setTopSet(productService.getTenBestSellersProduct().keySet());
    }
}
