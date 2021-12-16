package com.zee.streamingwithexoplayer.presentation.home_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@ExperimentalMaterialApi
@Composable
fun VideoCard(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    thumb: String,
    onCardClick:() ->Unit
) {


    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp), elevation = 3.dp, onClick = onCardClick) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f, false),
                painter = rememberImagePainter(data = thumb),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(text = title, style = MaterialTheme.typography.body2)
        }


    }

}

@ExperimentalMaterialApi
@Preview
@Composable
fun SingleAlbumCardPreview() {
    VideoCard(Modifier, "", "", "") {}
}


