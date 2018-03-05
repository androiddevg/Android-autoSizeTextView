package ast.me.autosizetextview;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.view.WindowManager;

/**
 * Created by Admin on 03-03-2018.
 */

public class MyApp extends Application {

    private static Context mContext;
    private Activity mCurrentActivity = new MainActivity();

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mctx) {
        mContext = mctx;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getAllActivityState();

        setContext(getApplicationContext());
        MultiDex.install(this);

    }

    public void getAllActivityState() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                activity.setTitle(getResources().getString(R.string.app_name));
                setCurrentActivity(activity);

                activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }

            @Override
            public void onActivityStarted(Activity activity) {

                setCurrentActivity(activity);

            }

            @Override
            public void onActivityResumed(Activity activity) {
                setCurrentActivity(activity);

            }

            @Override
            public void onActivityPaused(Activity activity) {
                clearReferences();
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                clearReferences();

            }


        });
    }

    private void clearReferences() {
        Activity currActivity = getCurrentActivity();
        if (this.equals(currActivity)) setCurrentActivity(null);
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

}