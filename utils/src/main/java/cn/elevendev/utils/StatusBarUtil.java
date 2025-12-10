package cn.elevendev.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Insets;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;

public class StatusBarUtil {

    /**
     * 设置状态栏颜色和状态栏图标颜色
     *
     * @param activity   当前 Activity
     * @param color 状态栏背景颜色
     * @param isDarkText 状态栏图标颜色，true 表示黑色，false 表示白色
     */
    public static void setStatusBar(Activity activity, int color, boolean isDarkText) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // Android 11+
            WindowInsetsController controller = window.getInsetsController();
            if (controller != null) {
                if (isDarkText) {
                    controller.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    );
                } else {
                    controller.setSystemBarsAppearance(
                            0,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    );
                }
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            int flags = decorView.getSystemUiVisibility();

            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            if (isDarkText) {
                flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(flags);
        }
    }


    /**
     * 沉浸式状态栏
     *
     * @param activity 当前 Activity
     * @param isDarkText 状态栏图标颜色，true 表示黑色，false 表示白色
     */
    public static void setTransparentStatusBar(Activity activity, boolean isDarkText) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            WindowInsetsController controller = window.getInsetsController();
            if (controller != null) {
                if (isDarkText) {
                    controller.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    );
                } else {
                    controller.setSystemBarsAppearance(
                            0,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    );
                }

                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_DEFAULT);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            if (isDarkText) {
                flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(flags);
        }

        View rootView = activity.findViewById(android.R.id.content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            rootView.setOnApplyWindowInsetsListener((v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsets.Type.systemBars());
                v.setPadding(
                        systemBars.left,
                        0,
                        systemBars.right,
                        systemBars.bottom
                );
                return insets.consumeSystemWindowInsets();
            });
        } else {
            rootView.setFitsSystemWindows(true);
        }
    }
    
    /**
     * 获取状态栏的高度
     *
     * @param context 当前上下文对象
     * @return 状态栏的高度（单位：像素）
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
