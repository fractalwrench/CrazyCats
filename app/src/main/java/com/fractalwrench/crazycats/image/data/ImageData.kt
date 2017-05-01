package com.fractalwrench.crazycats.image.data

/**
 * Model for a remote image source (thumbnail plus full-res URL) and its metadata.
 */
data class ImageData(val id: String?,
                     val imageUrl: String?,
                     val thumbnailUrl: String?,
                     val title: String?)
