package com.fractalwrench.crazycats.image.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.fractalwrench.crazycats.BaseActivity;
import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.image.data.ImageDetail;
import com.fractalwrench.crazycats.image.data.ImageSummary;

import javax.inject.Inject;

import butterknife.BindView;

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

    public static Intent getIntent(Context context, ImageSummary imageSummary) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putExtra(KEY_IMAGE_ID, imageSummary.getId());
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
        displayBackArrow();
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
    }

    private void displayBackArrow() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    /** ImageDetailView implementation **/


    @Override
    public void showProgress() {
        viewFlipper.setDisplayedChild(VIEW_PROGRESS);
    }

    @Override
    public void showContent(@NonNull ImageDetail imageDetail) {
        viewFlipper.setDisplayedChild(VIEW_CONTENT);
    }

    @Override
    public void showError(String errorMessage) {
        viewFlipper.setDisplayedChild(VIEW_ERR);
        errorView.setText(errorMessage);
    }

}