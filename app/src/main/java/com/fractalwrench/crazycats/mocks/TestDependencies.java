package com.fractalwrench.crazycats.mocks;


import com.fractalwrench.crazycats.injection.DefaultSchedulers;

import java.io.IOException;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestDependencies {

    private static DefaultSchedulers schedulers() {
        Scheduler immediate = Schedulers.trampoline();
        return new DefaultSchedulers(immediate, immediate);
    }

    public static MockSuccessRepository mockSuccessRepository() throws IOException {
        return new MockSuccessRepository(schedulers());
    }

    public static MockFailureRepository mockFailureRepository() throws IOException {
        return new MockFailureRepository();
    }

}
