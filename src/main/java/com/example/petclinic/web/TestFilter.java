package com.example.petclinic.web;

import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(initParams = {@WebInitParam(name="testFilter",value = "/test")})
@Order(1)
public class TestFilter implements Filter {
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
       Filter.super.init(filterConfig);
       System.out.println("-----Filter Initialization complete -----");
  }

@Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                        FilterChain filterChain) throws IOException, ServletException {
                           //  servletResponse.getWriter().write("before ...");
       filterChain.doFilter(servletRequest,servletResponse);
      //  servletResponse.getWriter().write("after ...");

    }

 @Override
   public void destroy() {
       Filter.super.destroy();
       System.out.println("Filter destroyed ");
  }


}
