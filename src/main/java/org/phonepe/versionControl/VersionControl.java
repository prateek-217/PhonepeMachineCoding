package org.phonepe.versionControl;

import org.phonepe.constants.OsName;
import org.phonepe.app.AndroidApp;
import org.phonepe.app.App;
import org.phonepe.app.IOSApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionControl {

    private final Map<String, Map<OsName, List<App>>> appNameToOsAppsMap = new HashMap<>();

    public Map<String, Map<OsName, List<App>>> getAppNameToOsAppsMap() {
        return appNameToOsAppsMap;
    }

    public void createOrAddApp(String appName,
                               int version,
                               OsName os,
                               String releaseNotes,
                               int supportedOsVersion) {
        appNameToOsAppsMap.putIfAbsent(appName, new HashMap<>());
        appNameToOsAppsMap.get(appName).putIfAbsent(os, new ArrayList<>());
        switch (os) {
            case ANDROID:
                appNameToOsAppsMap.get(appName)
                        .get(os)
                        .add(new AndroidApp(appName, version, releaseNotes, supportedOsVersion));
                break;
            case IOS:
                appNameToOsAppsMap.get(appName)
                        .get(os)
                        .add(new IOSApp(appName, version, releaseNotes, supportedOsVersion));
                break;
            default:
                throw new RuntimeException("No such OS registered");
        }
    }


}
