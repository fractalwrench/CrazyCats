package com.fractalwrench.crazycats.image.list;


import com.fractalwrench.crazycats.injection.ActivityScope;

import dagger.Subcomponent;

/**
 * Defines dependencies specific to the {@link ImageListActivity}
 */
@ActivityScope
@Subcomponent(modules = ImageListModule.class)
public interface ImageListComponent {

    void inject(ImageListActivity activity);

}
