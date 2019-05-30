package com.github.neatlife.jframework.fundation.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author suxiaolin
 * @date 2019-04-13 08:53
 */
@Slf4j
public class DingTalkUtil {
    private static String ddUrl = "";

    public static void setDdUrl(String ddUrl) {
        DingTalkUtil.ddUrl = ddUrl;
    }

    public static boolean send(String content, String title, Set<String> receivers) {
        try {
            HttpUtil.ResponseWrap result = HttpUtil.postWrap(ddUrl,
                    "{\n"
                            + "     \"msgtype\": \"text\",\n"
                            + "     \"text\": {\"content\":\"" + title + "\r\n" + content + "\n|"
                            + receivers.stream().map(r -> "@" + r).collect(Collectors.joining(" ")) + "\"},\n"
                            + "    \"at\": {\n"
                            + "        \"atMobiles\": [" + receivers.stream().map(r -> "\"" + r + "\"").collect(Collectors.joining(",")) + "], \n"
                            + "        \"isAtAll\": false\n"
                            + "    }\n"
                            + " }");
            return result.getStatusCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }
}
