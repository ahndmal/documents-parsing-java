package com.anma.tika.translate;

public class TikaTranslator {

    public String microsoftTranslateToFrench(String text) {
        MicrosoftTranslator translator = new MicrosoftTranslator();
        // Change the id and secret! See http://msdn.microsoft.com/en-us/library/hh454950.aspx.
        translator.setId("dummy-id");
        translator.setSecret("dummy-secret");
        try {
            return translator.translate(text, "fr");
        } catch (Exception e) {
            return "Error while translating.";
        }
    }
}
class MicrosoftTranslator {
    String translate(String text, String lang) {
        return "";
    }
    private String id;
    private String secret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
