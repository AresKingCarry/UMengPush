package com.soybean.notify.umeng;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.soybean.common.utils.basic.DateUtil;

/**<br>
 * Description: 推送定时任务计数器<br>
 * Company    : 上海黄豆网络科技有限公司 <br>
 * Author     : Iceberg<br>
 * Date       : 2018年12月24日 上午9:11:25<br>
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号<br>
 * v1.0.0      2018年12月24日             Iceberg         新增              1001<br>
 ********************************************************************/
public class TimeCalculatorUtils {

    private static byte[] broadCastlock = new byte[0];
    public static final String BROADCAST_CACHE_FIELD = "broadcast";
    public static final int BROADCAST_MAX_COUNT = 8;

    private static byte[] groupCastlock = new byte[0];
    public static final String GROUPCAST_CACHE_FIELD = "groupcast";
    public static final int GROUPCAST_MAX_COUNT = 3;

    private static byte[] fileCastlock = new byte[0];
    public static final String FILECAST_CACHE_FIELD = "filecast";
    public static final int FILECAST_MAX_COUNT = 200;

    private static byte[] customizedCastlock = new byte[0];
    public static final String CUSTOMIZEDCAST_CACHE_FIELD = "customizedcast";
    public static final int CUSTOMIZEDCAST_MAX_COUNT = 200;

    public static final Cache<String, Integer> broadCastCount = CacheBuilder.newBuilder().maximumSize(1).build();

    public static final Cache<String, Integer> groupCastCount = CacheBuilder.newBuilder().maximumSize(1).build();

    public static final Cache<String, Integer> fileCastCount = CacheBuilder.newBuilder().maximumSize(1).build();

    public static final Cache<String, Integer> customizedCastCount = CacheBuilder.newBuilder().maximumSize(1).build();

    public static boolean addBroadCastCount() {
        synchronized (broadCastlock) {
            Integer broadCount = broadCastCount.getIfPresent(BROADCAST_CACHE_FIELD);
            if (broadCount != null && broadCount.intValue() >= BROADCAST_MAX_COUNT) {
                    return false;
            } else {
                if (broadCount == null) {
                    broadCount = 0;
                }
                broadCastCount.put(BROADCAST_CACHE_FIELD, (broadCount.intValue() + 1));
                return true;
            }
        }
    }

    public static boolean addGroupCastCount() {
        synchronized (groupCastlock) {
            Integer broadCount = groupCastCount.getIfPresent(GROUPCAST_CACHE_FIELD);
            if (broadCount != null && broadCount.intValue() >= GROUPCAST_MAX_COUNT) {
                return false;
            } else {
                if (broadCount == null) {
                    broadCount = 0;
                }
                groupCastCount.put(GROUPCAST_CACHE_FIELD, (broadCount.intValue() + 1));
                return true;
            }
        }
    }

    public static boolean addFileCastCount() {
        synchronized (fileCastlock) {
            Integer broadCount = fileCastCount.getIfPresent(FILECAST_CACHE_FIELD);
            if (broadCount != null && broadCount.intValue() >= FILECAST_MAX_COUNT) {
                return false;
            } else {
                if (broadCount == null) {
                    broadCount = 0;
                }
                fileCastCount.put(FILECAST_CACHE_FIELD, (broadCount.intValue() + 1));
                return true;
            }
        }
    }

    public static boolean addCustomizedCastCount() {
        synchronized (customizedCastlock) {
            Integer broadCount = customizedCastCount.getIfPresent(CUSTOMIZEDCAST_CACHE_FIELD);
            if (broadCount != null && broadCount.intValue() >= CUSTOMIZEDCAST_MAX_COUNT) {
                return false;
            } else {
                if (broadCount == null) {
                    broadCount = 0;
                }
                customizedCastCount.put(CUSTOMIZEDCAST_CACHE_FIELD, (broadCount.intValue() + 1));
                return true;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getCurrentDefaultDateString());
    }

}
