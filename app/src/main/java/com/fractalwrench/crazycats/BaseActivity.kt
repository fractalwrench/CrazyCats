package com.fractalwrench.crazycats

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.fractalwrench.crazycats.injection.app.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    protected val VIEW_PROGRESS = 0 // views ordered by convention in layout XML
    protected val VIEW_ERR = 1
    protected val VIEW_CONTENT = 2

    /**
     * Sets the Layout ResId that the [BaseActivity] sets as its content view. This
     * automatically sets up [ButterKnife]binding.

     * @return the layout resource id used by this activity.
     */
    protected abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        ButterKnife.bind(this)
    }

    protected val appComponent: AppComponent
        get() {
            val application = application

            if (application !is CrazyCatsApp) {
                throw ClassCastException("Application class must implement CrazyCatsApp!")
            } else {
                return application.appComponent
            }
        }

}
