package com.kesavakumar.echojournal.ui.components

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.kesavakumar.echojournal.ui.modal.Mood
import com.kesavakumar.echojournal.ui.modal.getMoodBgColor
import com.kesavakumar.echojournal.ui.modal.getMoodColor
import com.kesavakumar.echojournal.ui.modal.getMoodIconResId
import com.kesavakumar.echojournal.ui.modal.JournalUi
import com.kesavakumar.echojournal.ui.theme.EchoJournalTheme

@Composable
fun EchoJournalItem(
    journalUi: JournalUi
) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = journalUi.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f).padding(end = 8.dp))

                Text(
                    text = journalUi.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            journalUi.selectedMood.let {
                AudioCard(it.getMoodColor(), it.getMoodBgColor())
            }

            Spacer(Modifier.height(5.dp))

            if (!TextUtils.isEmpty(journalUi.description))
            {
                Text(
                    text = journalUi.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }


            TopicsHorizontalList(journalUi.topics)
        }
    }
}

@Composable
fun EchoJournalListItem(journalUi: JournalUi, isFirst: Boolean? = false, isLast: Boolean = false)
{
    Row(
        modifier = Modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (isFirst != null)
            {
                VerticalDivider(
                    modifier = Modifier.then(
                        if (isFirst)
                            Modifier.padding(top = 10.dp)
                        else if (isLast)
                            Modifier.height(10.dp)
                        else
                            Modifier
                    ),
                    thickness = 2.dp
                )
            }

            Image(
                painter = painterResource(journalUi.selectedMood.getMoodIconResId()),
                contentDescription = "Mood"
            )
        }

        EchoJournalItem(journalUi)
    }

}


@PreviewLightDark
@Preview
@Composable
private fun EchoJournalItemPreview()
{
    EchoJournalTheme {
        EchoJournalListItem(
            JournalUi(
                id = 0,
                title = "Title",
                date = "",
                time = "10:00 AM",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec odio nec nunc.",
                selectedMood = Mood.Neutral,
                topics = listOf("Topic", "Work")
            )
        )

    }
}