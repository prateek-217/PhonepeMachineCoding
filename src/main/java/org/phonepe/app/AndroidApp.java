package org.phonepe.app;

import org.phonepe.constants.OsName;

public class AndroidApp extends App {

    public AndroidApp(String appName, int appVersion, String releaseNotes, int supportedOsVersion) {
        super(appName, new AppMetaData(appVersion, releaseNotes, OsName.ANDROID, supportedOsVersion));
    }
}
