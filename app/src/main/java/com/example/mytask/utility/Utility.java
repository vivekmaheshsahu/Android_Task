package com.example.mytask.utility;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.mytask.database.DatabaseManager;

public class Utility {
    public static String TAG = Utility.class.getName();

    public static SQLiteDatabase getDatabase() {
        return DatabaseManager.getInstance().openDatabase();
    }

    public static void animateView(final View view, final int toVisibility, float toAlpha, int duration) {
        boolean show = toVisibility == View.VISIBLE;
        if (show) {
            view.setAlpha(0);
        }
        view.setVisibility(View.VISIBLE);
        view.animate()
                .setDuration(duration)
                .alpha(show ? toAlpha : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(toVisibility);
                    }
                });
    }
}
