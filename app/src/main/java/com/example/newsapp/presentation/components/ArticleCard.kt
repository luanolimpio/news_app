package com.example.newsapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article
import com.example.newsapp.ui.theme.Shapes

@Composable
fun ArticleCard(
    article: Article,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = Shapes.medium
    ) {
        Row {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(128.dp),
                model = ImageRequest.Builder(LocalContext.current).data(article.urlToImage)
                    .size(Size.ORIGINAL)
                    .crossfade(true).build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                loading = {
                    Box {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                },
                error = {
                    Icon(
                        painter = painterResource(R.drawable.ic_broken_image),
                        contentDescription = null
                    )
                },
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = article.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
                article.description?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it, maxLines = 2,
                        overflow = TextOverflow.Ellipsis, style = TextStyle(fontSize = 14.sp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Badge {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = article.sourceName,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardPreview() {
    ArticleCard(
        article = Article(
            sourceName = "BBC News",
            author = null,
            title = "Magical Mets win Game 3, push Phillies to brink - ESPN",
            description = "It comes after a U.S. judge found Google guilty of holding a monopoly in general search.",
            url = "https://www.espn.com/mlb/story/_/id/41688700/new-york-mets-defeat-philadelphia-phillies-7-2-game-3",
            publishedAt = "2024-10-09T02:31:00Z",
            urlToImage = "https://a4.espncdn.com/combiner/i?img=%2Fphoto%2F2024%2F1009%2Fr1398113_1296x729_16%2D9.jpg",
        ),
        onClick = {}
    )
}