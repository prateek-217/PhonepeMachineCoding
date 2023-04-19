package org.phonepe.app;


public class App {
    private final String appName;
    private final AppMetaData appMetaData;

    protected App(String appName, AppMetaData appMetaData) {
        this.appName = appName;
        this.appMetaData = appMetaData;
    }

    public String getAppName() {
        return appName;
    }

    public AppMetaData getAppMetaData() {
        return appMetaData;
    }
}
