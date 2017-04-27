package com.fractalwrench.crazycats.image.detail;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.Presenter;
import com.fractalwrench.crazycats.image.data.ImageDataRepository;


/**
 * Presents a {@link ImageDetailView} by responding to any UI events, and fetching data if needed.
 */
public class ImageDetailPresenter extends Presenter<ImageDetailView> {

    private final ImageDataRepository repository;
    private String id;

    public ImageDetailPresenter(ImageDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void start(@NonNull ImageDetailView contentView) {
        super.start(contentView);
        compositeDisposable.add(
                repository.fetchImageById(id)
                          .subscribe(contentView::showContent, this::displayErrorMessage)
        );
    }

    public void setId(String id) {
        this.id = id;
    }

    private void displayErrorMessage(Throwable throwable) {
        // TODO would change message depending on error condition in prod app
        contentView.showError("Failed to fetch Image details.");
    }

}
