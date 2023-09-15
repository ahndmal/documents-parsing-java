package com.anma.tika.meta;

import org.apache.tika.metadata.Metadata;

public class MetadataMisc {
    private void addMetadata(Metadata metadata, String filePerms,
                             String numHardLinks, String fileOwner, String fileOwnerGroup,
                             String fileSize, String lastModDate, String fileName) {
        metadata.add("FilePermissions", filePerms);
        metadata.add("NumHardLinks", numHardLinks);
        metadata.add("FileOwner", fileOwner);
        metadata.add("FileOwnerGroup", fileOwnerGroup);
        metadata.add("FileSize", fileSize);
        metadata.add("LastModifiedDate", lastModDate);
        metadata.add("Filename", fileName);
        if (filePerms.indexOf("x") != -1 && filePerms.indexOf("d") == -1) {
            if (metadata.get("NumExecutables") != null) {
                int numExecs = Integer.valueOf(
                        metadata.get("NumExecutables"));
                numExecs++;
                metadata.set("NumExecutables", String.valueOf(numExecs));
            } else {
                metadata.set("NumExecutables", "1");
            }
        }
    }
}
