package jm.lib.framework.context.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by jiming.liu
 * TODO: use spring delegate proxy. perhaps should not be put here
 */
public class RequestContextFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     * <p/>
     * <p>This method is only called once all threads within the filter's
     * doFilter method have exited or after a timeout period has passed.
     * After the web container calls this method, it will not call the
     * doFilter method again on this instance of the filter.
     * <p/>
     * <p>This method gives the filter an opportunity to clean up any
     * resources that are being held (for example, memory, file handles,
     * threads) and make sure that any persistent state is synchronized
     * with the filter's current state in memory.
     */
    @Override
    public void destroy() {

    }
}
