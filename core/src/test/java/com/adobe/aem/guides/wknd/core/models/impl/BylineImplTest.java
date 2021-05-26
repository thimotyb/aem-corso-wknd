package com.adobe.aem.guides.wknd.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.Byline;
import com.adobe.cq.wcm.core.components.models.Image;
import com.google.common.collect.ImmutableList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class BylineImplTest {

    private final AemContext ctx = new AemContext();

    @Mock
    private Image image;

    @BeforeEach
    void setUp() throws Exception {
        ctx.addModelsForClasses(BylineImpl.class);
        ctx.load().json("/com/adobe/aem/guides/wknd/core/models/impl/BylineImplTest.json", "/content");
    }

    @Test 
    void testGetName() { 
        
        final String expected = "Stacey Roswells";

        ctx.currentResource("/content/byline");

        Byline byline = ctx.request().adaptTo(Byline.class);
        String actual = byline.getName();

        assertEquals(expected, actual);

    }
    
    @Test 
    void testGetOccupations() { 
        List<String> expected = new ImmutableList.Builder<String>()
                            .add("Blogger")
                            .add("Photographer")
                            .add("YouTuber")
                            .build();

        ctx.currentResource("/content/byline");
        Byline byline = ctx.request().adaptTo(Byline.class);

        List<String> actual = byline.getOccupations();

        assertEquals(expected, actual);
    }

    @Test 
    void testIsEmpty() { 
        
        ctx.currentResource("/content/empty");
        Byline byline = ctx.request().adaptTo(Byline.class);

        assertTrue(byline.isEmpty());

    }

    @Test
    public void testIsEmpty_WithoutName() {
        ctx.currentResource("/content/without-name");

        Byline byline = ctx.request().adaptTo(Byline.class);

        assertTrue(byline.isEmpty());
    }

    @Test
    public void testIsEmpty_WithoutOccupations() {
        ctx.currentResource("/content/without-occupations");

        Byline byline = ctx.request().adaptTo(Byline.class);

        assertTrue(byline.isEmpty());
    }
}
