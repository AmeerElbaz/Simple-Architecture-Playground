package com.elbaz.sample.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.elbaz.sample.data.models.AnimeModel
import com.elbaz.sample.data.local.Category
import com.elbaz.sample.ui.theme.PoppinsFont
import com.elbaz.sample.ui.theme.WhiteEdgar
import com.elbaz.sample.ui.theme.shimmerLoadingAnimation
import com.elbaz.sample.ui.theme.spacing

@Preview
@Composable
private fun CardSmallPreview() {
    CardSmall(
        AnimeModel("My Hero Academia", "", "5", Category.SOON),
        false,
        )

}

@Composable
fun CardSmall(item: AnimeModel, isLoading:Boolean) {
    Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier
                .width(128.dp)
                .height(190.dp)
                .padding(bottom = MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10))
                .shimmerLoadingAnimation(isLoading),
            model = if(isLoading) Color.Companion.Transparent else item.imageUrl ,
            contentDescription = "",
            contentScale = ContentScale.Crop,

        )
        Text(
            text = if(isLoading)" " else item.name,
            fontFamily = FontFamily(PoppinsFont),
            color = WhiteEdgar,
            fontSize = 10.sp
        )
    }

}