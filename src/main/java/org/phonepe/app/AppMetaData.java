package org.phonepe.app;

import org.phonepe.constants.OsName;

public class AppMetaData {
    private int version;
    private String releaseNotes;

    private OsName supportedOS;

    private int supportedOsVersion;
    public AppMetaData(int version, String releaseNotes, OsName SupportedOS, int supportedOsVersion) {
        this.version = version;
        this.releaseNotes = releaseNotes;
        this.supportedOS = SupportedOS;
        this.supportedOsVersion = supportedOsVersion;
    }

    public int getVersion() {
        return version;
    }

    public String getReleaseNotes() {
        return releaseNotes;
    }

    public OsName getSupportedOS() {
        return supportedOS;
    }

    public int getSupportedOsVersion() {
        return supportedOsVersion;
    }
}
