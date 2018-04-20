package store.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class ProductFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String category = request.getParameter("category");
//        try {
//            if (category.isEmpty()){
//                category = "0";
//                request.setAttribute("category", category);
//            }
//        } catch (Exception e){
//            category = "0";
//            request.setAttribute("category", category);
//        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
