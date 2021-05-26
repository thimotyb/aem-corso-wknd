package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.adobe.aem.guides.wknd.core.models.Byline;
import com.adobe.cq.wcm.core.components.models.Image;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Byline.class},
        resourceType = {BylineImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BylineImpl implements Byline {

    protected static final String RESOURCE_TYPE = "wknd/components/content/byline";

    @ValueMapValue
    private String name;

    @ValueMapValue
    private List<String> occupations;

    @Self
    private Image image;

    private Image getImage() {
        //Image image = null; // COme lo piglio?
        return image;
    }

    @Override
    public String getName() {        
        return name;
    }

    @Override
    public List<String> getOccupations() {
        if (occupations != null) {
            Collections.sort(occupations);
            return new ArrayList<String>(occupations);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean isEmpty() {

        final Image componentImage = getImage();

        if (StringUtils.isBlank(name)) {
            // Name is missing, but required
            return true;
        } else if (occupations == null || occupations.isEmpty()) {
            // At least one occupation is required
            return true;
        } else if (componentImage == null || StringUtils.isBlank(componentImage.getSrc())) {
            // A valid image is required
            return true;
        } else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }
    
}
