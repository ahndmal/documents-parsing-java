package com.anma.tika.bk;

public class MyTIkaLinkExtractor {
    /*public void startElement(String uri, String local, String name, Attributes attributes) {
        if (XHTML.equals(uri)) {
            if ("a".equals(local)) {
                LinkBuilder builder = new LinkBuilder("a");
                builder.setURI(attributes.getValue("", "href"));
                builder.setTitle(attributes.getValue("", "title"));
                builderStack.addFirst(builder);
            } else if ("img".equals(local)) {
                LinkBuilder builder = new LinkBuilder("img");
                builder.setURI(attributes.getValue("", "src"));
                builder.setTitle(attributes.getValue("", "title"));
                builderStack.addFirst(builder);
                String alt = attributes.getValue("", "alt");
                if (alt != null) {
                    char[] ch = alt.toCharArray();
                    characters(ch, 0, ch.length);
                }
            }
        }
    }

    public void endElement(String uri, String local, String name) {
        if (XHTML.equals(uri)) {
            if ("a".equals(local) || "img".equals(local)) {
                links.add(builderStack.removeFirst().getLink());
            }
        }
    }*/
}
