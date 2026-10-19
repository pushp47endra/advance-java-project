package com.shopsphere.filter;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebFilter;import jakarta.servlet.http.*;import java.io.IOException;import java.security.SecureRandom;import java.util.Base64;
@WebFilter("/*") public class SecurityFilter implements Filter {
 private static final SecureRandom RNG=new SecureRandom();
 public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException {HttpServletRequest r=(HttpServletRequest)req;HttpServletResponse s=(HttpServletResponse)res;s.setHeader("X-Content-Type-Options","nosniff");s.setHeader("X-Frame-Options","DENY");HttpSession session=r.getSession(true);if(session.getAttribute("csrf")==null){byte[] b=new byte[24];RNG.nextBytes(b);session.setAttribute("csrf",Base64.getUrlEncoder().withoutPadding().encodeToString(b));}if("POST".equalsIgnoreCase(r.getMethod())){String token=r.getParameter("csrf");if(token==null||!token.equals(session.getAttribute("csrf"))){s.sendError(403,"Invalid CSRF token");return;}}chain.doFilter(req,res);}
}
