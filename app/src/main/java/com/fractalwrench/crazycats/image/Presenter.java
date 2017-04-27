package com.fractalwrench.crazycats.image;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;

import static com.fractalwrench.crazycats.common.PreConditions.checkNonNull;

/**
 * Presenters are responsible for responding to user interactions from a View, by
 * communicating with the model layer to determine what the view should display.
 */
public abstract class Presenter<T> {

    protected T contentView;
    protected CompositeDisposable compositeDisposable;

    private boolean isPresenting = false;

    /**
     * Notify the presenter that it should start telling its View how to display itself.
     */
    public void start(@NonNull T contentView) {
        checkNonNull(contentView);
        compositeDisposable = new CompositeDisposable();

        if (isPresenting) {
            throw new IllegalStateException("Already presenting, please call stop() first");
        }
        //noinspection ConstantConditions
        if (contentView == null) {
            throw new IllegalArgumentException("Presenter View cannot be null");
        }
        this.contentView = contentView;
        isPresenting = true;
    }

    /**
     * Notify the presenter that it should stop telling its View how to display itself.
     */
    public void stop() {
        if (!isPresenting) {
            throw new IllegalStateException("Already not presenting, please call start() first");
        }
        this.contentView = null;
        this.isPresenting = false;

        if (compositeDisposable.isDisposed()) {
            throw new IllegalStateException("Attempted to dispose an already disposed composite");
        }
        else {
            compositeDisposable.dispose();
        }
    }

}
