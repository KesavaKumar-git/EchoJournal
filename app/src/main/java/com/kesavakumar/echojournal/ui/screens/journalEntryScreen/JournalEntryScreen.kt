package com.kesavakumar.echojournal.ui.screens.journalEntryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.kesavakumar.echojournal.ui.components.AudioCard
import com.kesavakumar.echojournal.ui.components.PrimaryButton
import com.kesavakumar.echojournal.ui.components.SecondaryButton
import com.kesavakumar.echojournal.ui.theme.EchoJournalTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEntryScreen()
{
    var text by remember { mutableStateOf("My Entry") }

    var description by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "New Entry",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.secondary)
                    }
                }
            )
        },
        bottomBar = {
            Row(
               modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SecondaryButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(3f)
                )

                PrimaryButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(7f)
                ) { isEnabled ->

                    Text(
                        text = "Save",
                        style = MaterialTheme.typography.headlineSmall,
                        color = if (isEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.outline)
                }


            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Mood",
                    modifier = Modifier.padding(end = 5.dp).background(MaterialTheme.colorScheme.inverseOnSurface, CircleShape).padding(6.dp).size(22.dp),
                    tint = MaterialTheme.colorScheme.inversePrimary
                )

                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    decorationBox = {

                        if(text.isEmpty())
                        {
                            Text(
                                text = "Add Title...",
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                        }

                        it()
                    }
                )
            }

            AudioCard(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.inverseOnSurface)

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "#",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )

                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = {
                        if (description.isEmpty())
                        {
                            Text(
                                text = "Topic",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.outlineVariant)
                        }

                        it()
                    }
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Add Mood",
                    tint = MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.size(20.dp))

                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = {
                        if (description.isEmpty())
                        {
                            Text(
                                text = "Add Description...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.outlineVariant)
                        }

                        it()
                    }
                )
            }

        }
    }
}

@PreviewLightDark
@Preview
@Composable
private fun NewEntryScreenPreview()
{
    EchoJournalTheme {
        NewEntryScreen()
    }
}