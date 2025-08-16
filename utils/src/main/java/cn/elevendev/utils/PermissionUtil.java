package cn.elevendev.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {

    private static final int REQUEST_WRITE_STORAGE = 10011;
    private static final int REQUEST_CODE_ALL_FILES_PERMISSION = 10012;
    private static final int REQUEST_CODE_FLOAT_PERMISSION = 10013;
    
    private static AndroidUtils.PermissionCallback callback;
    
    /**
     * 判断是否拥有存储权限
     *
     * @param activity 当前 Activity
     * @return 是否有权限
     */
    public static boolean isStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        }
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检查并请求存储权限（适配 Android 11 及以上）
     *
     * @param activity 当前 Activity
     */
    public static void requestStoragePermission(Activity activity) {
        if (isStoragePermissionGranted(activity)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requestAllFilesPermission(activity);
        } else {
            requestLegacyPermission(activity);
        }
    }
    
    /**
     * 检查并请求存储权限（适配 Android 11 及以上）
     *
     * @param activity 当前 Activity
     * @param cb       权限请求回调
     */
    public static void requestStoragePermission(Activity activity, AndroidUtils.PermissionCallback cb) {
        callback = cb;
        if (isStoragePermissionGranted(activity)) {
            if (callback != null) callback.onPermissionGranted();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requestAllFilesPermission(activity);
        } else {
            requestLegacyPermission(activity);
        }
    }

    /**
     * 检查悬浮窗权限
     *
     * @param context 上下文
     * @return 是否有权限
     */
    public static boolean isFloatPermission(Context context) {
        boolean canDrawOverlays;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            canDrawOverlays = Settings.canDrawOverlays(context);
        } else {
            canDrawOverlays = true;
        }

        // Android 4.4+，但 Settings.canDrawOverlays 无法使用时，进一步尝试 AppOps 检查
        if (!canDrawOverlays && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            if (appOpsManager == null) {
                return true; // 系统服务异常时，默认放行（保守策略）
            }

            String packageName = context.getPackageName();
            int uid = android.os.Process.myUid();
            int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_SYSTEM_ALERT_WINDOW, uid, packageName);

            if (mode == AppOpsManager.MODE_ALLOWED) {
                canDrawOverlays = true;
            }
        }

        return canDrawOverlays;
    }

    /**
     * 请求悬浮窗权限
     *
     * @param activity 当前 Activity
     */
    public static void requestFloatPermission(Activity activity) {
        requestFloatPermission(activity, "请手动前往设置开启悬浮窗权限");
    }

    /**
     * 请求悬浮窗权限
     *
     * @param activity      当前 Activity
     * @param fallbackMessage 降级提示信息
     */
    public static void requestFloatPermission(Activity activity, String fallbackMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            String packageName = activity.getPackageName();
            Uri uri = Uri.fromParts("package", packageName, null);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivityForResult(intent, 0);
        } else {
            if (fallbackMessage != null && !fallbackMessage.isEmpty()) {
                Toast.makeText(activity, fallbackMessage, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 请求读写存储权限
     *
     * @param activity 当前 Activity
     */
    private static void requestLegacyPermission(Activity activity) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(activity, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_WRITE_STORAGE);
        } else {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivityForResult(intent, REQUEST_WRITE_STORAGE);
        }
    }

    /**
     * 请求所有文件访问权限
     *
     * @param activity 当前 Activity
     */
    private static void requestAllFilesPermission(Activity activity) {
        if (!Environment.isExternalStorageManager()) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivityForResult(intent, REQUEST_CODE_ALL_FILES_PERMISSION);
        }
    }

    /**
     * 处理权限结果回调
     *
     * @param requestCode  请求码
     * @param grantResults 权限结果
     */
    public static void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (callback != null) callback.onPermissionGranted();
            } else {
                if (callback != null) callback.onPermissionDenied();
            }
            callback = null;
        }
    }

    /**
     * 处理 Android 11+ 文件权限结果回调
     *
     * @param requestCode 请求码
     */
    public static void onActivityResult(int requestCode) {
        if (requestCode == REQUEST_CODE_ALL_FILES_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    if (callback != null) callback.onPermissionGranted();
                } else {
                    if (callback != null) callback.onPermissionDenied();
                }
                callback = null;
            }
        }
    }

    public interface PermissionCallback {
        /** 权限已授予 */
        void onPermissionGranted();

        /** 权限被拒绝 */
        void onPermissionDenied();
    }

}
