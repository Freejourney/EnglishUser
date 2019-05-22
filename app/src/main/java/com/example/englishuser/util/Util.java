package com.example.englishuser.util;
/*
 * 文件名：Util
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */

import android.app.Activity;
import android.widget.Toast;

public class Util {

        /**
         * 显示toast
         * @param ctx
         * @param msg
         */
        public static void showToast(final Activity ctx, final String msg, int time) {
            // 判断是在子线程，还是主线程
            if ("main".equals(Thread.currentThread().getName())) {
                Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
            } else {
                // 子线程
                ctx.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
}
