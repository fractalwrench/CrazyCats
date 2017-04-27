package com.fractalwrench.crazycats.injection.app;

import com.fractalwrench.crazycats.CrazyCatsApp;
import com.fractalwrench.crazycats.data.DataModule;
import com.fractalwrench.crazycats.image.detail.ImageDetailComponent;
import com.fractalwrench.crazycats.image.detail.ImageDetailModule;
import com.fractalwrench.crazycats.image.list.ImageListComponent;
import com.fractalwrench.crazycats.image.list.ImageListModule;
import com.fractalwrench.crazycats.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Defines a Parent Component. Each activity defines a {@link dagger.Subcomponent}, which provides
 * UI dependencies with an {@link com.fractalwrench.crazycats.injection.ActivityScope}, and
 * long-lived singleton dependencies such as Rest Clients.
 */
@Singleton
@Component(modules = {AppModule.class, SchedulerModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {

    void inject(CrazyCatsApp app);

    ImageListComponent plus(ImageListModule imageListModule);
    ImageDetailComponent plus(ImageDetailModule imageDetailModule);

}
