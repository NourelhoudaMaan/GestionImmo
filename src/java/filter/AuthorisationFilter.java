/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebFilter(filterName="AuthFilter", urlPatterns={"*.xhtml"})
public class AuthorisationFilter implements Filter {
    
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;
    
    public AuthorisationFilter() {
    }    
        
    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        
    }
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)throws IOException, ServletException {
       try{
           HttpServletRequest reqt = (HttpServletRequest) request ;
           HttpServletResponse resp = (HttpServletResponse) response ;
           
           HttpSession ses = reqt.getSession(false) ;
           String reqURI = reqt.getRequestURI() ;
           
           if(reqURI.indexOf("/index.xhtml") >= 0 || (ses != null && ses.getAttribute("grade") != null) || reqURI.indexOf("/public/") >= 0 || reqURI.contains("java.faces.resource")){
               resp.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
               resp.setHeader("Pragma", "no-cache");
               resp.setDateHeader("Expires", 0);
               chain.doFilter(request, response);
           }else{
               resp.sendRedirect(reqt.getContextPath() + "/faces/index.xhtml");
           }
       }catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
    }


    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

}
