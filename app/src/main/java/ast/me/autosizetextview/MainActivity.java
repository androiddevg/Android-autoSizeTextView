package ast.me.autosizetextview;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Random;

import ast.me.autosizetextview.customeView.ArrowDirection;
import ast.me.autosizetextview.customeView.BubbleLayout;
import ast.me.autosizetextview.customeView.BubblePopupHelper;

// custome bubble layout

public class MainActivity extends Activity {

    private TextView mOutput, mAutofitOutput;
    private ConstraintLayout constraintLayout;
    private BubbleLayout bubbleLayout = null;
    private TextView tv_text = null;
    private PopupWindow popupWindow = null;
    private Random random = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ThemeApp_Green);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutput = (TextView) findViewById(R.id.output);
        mAutofitOutput = (TextView) findViewById(R.id.output_autofit);
        constraintLayout = (ConstraintLayout) findViewById(R.id.main_ll);

        // create bubble view to show full text
        createBubbleView();

        // set on clicks
        mOutput.setOnClickListener(new mClickListener());
        constraintLayout.setOnClickListener(new mClickListener());
        mOutput.setOnLongClickListener(new mLongClickListener());


        // get new entered text

        ((EditText) findViewById(R.id.input)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                mOutput.setText(charSequence);
                mAutofitOutput.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });
    }

    private void createBubbleView() {

        if (bubbleLayout == null) {
            bubbleLayout = (BubbleLayout) LayoutInflater.from(this).inflate(R.layout.layout_sample_popup, null);
            tv_text = bubbleLayout.findViewById(R.id.tv_text);

        }

        if (popupWindow == null) {
            popupWindow = BubblePopupHelper.create(this, bubbleLayout);
            popupWindow.setOutsideTouchable(false);
        }

        if (random == null) random = new Random();

    }

    @Override
    protected void onStop() {
        clearEditTex();
        super.onStop();
    }

    ;

    private void clearEditTex() {
        ViewGroup group = (ViewGroup) findViewById(R.id.main_ll);
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }
        }
    }

    ;

    @Override
    protected void onDestroy() {
        clearEditTex();
        super.onDestroy();
    }

    public class mClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == mOutput.getId()) {
                popupWindow.dismiss();

            } else if (v.getId() == constraintLayout.getId()) {
                popupWindow.dismiss();

            }
        }
    }

    public class mLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {

            if (v.getId() == mOutput.getId()) {

                int[] location = new int[2];
                v.getLocationInWindow(location);
                if (random.nextBoolean()) {
                    bubbleLayout.setArrowDirection(ArrowDirection.TOP);
                } else {
                    bubbleLayout.setArrowDirection(ArrowDirection.BOTTOM);
                }
                tv_text.setText(((EditText) findViewById(R.id.input)).getText().toString());
                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], v.getHeight() + location[1]);
            }

            return false;
        }

    }
}