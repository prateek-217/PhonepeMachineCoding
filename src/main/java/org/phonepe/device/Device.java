package org.phonepe.device;

import org.phonepe.app.App;
import org.phonepe.constants.OsName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Device {

    private int deviceId;
    private OsName deviceOS;
    private int osVersion;
    private boolean isBetaUser;

    private Map<String, Integer> appToVersionMap;

    public Device(int deviceId, OsName deviceOS, int osVersion, boolean isBetaUser) {
        this.deviceId = deviceId;
        this.deviceOS = deviceOS;
        this.osVersion = osVersion;
        this.isBetaUser = isBetaUser;
        appToVersionMap = new HashMap<>();
    }

    public int getDeviceId() {
        return deviceId;
    }

    public OsName getDeviceOS() {
        return deviceOS;
    }

    public int getOsVersion() {
        return osVersion;
    }

    public boolean isBetaUser() {
        return isBetaUser;
    }

    public Map<String, Integer> getAppToVersionMap() {
        return appToVersionMap;
    }

}
