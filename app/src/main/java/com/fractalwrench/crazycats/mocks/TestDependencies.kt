package com.fractalwrench.crazycats.mocks


import com.fractalwrench.crazycats.injection.DefaultSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

object TestDependencies {

    private fun schedulers(): DefaultSchedulers {
        val immediate = Schedulers.trampoline()
        return DefaultSchedulers(immediate, immediate)
    }

    @Throws(IOException::class)
    fun mockSuccessRepository(): MockSuccessRepository {
        return MockSuccessRepository(schedulers())
    }

    @Throws(IOException::class)
    fun mockFailureRepository(): MockFailureRepository {
        return MockFailureRepository()
    }

}
