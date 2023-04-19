package org.phonepe.app;

import org.phonepe.constants.OsName;

public class IOSApp extends App {

    public IOSApp(String appName, int version, String releaseNotes, int supportedOsVersion) {
        super(appName, new AppMetaData(version, releaseNotes, OsName.IOS, supportedOsVersion));
    }
}
