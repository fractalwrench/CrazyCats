package com.fractalwrench.crazycats;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fractalwrench.crazycats.injection.app.AppComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected static final int VIEW_PROGRESS = 0; // views ordered by convention in layout XML
    protected static final int VIEW_ERR = 1;
    protected static final int VIEW_CONTENT = 2;

    /**
     * Sets the Layout ResId that the {@link BaseActivity} sets as its content view. This
     * automatically sets up {@link ButterKnife }binding.
     *
     * @return the layout resource id used by this activity.
     */
    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
    }

    protected AppComponent getAppComponent() {
        Application application = getApplication();

        if (!(application instanceof CrazyCatsApp)) {
            throw new ClassCastException("Application class must implement CrazyCatsApp!");
        }
        else {
            return ((CrazyCatsApp) application).getAppComponent();
        }
    }
}
