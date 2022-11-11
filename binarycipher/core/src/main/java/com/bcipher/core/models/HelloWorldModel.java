package com.bcipher.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Optional;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

/**
 * Hello word model for reading message.
 */
@Model(adaptables = Resource.class)
public class HelloWorldModel {

    /**
     * Resource type.
     */
    @Inject
    @ValueMapValue(name = PROPERTY_RESOURCE_TYPE,
            injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "No resourceType")
    private String resourceType;
    /**
     * current resource.
     */
    @SlingObject
    private Resource currentResource;
    /**
     * resource resolver.
     */
    @SlingObject
    private ResourceResolver resourceResolver;
    /**
     * Message.
     */
    @Inject
    private String message;

    /**
     * Initialization of servlet.
     */
    @PostConstruct
    protected void init() {
        final PageManager pageManager =
                resourceResolver.adaptTo(PageManager.class);
        final String currentPagePath = Optional.ofNullable(pageManager)
                .map(pm -> pm.getContainingPage(currentResource))
                .map(Page::getPath).orElse("");

        message = "Hello World!\n"
            + "Resource type is: " + resourceType + "\n"
            + "Current page is:  " + currentPagePath + "\n";
    }

    /**
     * Get message.
     * @return {@link String}
     */
    public String getMessage() {
        return message;
    }

}
