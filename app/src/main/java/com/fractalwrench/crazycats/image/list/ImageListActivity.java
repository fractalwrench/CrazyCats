package com.fractalwrench.crazycats.image.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.fractalwrench.crazycats.BaseActivity;
import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.image.data.ImageData;
import com.fractalwrench.crazycats.image.detail.ImageDetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Displays a searchable list of Images, and allows the user to select an item to view in more
 * detail, which will launch the {@link ImageDetailActivity}
 */
public class ImageListActivity extends BaseActivity implements ImageListView {

    private ImageListComponent imageListComponent;
    @Inject ImageListPresenter presenter;
    @Inject RecyclerView.LayoutManager layoutManager;
    @Inject ImageListAdapter imageListAdapter;
    @Inject RecyclerView.ItemDecoration itemDecoration;

    @BindView(R.id.image_list_root) ViewFlipper viewFlipper;
    @BindView(R.id.image_list_content) RecyclerView contentView;
    @BindView(R.id.image_list_err) TextView errorView;
    @BindView(R.id.image_list_progress) View progressBar;

    private ImageListView.CellDelegate delegate;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_image_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageListComponent = getAppComponent().plus(new ImageListModule(this));
        imageListComponent.inject(this);

        contentView.setLayoutManager(layoutManager);
        contentView.setAdapter(imageListAdapter);
        contentView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageListComponent = null; // release reference
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(this);
        imageListAdapter.setDelegate(delegate);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
        imageListAdapter.setDelegate(null);
    }


    /** ImageListView **/


    @Override
    public void setDelegate(@Nullable CellDelegate delegate) {
        this.delegate = delegate;
        imageListAdapter.setDelegate(delegate);
    }

    @Override
    public void showProgress() {
        viewFlipper.setDisplayedChild(VIEW_PROGRESS);
    }

    @Override
    public void showContent(@NonNull List<ImageData> content) {
        viewFlipper.setDisplayedChild(VIEW_CONTENT);
        imageListAdapter.updateImages(content);
        imageListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        viewFlipper.setDisplayedChild(VIEW_ERR);
        errorView.setText(errorMessage);
    }

    @Override
    public void showImageDetail(ImageData imageData) {
        startActivity(ImageDetailActivity.getIntent(this, imageData));
    }
}
