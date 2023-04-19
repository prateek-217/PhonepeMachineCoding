package org.phonepe;

import org.phonepe.device.Device;
import org.phonepe.app.App;
import org.phonepe.constants.OsName;
import org.phonepe.constants.RolloutStrategy;
import org.phonepe.device.DeviceManager;
import org.phonepe.versionControl.VersionControl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin {

    private VersionControl versionControl = new VersionControl();

    private Map<String, Integer> appRollOutSizeMap = new HashMap<>();
    private int maxVersionDiff = 5;
    private int rolloutPercentage = 10;


    private void rolloutUpdate(String appName, Device device) {
        List<App> appList = versionControl.getAppNameToOsAppsMap().get(appName).get(device.getDeviceOS());
        App app = appList.get(appList.size() - 1);
        if (isAppVersionSupported(app, device)) {
            if (checkForInstall(app, device)) {
                install(app, device, app.toString().getBytes(StandardCharsets.UTF_8));
                device.getAppToVersionMap().put(app.getAppName(), app.getAppMetaData().getVersion());
                System.out.println("Application " + appName + " installed for device " + device.getDeviceId());
            } else if (checkForUpdate(app, device)) {
                update(app, device, createUpdatePatch(appList));
                device.getAppToVersionMap().put(app.getAppName(), app.getAppMetaData().getVersion());
                System.out.println("Application " + appName + " updated for device " + device.getDeviceId());
            }
        }
    }


    private byte[] createUpdatePatch(List<App> appList) {
        if (appList.size() > 1) {
            App newApp = appList.get(appList.size() - 1);
            App oldApp = appList.get(appList.size() - 2);
            System.out.println("Update patch generated for " + newApp.getAppName());
            return generateDiffPatch(newApp, oldApp);
        }
        return appList.get(0).toString().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Some dummy logic
     */
    private byte[] generateDiffPatch(App newApp, App oldApp) {
        return newApp.toString().getBytes(StandardCharsets.UTF_8);
    }

    private boolean checkForUpdate(App app, Device device) {
        return device.getAppToVersionMap().getOrDefault(app.getAppName(), 0) < app.getAppMetaData().getVersion()
                && device.getAppToVersionMap().getOrDefault(app.getAppName(), 0) - app.getAppMetaData().getVersion() < maxVersionDiff;
    }

    private boolean checkForInstall(App app, Device device) {
        return device.getAppToVersionMap().getOrDefault(app.getAppName(), 0) < app.getAppMetaData().getVersion()
                && device.getAppToVersionMap().getOrDefault(app.getAppName(), 0) - app.getAppMetaData().getVersion() > maxVersionDiff;
    }

    private boolean isAppVersionSupported(App app, Device device) {
        return app.getAppMetaData().getSupportedOS() == device.getDeviceOS()
                && app.getAppMetaData().getSupportedOsVersion() <= device.getOsVersion();
    }

    /**
     * Dummy method
     */
    private void install(App app, Device device, byte[] stream) {

    }

    /**
     * Dummy method
     */
    private void update(App app, Device device, byte[] stream) {

    }
    private void rollOut(String appName, RolloutStrategy rolloutStrategy) {
        switch (rolloutStrategy) {
            case BETA:
                DeviceManager.getDeviceList()
                        .stream()
                        .filter(Device::isBetaUser)
                        .forEach(device -> {
                            rolloutUpdate(appName, device);
                        });
                break;
            case PERCENTAGE:
                    int rolloutSize = DeviceManager.getDeviceList().size()*rolloutPercentage/100;
                    if(rolloutSize == 0) {
                        rolloutSize = DeviceManager.getDeviceList().size();
                    }
                    for (int i = appRollOutSizeMap.getOrDefault(appName, 0); i < rolloutSize; i++) {
                        rolloutUpdate(appName, DeviceManager.getDeviceList().get(i));
                    }
                appRollOutSizeMap.put(appName, rolloutSize);
                break;
            default:
                throw new RuntimeException("No such rollout strategy");
        }
    }

    public void releaseNewVersion(String appName, RolloutStrategy rolloutStrategy) {
        rollOut(appName, rolloutStrategy);
    }

    public void uploadNewVersion(String appName,
                               int version,
                               OsName os,
                               String releaseNotes,
                               int supportedOsVersion) {
        versionControl.createOrAddApp(appName, version, os, releaseNotes, supportedOsVersion);
        System.out.println("New Version released");
        System.out.println("OS: " + os + " Version: " + version + " App: " + appName);
    }

    public void updateRollOutPercentage(int rolloutPercentage) {
        this.rolloutPercentage = rolloutPercentage;
    }
}
