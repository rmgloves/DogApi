package com.rmgloves.dogapi.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.ui.theme.Dimens

@Composable
fun LayeredImage(
    imageItem: LayeredImageItem,
) {
    val backgroundModifier = imageItem.backgroundColor()?.let {
        Modifier.background(it, CircleShape)

    } ?: Modifier
    Box(modifier = imageItem.modifier.then(backgroundModifier), contentAlignment = Alignment.Center) {
        imageItem.layers().forEach { imageLayer ->
            Image(
                modifier = imageLayer.modifier,
                painter = painterResource(imageLayer.res),
                contentDescription = null,
                colorFilter = imageLayer.color?.let { ColorFilter.tint(it) }
            )
        }
    }
}

data class ImageLayer(
    @DrawableRes
    val res: Int,
    val color: Color?,
    val modifier: Modifier = Modifier
)

interface LayeredImageItem {
    @Composable
    fun backgroundColor(): Color?

    @Composable
    fun layers(): List<ImageLayer>

    val modifier: Modifier
}

object FallbackImage : LayeredImageItem {
    @Composable
    override fun backgroundColor() = MaterialTheme.colorScheme.primary

    @Composable
    override fun layers() = listOf(
        ImageLayer(
            res = R.drawable.placeholder_image,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(Dimens.SpaceM)
        )
    )

    override val modifier = Modifier.size(Dimens.ImageSize)
}

object ErrorImage : LayeredImageItem {
    @Composable
    override fun backgroundColor() = MaterialTheme.colorScheme.primary

    @Composable
    override fun layers() = listOf(
        ImageLayer(
            res = R.drawable.placeholder_image,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(Dimens.SpaceM)
        ),
        ImageLayer(
            res = R.drawable.ic_close,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(Dimens.ImageSize)
        )
    )

    override val modifier = Modifier.size(Dimens.ImageSize)

}



