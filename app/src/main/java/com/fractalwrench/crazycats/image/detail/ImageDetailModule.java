package com.fractalwrench.crazycats.image.detail;


import com.fractalwrench.crazycats.image.data.ImageDataRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageDetailModule {

    @Provides
    ImageDetailPresenter imageDetailPresenter(ImageDataRepository repository) {
        return new ImageDetailPresenter(repository);
    }

}
