package com.example.movies.presentation.main_screen.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.R
import com.example.movies.domain.model.Item
import com.example.movies.ui.theme.MoviesTheme

@Composable
fun ItemCard(
    item: Item,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var googlePlayIntent: Intent? = null

    if (item.googleStoreUrl != null) {
        googlePlayIntent = remember {
            Intent(Intent.ACTION_VIEW, Uri.parse(item.googleStoreUrl))
        }
    }

    var iosStoreIntent: Intent? = null

    if (item.appStoreUrl != null) {
        iosStoreIntent = remember {
            Intent(Intent.ACTION_VIEW, Uri.parse(item.appStoreUrl))
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {

            Box(
                modifier = Modifier.padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(item.logoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = item.name ?: "Null",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(4.dp)
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.type ?: "Null",
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = {
                            if (item.googleStoreUrl != null)
                                context.startActivity(googlePlayIntent)
                        }
                    ) {

                        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)

                    }
                    IconButton(
                        onClick = {
                            if (item.appStoreUrl != null)
                                context.startActivity(iosStoreIntent)
                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ios), contentDescription = null)
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun ItemCardPreview() {
    MoviesTheme {
        ItemCard(
            item = Item(
                name = "Item 1",
                type = "Type 1",
                logoUrl = "",
                appStoreUrl = "",
                googleStoreUrl = ""
            )
        )
    }
}