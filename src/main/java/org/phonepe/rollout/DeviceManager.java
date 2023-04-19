package org.phonepe.rollout;

import org.phonepe.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {
    private static final List<Device> deviceList = new ArrayList<>();

    public static void addDevice(Device device) {
        deviceList.add(device);
    }

    public static List<Device> getDeviceList() {
        return deviceList;
    }


}
