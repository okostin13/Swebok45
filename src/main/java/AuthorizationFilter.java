import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AuthorizationFilter implements Filter {
    private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";


    public void init(FilterConfig cfg){

    }


    public void destroy(){

    }



    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURL = request.getContextPath() + "/authorization.xhtml";

        boolean loggedIn = (session != null) && (session.getAttribute("user") != null);
        boolean loginRequest = request.getRequestURI().equals(loginURL);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
        boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));

        if (loggedIn || loginRequest || resourceRequest) {
            if (!resourceRequest) { // Prevent browser from caching restricted resources. See also https://stackoverflow.com/q/4194207/157882
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
            }

            chain.doFilter(request, response); // So, just continue request.
        } else if (ajaxRequest) {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // So, return special XML response instructing JSF ajax to send a redirect.
        } else {
            response.sendRedirect(loginURL); // So, just perform standard synchronous redirect.
        }

    /*
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain) throws ServletException, IOException {
        String loginURL = request.getContextPath() + "/authorization.xhtml";

        boolean loggedIn = (session != null) && (session.getAttribute("user") != null);
        boolean loginRequest = request.getRequestURI().equals(loginURL);
        boolean resourceRequest = Servlets.isFacesResourceRequest(request);

        if (loggedIn || loginRequest || resourceRequest) {
            if (!resourceRequest) { // Prevent browser from caching restricted resources. See also https://stackoverflow.com/q/4194207/157882
                Servlets.setNoCacheHeaders(response);
            }

            chain.doFilter(request, response); // So, just continue request.
        }
        else {
            Servlets.facesRedirect(request, response, loginURL);
        }
    }
*/
    }
}