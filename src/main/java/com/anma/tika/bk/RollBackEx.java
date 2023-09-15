package com.anma.tika.bk;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RollBackEx {
    public void rollback(File deployArea) throws IOException, SAXException, TikaException {
        LinkContentHandler handler = new LinkContentHandler();
        Metadata meta = new Metadata();
        DeploymentAreaParser parser = new DeploymentAreaParser();
//        parser.parse(IOUtils.toInputStream(deployArea.getAbsolutePath()), handler, meta);
        List<Link> links = handler.getLinks();
        if (links.size() < 2)
            throw new IOException("Must have installed at least 2 versions!");
        Collections.sort(links, new Comparator<Link>() {
            public int compare(Link o1, Link o2) {
                return o1.getText().compareTo(o2.getText());
            }
        });
//        this.updateVersion(links.get(links.size() - 2).getText());
    }
}
