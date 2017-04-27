package com.fractalwrench.crazycats.injection;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

/**
 * Defines io/ui schedulers (which are swapped out to facilitate testing)
 */
public class DefaultSchedulers {

    private final Scheduler io;
    private final Scheduler ui;

    public DefaultSchedulers(Scheduler io, Scheduler ui) {
        this.io = io;
        this.ui = ui;
    }

    public Scheduler io() {
        return io;
    }

    public Scheduler ui() {
        return ui;
    }

    /**
     * Should be called using {@link Observable#compose(ObservableTransformer)} to set the default
     * schedulers for an observable.
     *
     * @param observable the observable
     * @param <T>        type
     * @return the observable with newly set schedulers
     */
    public <T> Observable<T> apply(Observable<T> observable) {
        return observable.subscribeOn(io)
                         .observeOn(ui);
    }

}
