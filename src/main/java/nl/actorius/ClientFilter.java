package nl.actorius;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Peter van Vliet
 */
public class ClientFilter implements Filter
{
    private static final String[] allowedExtensions =
    {
        // Basic files
        "html", "css", "js", "map", "json",
        
        // Images
        "png", "jpg", "gif", "svg",
        
        // Fonts
        "eot", "ttf", "woff", "woff2",
        
        // Downloads
        "pdf", "zip", "doc", "docx", "xls", "xlsx", "ppt", "pptx"
    };
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (shouldRedirect(request.getRequestURI()))
        {
            response.sendRedirect("/");
        }
        else
        {
            chain.doFilter(req, res);
        }
    }

    private boolean shouldRedirect(String uri)
    {
        return !uri.equals("")
            && !uri.equals("/")
            && !uri.startsWith("/api")
            && !isAllowedExtension(uri);
    }
    
    private boolean isAllowedExtension(String uri)
    {
        for(String extension : allowedExtensions)
        {
            if(uri.endsWith(extension))
            {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public void destroy()
    {
        
    }
}
