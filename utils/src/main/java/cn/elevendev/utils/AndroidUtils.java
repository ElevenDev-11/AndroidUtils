package cn.elevendev.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Map;

public class AndroidUtils {

    private AndroidUtils() {
    }


// ================================== 状态栏相关 ==================================


    /**
     * 设置状态栏颜色和状态栏图标颜色
     *
     * @param activity   当前 Activity
     * @param color 状态栏背景颜色
     * @param isDarkText 状态栏图标颜色，true 表示黑色，false 表示白色
     */
    public static void setStatusBar(Activity activity, int color, boolean isDarkText) {
        StatusBarUtil.setStatusBar(activity, color, isDarkText);
    }

    /**
     * 设置状态栏透明，并设置状态栏图标颜色
     *
     * @param activity   当前 Activity
     * @param isDarkText 状态栏图标颜色，true 表示黑色，false 表示白色
     */
    public static void setTransparentStatusBar(Activity activity, boolean isDarkText) {
        StatusBarUtil.setTransparentStatusBar(activity, isDarkText);
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        return StatusBarUtil.getStatusBarHeight(context);
    }




// ================================== 导航栏相关 ==================================


    /**
     * 获取导航栏的高度（像素）
     *
     * @param activity 当前 Activity
     * @return 导航栏高度（像素），如果导航栏被隐藏或高度小于 60 则返回 0
     */
    public static int getNavigationBarHeight(Activity activity) {
        return NavigationBarUtil.getNavigationBarHeight(activity);
    }

    /**
     * 判断导航栏是否可见
     *
     * @param activity 当前 Activity
     * @return true 表示可见，false 表示不可见
     */
    public static boolean isNavigationBarVisible(Activity activity) {
        return NavigationBarUtil.isNavigationBarVisible(activity);
    }

    /**
     * 隐藏导航栏
     *
     * @param activity 当前 Activity
     */
    public static void hideNavigationBar(Activity activity) {
        NavigationBarUtil.hideNavigationBar(activity);
    }

    /**
     * 显示导航栏
     *
     * @param activity 当前 Activity
     */
    public static void showNavigationBar(Activity activity) {
        NavigationBarUtil.showNavigationBar(activity);
    }

    /**
     * 设置导航栏颜色
     *
     * @param activity 当前 Activity
     * @param color    颜色值
     */
    public static void setNavigationBarColor(Activity activity, String color) {
        NavigationBarUtil.setNavigationBarColor(activity, color);
    }

    /**
     * 设置导航栏颜色
     *
     * @param activity 当前 Activity
     * @param color 颜色值（十六进制 int 类型，例如 0xFFFFFFFF）
     */
    public static void setNavigationBarColor(Activity activity, int color) {
        NavigationBarUtil.setNavigationBarColor(activity, color);
    }

    /**
     * 设置导航栏图标颜色
     *
     * @param activity 当前 Activity
     * @param dark 是否深色图标
     */
    public static void setNavigationBarIconDark(Activity activity, boolean dark) {
        NavigationBarUtil.setNavigationBarIconDark(activity, dark);
    }




// ================================== 屏幕相关 ==================================


    /**
     * 获取屏幕的宽度（像素）
     *
     * @param context 上下文
     * @return 屏幕宽度（像素）
     */
    public static int getScreenWidth(Context context) {
        return DisplayMetricsUtil.getScreenWidth(context);
    }

    /**
     * 获取屏幕的高度（像素）
     *
     * @param context 上下文
     * @return 屏幕高度（像素）
     */
    public static int getScreenHeight(Context context) {
        return DisplayMetricsUtil.getScreenHeight(context);
    }

    /**
     * 获取屏幕密度
     *
     * @param context 上下文
     * @return 屏幕密度
     */
    public static float getScreenDensity(Context context) {
        return DisplayMetricsUtil.getScreenDensity(context);
    }

    /**
     * 获取屏幕密度 DPI
     *
     * @param context 上下文
     * @return 屏幕密度 DPI
     */
    public static int getScreenDensityDpi(Context context) {
        return DisplayMetricsUtil.getScreenDensityDpi(context);
    }

    /**
     * px 转 dp
     *
     * @param context 上下文
     * @param px      px
     * @return dp
     */
    public static int px2dp(Context context, int px) {
        return DisplayMetricsUtil.px2dp(context, px);
    }

    /**
     * dp 转 px
     *
     * @param context 上下文
     * @param dp      dp
     * @return px
     */
    public static int dp2px(Context context, int dp) {
        return DisplayMetricsUtil.dp2px(context, dp);
    }

    /**
     * sp 转 px
     *
     * @param context 上下文
     * @param sp      sp
     * @return px
     */
    public static int sp2px(Context context, int sp) {
        return DisplayMetricsUtil.sp2px(context, sp);
    }

    /**
     * px 转 sp
     *
     * @param context 上下文
     * @param px      px
     * @return sp
     */
    public static int px2sp(Context context, int px) {
        return DisplayMetricsUtil.px2sp(context, px);
    }




// ================================== 时间相关 ==================================


    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public static String getCurrentTime() {
        return TimeUtil.getCurrentTime();
    }

    /**
     * 获取当前时间字符串
     *
     * @param pattern 时间格式
     * @return 当前时间字符串
     */
    public static String getCurrentTime(String pattern) {
        return TimeUtil.getCurrentTime(pattern);
    }

    /**
     * 获取当前年
     *
     * @return 当前年
     */
    public static int getCurrentYear() {
        return TimeUtil.getCurrentYear();
    }

    /**
     * 获取当前月
     *
     * @return 当前月
     */
    public static int getCurrentMonth() {
        return TimeUtil.getCurrentMonth();
    }

    /**
     * 获取当前日
     *
     * @return 当前日
     */
    public static int getCurrentDay() {
        return TimeUtil.getCurrentDay();
    }

    /**
     * 将时间戳（毫秒）转换为时间字符串
     *
     * @param timestamp 时间戳
     * @param pattern   时间格式
     * @return 格式化后的时间
     */
    public static String formatTime(long timestamp, String pattern) {
        return TimeUtil.formatTime(timestamp, pattern);
    }

    /**
     * 将时间字符串解析为时间戳（毫秒）
     *
     * @param timeStr   时间字符串
     * @param pattern   时间格式
     * @return 解析后的时间戳
     */
    public static long parseTime(String timeStr, String pattern) {
        return TimeUtil.parseTime(timeStr, pattern);
    }

    /**
     * 将秒级时间戳格式化为时间字符串
     *
     * @param seconds   时间戳
     * @param pattern   时间格式
     * @return 格式化后的时间
     */
    public static String formatSeconds(long seconds, String pattern) {
        return TimeUtil.formatSeconds(seconds, pattern);
    }

    /**
     * 将时间字符串转换为秒级时间戳
     *
     * @param timeStr 时间字符串
     * @param pattern 时间格式
     * @return 秒级时间戳，解析失败返回 -1
     */
    public static long parseToSeconds(String timeStr, String pattern) {
        return TimeUtil.parseToSeconds(timeStr, pattern);
    }




// ================================== 权限相关 ==================================


    public interface PermissionCallback {
        /** 权限已授予 */
        void onPermissionGranted();

        /** 权限被拒绝 */
        void onPermissionDenied();
    }

    /**
     * 判断是否拥有存储权限
     *
     * @param activity 当前 Activity
     * @return 是否有权限
     */
    public static boolean isStoragePermissionGranted(Activity activity) {
        return PermissionUtil.isStoragePermissionGranted(activity);
    }

    /**
     * 检查并请求存储权限（适配 Android 11 及以上）
     *
     * @param activity 当前 Activity
     */
    public static void requestStoragePermission(Activity activity) {
        PermissionUtil.requestStoragePermission(activity);
    }

    /**
     * 检查并请求存储权限（适配 Android 11 及以上）
     *
     * @param activity 当前 Activity
     * @param cb       权限请求回调
     */
    public static void requestStoragePermission(Activity activity, PermissionCallback cb) {
        PermissionUtil.requestStoragePermission(activity, cb);
    }

    /**
     * 判断是否拥有悬浮窗权限
     *
     * @param context 上下文
     * @return 是否有权限
     */
    public static boolean isFloatPermission(Context context) {
        return PermissionUtil.isFloatPermission(context);
    }

    /**
     * 检查并请求悬浮窗权限
     *
     * @param activity 当前 Activity
     */
    public static void requestFloatPermission(Activity activity) {
        PermissionUtil.requestFloatPermission(activity);
    }

    /**
     * 处理权限结果回调
     *
     * @param requestCode  请求码
     * @param grantResults 权限结果
     */
    public static void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        PermissionUtil.onRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理 Activity 结果回调
     *
     * @param requestCode 请求码
     */
    public static void onActivityResult(int requestCode) {
        PermissionUtil.onActivityResult(requestCode);
    }




// ================================== 版本相关 ==================================



    /**
     * 判断是否需要更新
     *
     * @param context 上下文
     * @param context 服务器版本号
     * @return 是否需要更新
     */
    public static boolean isUpdateNeeded(Context context, String serverVersion) {
        return VersionUtil.isUpdateNeeded(context, serverVersion);
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     * @return 版本号
     */
    public static String getVersionName(Context context) {
        return VersionUtil.getVersionName(context);
    }

    /**
     * 获取版本 code
     *
     * @param context 上下文
     * @return 版本 code
     */
    public static int getVersionCode(Context context) {
        return VersionUtil.getVersionCode(context);
    }

}
