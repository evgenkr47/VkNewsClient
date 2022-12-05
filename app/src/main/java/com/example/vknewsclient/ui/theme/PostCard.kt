package com.example.vknewsclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.R
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.StatisticItem
import com.example.vknewsclient.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeItemClickListener: (StatisticItem) -> Unit,
    onShareItemClickListener: (StatisticItem) -> Unit,
    onViewsItemClickListener: (StatisticItem) -> Unit,
    onCommentItemClickListener: (StatisticItem) -> Unit
){
    Card(modifier = modifier){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        postHeader(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = feedPost.contentText,
                    color = MaterialTheme.colors.onPrimary
                    )
            Spacer(modifier = Modifier.height(8.dp))
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    painter = painterResource(id = feedPost.contentImageResId),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                statistics = feedPost.statistics,
                onCommentItemClickListener = onCommentItemClickListener,
                onShareItemClickListener = onShareItemClickListener,
                onViewsItemClickListener = onViewsItemClickListener,
                onLikeItemClickListener = onLikeItemClickListener)

        }

    }
}
@Composable
private fun postHeader(feedPost: FeedPost){
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)

        )
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = feedPost.publicationData,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )

    }
}



@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onLikeItemClickListener: (StatisticItem) -> Unit,
    onShareItemClickListener: (StatisticItem) -> Unit,
    onViewsItemClickListener: (StatisticItem) -> Unit,
    onCommentItemClickListener: (StatisticItem) -> Unit
){
    Row() {
        Row(modifier = Modifier.weight(1f)) {
            val viewItem = statistics.getItemByType(type = StatisticType.VIEWS)
            iconWithText(
                iconResId = R.drawable.ic_views_count,
                text = viewItem.count.toString(),
                onItemClickListener = {
                    onViewsItemClickListener(viewItem)
                }
            )
        }
        Row(modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.SpaceBetween) {
            val commentItem = statistics.getItemByType(type = StatisticType.COMMENTS)
            val shareItem = statistics.getItemByType(type = StatisticType.SHARES)
            val likeItem = statistics.getItemByType(type = StatisticType.LIKES)
            iconWithText(
                iconResId = R.drawable.ic_share,
                text = shareItem.count.toString(),
                onItemClickListener = {
                    onShareItemClickListener(shareItem)
                }
            )
            iconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentItem.count.toString(),
                onItemClickListener = {
                    onCommentItemClickListener(commentItem)
                }
            )
            iconWithText(
                iconResId = R.drawable.ic_like,
                text = likeItem.count.toString(),
                onItemClickListener = {
                    onLikeItemClickListener(likeItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type:StatisticType): StatisticItem{
    return this.find { it.type == type } ?: throw IllegalStateException()
}

@Composable
private fun iconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit
){
    Row(modifier = Modifier.clickable {
        onItemClickListener()
    },

        verticalAlignment = Alignment.CenterVertically
    ){
       Icon(
           painter = painterResource(id = iconResId),
           contentDescription = null,
           tint = MaterialTheme.colors.onSecondary
           )
        Spacer(modifier = Modifier.width(4.dp))
       Text(text = text,
            color = MaterialTheme.colors.onSecondary
           )
    }
  
}

