package com.fractalwrench.crazycats.image.list;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.common.TextUtils;
import com.fractalwrench.crazycats.image.Presenter;
import com.fractalwrench.crazycats.image.data.ImageDataRepository;
import com.fractalwrench.crazycats.image.data.ImageSummary;

import java.util.List;

/**
 * Presents a {@link ImageListView}, and responds to UI events by fetching data if needed
 */
public class ImageListPresenter extends Presenter<ImageListView> implements ImageListView.CellDelegate {

    private final ImageDataRepository repository;
    private String searchTerm = "";

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
    public void onImageCellClicked(ImageSummary ImageSummary) {
        contentView.showImageDetail(ImageSummary);
    }


    /** Private impl **/


    private void fetchImageSuggestions() {
        contentView.showProgress();
        compositeDisposable.add(repository.fetchImageSummaries(searchTerm)
                                          .subscribe(this::handleImageFetchSuccess,
                                                          this::handleImageFetchFailure));
    }

    private void handleImageFetchSuccess(@NonNull List<ImageSummary> summaries) {
        if (summaries.isEmpty() && !TextUtils.isEmpty(searchTerm)) {
            String msg = String.format("No Images found matching '%s'", searchTerm);
            contentView.showError(msg);
        }
        else {
            contentView.showContent(summaries);
        }
    }

    private void handleImageFetchFailure(Throwable throwable) {
        // TODO would change message (and localise) depending on error condition in prod app
        contentView.showError("Failed to fetch Image list. Please check your connection.");
    }

}

