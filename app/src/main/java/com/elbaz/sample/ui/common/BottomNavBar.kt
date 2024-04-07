package com.elbaz.sample.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.elbaz.architectur_smaple.R
import com.elbaz.sample.ui.theme.PindjurRed
import com.elbaz.sample.ui.theme.WhiteEdgar
import com.elbaz.sample.ui.theme.grey

@Composable
@Preview
private fun BottomNavBarPreview(){
BottomNavBar(Modifier)

}

@Composable
fun BottomNavBar(modifier: Modifier){

    Row(modifier.zIndex(1f).shadow(4.dp,RoundedCornerShape(50),clip = true).background(PindjurRed, shape = RoundedCornerShape(50)).padding(horizontal = 32.dp, vertical = 4.dp),
horizontalArrangement =Arrangement.spacedBy(42.dp)
        ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = "Home Icon", tint = WhiteEdgar)

        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = "Home Icon", tint = grey)

        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.ic_compass), contentDescription = "Home Icon",tint = grey)

        }

    }

}