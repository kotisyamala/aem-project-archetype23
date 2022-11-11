package com.bcipher.core.filters;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.engine.EngineConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.component.propertytypes.ServiceVendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Simple servlet filter component that logs incoming requests.
 */
@Component(service = Filter.class,
           property = {
                   EngineConstants.SLING_FILTER_SCOPE
                    + "=" + EngineConstants.FILTER_SCOPE_REQUEST,
           })
@ServiceDescription("Demo to filter incoming requests")
@ServiceVendor("Adobe")
public class LoggingFilter implements Filter {

    /**
     * Logger constant.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Do filter on the filter call.
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final SlingHttpServletRequest slingRequest =
                (SlingHttpServletRequest) request;
        if (logger.isDebugEnabled()) {
            logger.debug("request for {}, with selector {}", slingRequest
                    .getRequestPathInfo().getResourcePath(), slingRequest
                    .getRequestPathInfo().getSelectorString());
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Initialization of logger.
     * @param filterConfig filter configuration.
     */
    @Override
    public void init(final FilterConfig filterConfig) {
        if (logger.isDebugEnabled()) {
            logger.debug("filterConfig for {}", filterConfig);
        }
    }

    /**
     * destory filter.
     */
    @Override
    public void destroy() {
        if (logger.isDebugEnabled()) {
            logger.debug("destroy method invoked");
        }
    }

}
