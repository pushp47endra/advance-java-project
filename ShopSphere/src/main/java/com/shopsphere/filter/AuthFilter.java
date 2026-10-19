package com.shopsphere.filter;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebFilter;import jakarta.servlet.http.*;import java.io.IOException;import com.shopsphere.model.User;
@WebFilter(urlPatterns={"/cart","/checkout","/orders","/wishlist","/review","/admin","/admin/*"})
public class AuthFilter implements Filter {
 public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException {HttpServletRequest r=(HttpServletRequest)request;HttpServletResponse s=(HttpServletResponse)response;HttpSession session=r.getSession(false);User u=session==null?null:(User)session.getAttribute("user");if(u==null){s.sendRedirect(r.getContextPath()+"/login");return;}if(r.getServletPath().startsWith("/admin")&&!"ADMIN".equals(u.getRole())){s.sendError(403,"Admin access required");return;}chain.doFilter(request,response);}
}
