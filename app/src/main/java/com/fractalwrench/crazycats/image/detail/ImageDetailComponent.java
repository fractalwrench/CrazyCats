package com.fractalwrench.crazycats.image.detail;


import com.fractalwrench.crazycats.injection.ActivityScope;

import dagger.Subcomponent;

/**
 * Defines dependencies specific to the {@link ImageDetailActivity}. These objects follow the
 * scope of the activity lifecycle.
 */
@ActivityScope
@Subcomponent(modules = ImageDetailModule.class)
public interface ImageDetailComponent {

    void inject(ImageDetailActivity activity);

}
