package com.fractalwrench.crazycats.image.list;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.Presenter;
import com.fractalwrench.crazycats.image.data.ImageData;
import com.fractalwrench.crazycats.image.data.ImageDataRepository;


/**
 * Presents a {@link ImageListView}, and responds to UI events by fetching data if needed
 */
public class ImageListPresenter extends Presenter<ImageListView> implements
                                                                 ImageListView.CellDelegate {

    private final ImageDataRepository repository;

    public ImageListPresenter(ImageDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void start(@NonNull ImageListView contentView) {
        super.start(contentView);
        contentView.setDelegate(this);
        fetchImageSuggestions();
    }

    @Override
    public void stop() {
        if (contentView != null) {
            contentView.setDelegate(null);
        }
        super.stop();
    }

    /** ImageListView.CellDelegate **/

    @Override
    public void onImageCellClicked(ImageData imageData) {
        contentView.showImageDetail(imageData);
    }

    void fetchImageSuggestions() {
        contentView.showProgress();
        compositeDisposable.add(repository.fetchImageSummaries()
                                          .subscribe(contentView::showContent,
                                                     this::handleImageFetchFailure));
    }

    /** Private impl **/

    private void handleImageFetchFailure(Throwable throwable) {
        // TODO would change message (and localise) depending on error condition in prod app
        contentView.showError("Failed to fetch your cats images! Tap here to retry.");
    }

}

