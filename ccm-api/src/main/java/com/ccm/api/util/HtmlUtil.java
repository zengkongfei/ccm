package com.ccm.api.util;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public abstract class HtmlUtil {
    
    public static String sanitizeXss(String content) {
        if (content == null) return content;
        String safe = Jsoup.clean(content, "http://www.ccm/", Whitelist.relaxed().addAttributes(":all","style").addAttributes(":all","src").addTags("span").preserveRelativeLinks(true));
        return safe;
    }
    
    public static boolean isBlankContent(String content) {
        if (content == null) return true;
        content = Jsoup.parse(content).text();
        return StringUtils.isBlank(content);
    }

}
