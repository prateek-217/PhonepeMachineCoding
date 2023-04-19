package org.phonepe;

import org.phonepe.constants.OsName;
import org.phonepe.constants.RolloutStrategy;
import org.phonepe.device.Device;
import org.phonepe.device.DeviceManager;

public class AppVersionManagementApplication {
    public static void main(String[] args) {
        Admin admin = new Admin();
        DeviceManager.addDevice(new Device(1, OsName.ANDROID, 2, false));
        admin.uploadNewVersion("Phonepe", 1, OsName.ANDROID, "dummy Release Notes", 1);
        admin.releaseNewVersion("Phonepe", RolloutStrategy.BETA);
        admin.releaseNewVersion("Phonepe", RolloutStrategy.PERCENTAGE);
    }
}