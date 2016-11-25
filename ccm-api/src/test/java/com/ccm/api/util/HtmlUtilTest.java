package com.ccm.api.util;

import static org.junit.Assert.assertEquals;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.Assert;
import org.junit.Test;

public class HtmlUtilTest {
    
    @Test
    public void cleanHtmlTest() {
        String expect = "酒店点评";
        String result = Jsoup
                .parse("<p><strong style=\"margin: 0px; padding: 0px; border: 0px; outline: 0px; font-size: 14.44444465637207px; vertical-align: baseline; color: rgb(85, 85, 85); font-family: Arial, Helvetica, sans-serif; line-height: 25px;\">酒店点评</strong></p>")
                .text();
        Assert.assertEquals(expect, result);
    }

    @Test
    public void sanitizeXss() {
        {
            String unsafe = "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
            String safe = Jsoup.clean(unsafe, Whitelist.basic());
            // now: <p><a href="http://example.com/" rel="nofollow">Link</a></p>
            String expected = "<p><a href=\"http://example.com/\" rel=\"nofollow\">Link</a></p>";
            assertEquals(expected, safe);
        }
        {
            String safeText = "<p><strong style=\"margin: 0px; padding: 0px; border: 0px; outline: 0px; font-size: 14.44444465637207px; vertical-align: baseline; color: rgb(85, 85, 85); font-family: Arial, Helvetica, sans-serif; line-height: 25px;\">酒店点评</strong></p>";
            String safe = Jsoup.clean(safeText, Whitelist.relaxed().addAttributes(":all","style").preserveRelativeLinks(true));
            assertEquals(safeText, safe);
        }
        {
            String safeText = "<p><img src=\"/resources/roomPic/4fd08040e4b0cc89a5330a9f/webscale_540x360.JPG\" style=\"margin:5px;\" /></p>";
            String safe = Jsoup.clean(safeText, "http://www.ccm.com/", Whitelist.relaxed().addAttributes(":all","style").addAttributes(":all","src").preserveRelativeLinks(true));
            assertEquals(safeText, safe);
        }
    }
    
    @Test
    public void sanitizeXssTest() {
        {
            String unsafe = "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
            String safe = HtmlUtil.sanitizeXss(unsafe);
            // now: <p><a href="http://example.com/" rel="nofollow">Link</a></p>
            String expected = "<p><a href=\"http://example.com/\">Link</a></p>";
            assertEquals(expected, safe);
        }
        {
            String safeText = "<p><strong style=\"margin: 0px; padding: 0px; border: 0px; outline: 0px; font-size: 14.44444465637207px; vertical-align: baseline; color: rgb(85, 85, 85); font-family: Arial, Helvetica, sans-serif; line-height: 25px;\">酒店点评</strong></p>";
            String safe = HtmlUtil.sanitizeXss(safeText);
            assertEquals(safeText, safe);
        }
        {
            String safeText = "<p><img src=\"/resources/roomPic/4fd08040e4b0cc89a5330a9f/webscale_540x360.JPG\" style=\"margin:5px;\" /></p>";
            String safe = HtmlUtil.sanitizeXss(safeText);
            assertEquals(safeText, safe);
        } 
        {
            //Assert.assertEquals("<div><span style=\"color:#FF0000;\">afdsafdsaf</span></div>", HtmlUtil.sanitizeXss("<div><span style=\"color:#FF0000;\">afdsafdsaf</span></div>"));
        }
    }
    
    @Test
    public void isBlankContentTest() {
        Assert.assertTrue(HtmlUtil.isBlankContent("<p><strong style=\"margin: 0px; padding: 0px; border: 0px; outline: 0px; font-size: 14.44444465637207px; vertical-align: baseline; color: rgb(85, 85, 85); font-family: Arial, Helvetica, sans-serif; line-height: 25px;\"> </strong></p>"));
    }

}
