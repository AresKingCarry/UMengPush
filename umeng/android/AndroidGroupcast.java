package com.soybean.notify.umeng.android;

import org.json.JSONObject;

import com.soybean.notify.umeng.AndroidNotification;

public class AndroidGroupcast extends AndroidNotification {
    public AndroidGroupcast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "groupcast");
    }

    public void setFilter(JSONObject filter) throws Exception {
        setPredefinedKeyValue("filter", filter);
    }
}
