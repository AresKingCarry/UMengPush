package com.soybean.notify.umeng;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import com.google.common.collect.Maps;

/**<br>
 * Description: 友盟签名工具类<br>
 * Company    : 上海黄豆网络科技有限公司 <br>
 * Author     : Iceberg<br>
 * Date       : 2018年12月18日 下午2:01:57<br>
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号<br>
 * v1.0.0      2018年12月18日             Iceberg         新增              1001<br>
 ********************************************************************/
public class SignUtils {

    /**
     * <br>
     * Title      : SignUtils<br>
     * Description: 友盟请求签名<br>
     * Param      : @param msg
     * Param      : @param url
     * Param      : @return
     * Param      : @throws Exception 参数<br>
     * Return     : String 返回类型<br>
     * Throws     : 抛出的异常，有多个用逗号分隔<br>
     */
    public static String sign(UmengNotification msg, String url) throws Exception {
        String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
        msg.setPredefinedKeyValue("timestamp", timestamp);
        String postBody = msg.getPostBody();
        return DigestUtils.md5Hex(("POST" + url + postBody + msg.getAppMasterSecret()).getBytes("utf8"));
    }

    public static String sign(Map<String, Object> params, String url, String secret) throws Exception {
        String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
        params.put("timestamp", timestamp);
        String postBody = getPostBody(params);
        return DigestUtils.md5Hex(("POST" + url + postBody + secret).getBytes("utf8"));
    }

    /**
     * <br>
     * Title      : SignUtils<br>
     * Description: 获取参数body<br>
     * Param      : @param params
     * Param      : @return 参数<br>
     * Return     : String 返回类型<br>
     * Throws     : 抛出的异常，有多个用逗号分隔<br>
     */
    public static String getPostBody(Map<String, Object> params) {
        JSONObject uploadJson = new JSONObject();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            uploadJson.put(entry.getKey(), entry.getValue());
        }
        return uploadJson.toString();
    }

    public static String mergeSignUrl(String originalUrl, String sign) {
        StringBuilder builder = new StringBuilder(originalUrl);
        builder.append("?sign=");
        builder.append(sign);
        return builder.toString();
    }

    public static void main(String[] args) {
        String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
        Map<String, Object> params = Maps.newHashMap();
        params.put("appkey", "123456");
        params.put("timestamp", timestamp);
        params.put("content", "1,2,3,4,5");
        System.out.println(getPostBody(params));
    }
}
