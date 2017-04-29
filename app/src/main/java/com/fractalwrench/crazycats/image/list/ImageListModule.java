package com.fractalwrench.crazycats.image.list;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fractalwrench.crazycats.image.data.ImageDataRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageListModule {

    private final Context context;

    public ImageListModule(Context context) {
        this.context = context;
    }

    @Provides
    ImageListPresenter imageListPresenter(ImageDataRepository repository) {
        return new ImageListPresenter(repository);
    }

    @Provides
    RecyclerView.LayoutManager layoutManager() {
        return new LinearLayoutManager(context);
    }

    @Provides
    ImageListAdapter imageListAdapter() {
        return new ImageListAdapter();
    }

}
