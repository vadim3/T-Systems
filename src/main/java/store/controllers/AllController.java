package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.dto.ProductDTO;
import store.dto.UserDTO;
import store.services.interfaces.ProductCategoryService;
import store.services.interfaces.ProductService;
import store.services.interfaces.ProductVendorService;
import store.tools.SimpleMessageProducer;


import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Controller("AllController")
public class AllController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductVendorService productVendorService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * Method for all items
     *
     * @param req   request from page
     * @param model model for page view
     * @return page for catalog of items
     */
    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalog(HttpServletRequest req, Model model,
                          @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "vendor", required = false) String vendor,
                          @RequestParam(value = "minprice", required = false) String minprice,
                          @RequestParam(value = "maxprice", required = false) String maxprice,
                          @RequestParam(value = "page", required = false) String page
    ) {
        int items = 0;
        for (Object i : ((HashMap) req.getSession().getAttribute("cartProducts")).values()) {
            items += (Integer) i;
        }
        model.addAttribute("items", items);
        model.addAttribute("productList", productService.getProductByComplex(category, vendor, minprice, maxprice, page));
        model.addAttribute("allCategories", productCategoryService.getAll());
        model.addAttribute("allVendors", productVendorService.getAll());
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("searchCategory", category);
        model.addAttribute("searchVendor", vendor);
        model.addAttribute("minprice", minprice);
        model.addAttribute("maxprice", maxprice);
        model.addAttribute("page", (page == null) ? 1 : Integer.parseInt(page));
        model.addAttribute("pageQuantity", productService.paginationPages(category, vendor, minprice, maxprice, page));
        model.addAttribute("itemsQuantity", productService.itemsQuintity(category, vendor, minprice, maxprice, page));
        return "all/allproducts";
    }

    /**
     * Method for all items
     *
     * @param req   request from page
     * @param model model for page view
     * @return home page of the application
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(HttpServletRequest req, Model model) throws JMSException {

        if (req.getSession().getAttribute("cartProducts") == null) {
            req.getSession().setAttribute("cartProducts", new HashMap<ProductDTO, Integer>());
        }
        if (req.getSession().getAttribute("currentUser") == null) {
            req.getSession().setAttribute("currentUser", new UserDTO());
        }

        int items = 0;
        for (Object i : ((HashMap) req.getSession().getAttribute("cartProducts")).values()) {
            items += (Integer) i;
        }

        model.addAttribute("popularProducts", productService.getTenBestSellersProduct().keySet());
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("items", items);
        return "all/index";
    }


    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contact(HttpServletRequest req, Model model) throws Exception {
        String sendType = "testTopic";
        ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/producer-jms-context.xml", AllController.class);
        SimpleMessageProducer producer = (SimpleMessageProducer) context.getBean("messageProducer");
        producer.sendMessages(sendType,"text");

//        BrokerService broker = BrokerFactory.createBroker(new URI( "broker:(tcp://localhost:61616)"));
//        broker.start();
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring-message-context.xml");
//        try {
//            SpringJmsProducer springJmsProducer = (SpringJmsProducer) context.getBean("springJmsProducer");
//            springJmsProducer.sendMessage("Hi");
//            SpringJmsConsumer springJmsConsumer = (SpringJmsConsumer) context.getBean("springJmsConsumer");
//            System.out.println("Consumer receives " + springJmsConsumer.receiveMessage());
//        } finally {
//            broker.stop();
//            context.close();
//        }
        return "all/contacts";
    }


    @RequestMapping(value = "/catalog", method = RequestMethod.POST)
    @Scope("session")
    public void addtoCart(HttpServletRequest req, Model model,
                            @RequestParam(value = "item", required = false) String item)
    {
        int productId = Integer.parseInt(item);
        HashMap<ProductDTO, Integer> cartProducts = (HashMap<ProductDTO, Integer>) req.getSession().getAttribute("cartProducts");
        ProductDTO product = productService.getEntityById(productId);
        System.out.println(productId);

        if (cartProducts.containsKey(product)) {
            cartProducts.put(product, cartProducts.get(product) + 1);
        } else {
            cartProducts.put(product, 1);
        }
        int items = 0;
        for (Object i : ((HashMap) req.getSession().getAttribute("cartProducts")).values()) {
            items += (Integer) i;
        }

        model.addAttribute("items", items);
        model.addAttribute("allCategories", productCategoryService.getAll());
        model.addAttribute("allVendors", productVendorService.getAll());
        model.addAttribute("productList", productService.getAll());
        model.addAttribute("imgprefix", "/img/products/");
//        return "redirect:catalog";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(@RequestParam("id") int id, HttpServletRequest req, Model model) {
        model.addAttribute("product", productService.getEntityById(id));
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        return "all/singleproduct";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @Scope("session")
    public String addProductToCart(HttpServletRequest req, Model model) {
        int productId = Integer.parseInt(req.getParameter("product"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        HashMap<ProductDTO, Integer> cartProducts = (HashMap<ProductDTO, Integer>) req.getSession().getAttribute("cartProducts");
        ProductDTO product = productService.getEntityById(productId);
        System.out.println(productId);

        if (cartProducts.containsKey(product)) {
            cartProducts.put(product, cartProducts.get(product) + quantity);
        } else {
            cartProducts.put(product, quantity);
        }
        int items = 0;
        for (Object i : ((HashMap) req.getSession().getAttribute("cartProducts")).values()) {
            items += (Integer) i;
        }
        model.addAttribute("items", items);


        model.addAttribute("product", productService.getEntityById(productId));
        model.addAttribute("imgprefix", "/img/products/");
        return "all/singleproduct";
    }
}
