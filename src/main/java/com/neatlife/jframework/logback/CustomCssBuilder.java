package com.neatlife.jframework.logback;

import ch.qos.logback.classic.html.DefaultCssBuilder;

import static ch.qos.logback.core.CoreConstants.LINE_SEPARATOR;

public class CustomCssBuilder extends DefaultCssBuilder {
    @Override
    public void addCss(StringBuilder sbuf) {
        super.addCss(sbuf);
        sbuf.append("<style  type=\"text/css\">");

        sbuf.append(LINE_SEPARATOR);
        sbuf.append("TR.bg-pink { background: pink; color: #000 }");
        sbuf.append(LINE_SEPARATOR);

        sbuf.append("</style>");
        sbuf.append(LINE_SEPARATOR);
    }
}
