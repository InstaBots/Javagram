package net.steppschuh.instabots.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PostTest {

    @Test
    public void getPostIdFromUrl_validUrl_correctId() throws Exception {
        String url = "https://www.instagram.com/p/BekncLgn3AQ/?tagged=natgeo";
        String expectedId = "BekncLgn3AQ";
        String actualId = Post.getIdFromUrl(url);
        assertEquals(expectedId, actualId);
    }

    @Test
    public void getPostIdFromUrl_invalidUrl_throwsException() throws Exception {
        String url = "https://www.instagram.com/explore/tags/natgeo/";
        try {
            Post.getIdFromUrl(url);
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

}