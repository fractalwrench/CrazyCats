package com.fractalwrench.crazycats.image.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.fractalwrench.crazycats.BaseActivity;
import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.image.data.ImageData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;

import static com.fractalwrench.crazycats.R.id.image_detail_photo_view;

/**
 * Displays details about a single image, allowing the user to interact with it.
 */
public class ImageDetailActivity extends BaseActivity implements ImageDetailView {

    private static final String KEY_IMAGE_ID = "KEY_IMAGE_ID";

    private ImageDetailComponent imageDetailComponent;
    @Inject ImageDetailPresenter presenter;

    @BindView(R.id.image_detail_root) ViewFlipper viewFlipper;
    @BindView(R.id.image_detail_progress) View progressView;
    @BindView(R.id.image_detail_err) TextView errorView;
    @BindView(R.id.image_detail_content) View contentView;
    @BindView(image_detail_photo_view) ImageView photoView;
    @BindView(R.id.image_detail_backstory) TextView backStory;

    public static Intent getIntent(Context context, ImageData imageData) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putExtra(KEY_IMAGE_ID, imageData.getId());
        return intent;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_image_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageDetailComponent = getAppComponent().plus(new ImageDetailModule());
        imageDetailComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageDetailComponent = null; // release reference
    }

    @Override
    protected void onStart() {
        super.onStart();
        String summaryId = getIntent().getStringExtra(KEY_IMAGE_ID);
        presenter.setId(summaryId);
        presenter.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
        Picasso.with(this)
               .cancelRequest(photoView);
    }


    /** ImageDetailView implementation **/


    @Override
    public void showProgress() {
        viewFlipper.setDisplayedChild(VIEW_PROGRESS);
    }

    @Override
    public void showContent(@NonNull ImageData imageData) {
        viewFlipper.setDisplayedChild(VIEW_CONTENT);
        backStory.setText(imageData.getTitle());

        final Context context = this;

        // load thumbnail URL first as should already be in cache, then full image
        Picasso.with(context)
               .load(imageData.getThumbnailUrl())
               .placeholder(R.drawable.ic_photo_black_24dp)
               .error(R.drawable.ic_error_outline_black_24dp)
               .into(photoView, new Callback() {
                   @Override
                   public void onSuccess() {
                       Picasso.with(context)
                              .load(imageData.getImageUrl())
                              .placeholder(photoView.getDrawable()) // use thumbnail as placeholder
                              .into(photoView);
                   }

                   @Override
                   public void onError() {
                       // no-op
                   }
               });
    }

    @Override
    public void showError(String errorMessage) {
        viewFlipper.setDisplayedChild(VIEW_ERR);
        errorView.setText(errorMessage);
    }

}
