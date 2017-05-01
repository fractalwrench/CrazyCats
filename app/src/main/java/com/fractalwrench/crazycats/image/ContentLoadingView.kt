package com.fractalwrench.crazycats.image

/**
 * A View which has 3 states - Error, Progress, and Content. This covers the 3 main conditions when
 * dynamically loading data from an API.

 * @param <T> the type of the content
</T> */
interface ContentLoadingView<T> {

    /**
     * The view should display progress to the user, indicating that data is being fetched.
     */
    fun showProgress()

    /**
     * The view should display the given content to the user.

     * @param content the content
     */
    fun showContent(content: T)

    /**
     * The view should display the error message to the user as content could not be fetched.

     * @param errorMessage the message
     */
    fun showError(errorMessage: String)

}
