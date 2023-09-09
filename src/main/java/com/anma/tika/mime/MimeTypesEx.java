package com.anma.tika.mime;

import org.apache.tika.mime.MediaType;

import java.util.Map;

public class MimeTypesEx {
    public static void mimes() {
        MediaType type = MediaType.parse("text/plain; charset=UTF-8");

        System.out.println("type: " + type.getType());
        System.out.println("subtype: " + type.getSubtype());

        Map<String, String> parameters = type.getParameters();
        System.out.println("parameters:");

        for (String name : parameters.keySet()) {
            System.out.println(" " + name + "=" + parameters.get(name));
        }
    }
}
