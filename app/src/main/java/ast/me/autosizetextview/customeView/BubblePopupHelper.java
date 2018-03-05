package ast.me.autosizetextview.customeView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import ast.me.autosizetextview.R;

/**
 * Created by Admin on 03-03-2018.
 */

public class BubblePopupHelper {

    public static PopupWindow create(@NonNull Context context, @NonNull BubbleLayout bubbleLayout) {

        PopupWindow popupWindow = new PopupWindow(context);

        popupWindow.setContentView(bubbleLayout);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // change background color to transparent
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.popup_window_transparent));

        return popupWindow;
    }

}