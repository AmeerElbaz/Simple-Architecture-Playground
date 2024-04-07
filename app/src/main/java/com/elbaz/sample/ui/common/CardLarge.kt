package com.elbaz.sample.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.elbaz.sample.data.models.AnimeModel
import com.elbaz.sample.data.local.Category
import com.elbaz.sample.ui.theme.WhiteEdgar
import com.elbaz.sample.ui.common.shimmerLoadingAnimation

@Composable
fun CardLarge(item: AnimeModel, isLoading: Boolean) {
    ConstraintLayout(
        Modifier
            .height(220.dp)
            .width(256.dp)
            .clip(RoundedCornerShape(10))
            .shimmerLoadingAnimation(isLoading),

        ) {
        val (starIcon, rating, chaptersNumber, animeName) = createRefs()

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = if (isLoading) Color.Transparent else item.imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop, colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply { setToSaturation(0.8f) },
            )
        )
        Icon(
            modifier = Modifier.constrainAs(starIcon) {
                bottom.linkTo(parent.bottom, 8.dp)
                start.linkTo(parent.start, 4.dp)
            },
            imageVector = Icons.Filled.Star,
            contentDescription = "Rating icon",
            tint = if (isLoading) Color.Transparent else Color.Yellow
        )
        Text(modifier = Modifier.constrainAs(rating) {
            bottom.linkTo(parent.bottom, 8.dp)
            start.linkTo(starIcon.end, 2.dp)
        }, text = if (isLoading) "" else item.rating, color = WhiteEdgar)
        Text(modifier = Modifier.constrainAs(chaptersNumber) {

            bottom.linkTo(parent.bottom, 8.dp)
            start.linkTo(rating.end, 2.dp)

        }, text = if (isLoading) "" else "Chapters: 1070", color = WhiteEdgar)
        Text(
            modifier = Modifier.constrainAs(animeName) {
                bottom.linkTo(rating.top, 8.dp)
                start.linkTo(parent.start, 4.dp)

            }, text = if (isLoading) "" else item.name, color = WhiteEdgar,
            fontSize = 18.sp, fontWeight = FontWeight(800)
        )
    }

}

@Preview
@Composable
fun CardLargePreview() {
    CardLarge(AnimeModel("My Hero Academia", "", "5",Category.POPULAR), isLoading = false)
}