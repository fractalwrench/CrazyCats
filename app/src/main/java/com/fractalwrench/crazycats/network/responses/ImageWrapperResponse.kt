package com.fractalwrench.crazycats.network.responses

data class ImageWrapperResponse(val source: ImageResponse?, val resolutions: List<ImageResponse>?)
data class ImageResponse(val url: String?, val width: Int, val height: Int)
data class Preview(val images: List<ImageWrapperResponse>?)