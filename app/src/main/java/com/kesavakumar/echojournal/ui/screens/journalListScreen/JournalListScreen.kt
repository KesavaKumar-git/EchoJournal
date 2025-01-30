package com.kesavakumar.echojournal.ui.screens.journalListScreen

import android.text.TextUtils
import android.widget.Toast
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kesavakumar.echojournal.R
import com.kesavakumar.echojournal.ui.components.CustomBottomSheet
import com.kesavakumar.echojournal.ui.components.EchoJournalListItem
import com.kesavakumar.echojournal.ui.components.JournalFilterChip
import com.kesavakumar.echojournal.ui.modal.getMoodIconResId
import com.kesavakumar.echojournal.ui.navigation.NavigationAction
import com.kesavakumar.echojournal.ui.theme.EchoJournalTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class)
@Composable
fun EchoJournalListScreen(uiState: JournalListState, onAction: (JournalListAction) -> Unit, navAction: (NavigationAction) -> Unit)
{
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF578CFF),
            Color(0xFF0057CC))
    )

    val bgGradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.inverseOnSurface,
            MaterialTheme.colorScheme.tertiaryContainer
        )
    )

    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        )
    )

    Scaffold(
        modifier = Modifier.background(brush = bgGradient),
        containerColor = Color.Transparent,
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Echo Journal",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Settings"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
                )

                FlowRow(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val (displayingMoods, moodIcons: List<Int>?) = remember(uiState.selectedMoods) {
                        uiState.selectedMoods.let {
                            when
                            {
                                it.isEmpty() -> Pair("All Moods", null)

                                it.size > 2 -> Pair("${it.take(2).joinToString(", ")} +${it.size - 2}", it.take(2).map { it.getMoodIconResId() })

                                else -> Pair(it.joinToString(", "), it.map { it.getMoodIconResId() }.toList())
                            }
                        }
                    }

                    val displayTopics = remember(uiState.selectedTopics) {
                        uiState.selectedTopics.let {
                            when
                            {
                                it.isEmpty() -> "All Topics"

                                it.size > 2 -> "${it.take(2).joinToString(", ")} +${it.size - 2}"

                                else -> it.joinToString(", ")
                            }
                        }
                    }

                    JournalFilterChip(
                        value = displayingMoods,
                        onAction = onAction,
                        onClear = { onAction(JournalListAction.ClearMoodFilter) },
                        leadingIcons = moodIcons,
                        selectedIds = uiState.selectedMoods.map { it.name }
                    )

                    JournalFilterChip(
                        value = displayTopics,
                        onAction = onAction,
                        onClear = { onAction(JournalListAction.ClearTopicFilter) },
                        leadingIcons = null,
                        list = listOf("a","work","personal", "private", "public"),
                        selectedIds = uiState.selectedTopics.toList()
                    )
                }
            }
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
            ){
                if (uiState.isRecording)
                {
                    repeat(2) { index ->
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .scale(scale)
                                .scale(1f + (index * 0.2f))
                                .alpha(0.3f - (index * 0.1f))
                                .background(brush = gradient, shape = CircleShape)
                        )
                    }
                }

                var offsetX by remember { mutableStateOf(0f) }

                var recording by remember { mutableStateOf(false) }

                Icon(
                    painter = painterResource(if (uiState.isRecording) R.drawable.ic_mic else R.drawable.ic_add_record),
                    contentDescription = "Record",
                    tint = Color.White,
                    modifier = Modifier
                        .background(brush = gradient, shape = CircleShape)
                        .size(64.dp)
                        .padding(16.dp)
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures(onDragStart = {
                                offsetX = 0f
                            }) { change, dragAmount ->
                                if (recording)
                                {
                                    change.consume()
                                    offsetX += dragAmount
                                    if (offsetX <= -50.dp.toPx())
                                    {
                                        onAction(JournalListAction.DiscardRecording)
                                    }
                                }
                            }
                        }
                        .combinedClickable(onClick = {
                            if (!uiState.isRecording)
                            {
                                onAction(JournalListAction.CanShowSheetToRecord(true))
                            }
                        }, onLongClick = {
                            if (!uiState.isRecording)
                            {
                                onAction(JournalListAction.StartRecording)
                                recording = true
                            }
                        })
                )
            }
        }
    ) { innerPadding ->

        Column(
            Modifier.padding(innerPadding)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
            ) {

                if(uiState.entries.isEmpty())
                {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(painterResource(R.drawable.ic_no_entries), contentDescription = "No Entries")

                        Spacer(Modifier.height(16.dp))

                        Text(
                            text = "No Entries",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.height(3.dp))

                        Text(
                            text = "Start recording your first Echo",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(Modifier.height(120.dp))
                    }
                }
                else
                {
                    LazyColumn {

                        uiState.entries.forEach { (date, entries) ->

                            item {

                                Text(
                                    text = date,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }

                            itemsIndexed(entries) { index, item ->

                                EchoJournalListItem(
                                    journalUi = item,
                                    isFirst = if (entries.size == 1) null else index == 0,
                                    isLast = index == 2)
                            }
                        }
                    }
                }
            }
        }
    }
    uiState.showSheetToRecord.let {
        if (it)
        {
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            val scope  = rememberCoroutineScope()
            val context = LocalContext.current

            CustomBottomSheet(
                dismiss = {
                    if(!uiState.isRecording && !uiState.isPaused)
                        onAction(JournalListAction.CanShowSheetToRecord(false))
                    else
                    {
                        scope.launch {
                            Toast.makeText(context, "Stop/Discard your recording to close the sheet", Toast.LENGTH_SHORT).show()
                            sheetState.show()
                        }
                    }
                },
                sheetState = sheetState,
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (uiState.isRecording) "Recording your memories..." else if(uiState.isPaused) "Recording paused" else "Record your Echo",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = uiState.recordingTime,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .padding(bottom = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconButton(
                        onClick = {
                            if (uiState.isRecording)
                            {
                                onAction(JournalListAction.DiscardRecording)
                            }
                            else
                            {
                                onAction(JournalListAction.CanShowSheetToRecord(false))
                            }
                        },
                        modifier = Modifier.background(color = MaterialTheme.colorScheme.errorContainer, shape = CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_discard),
                            contentDescription = "Discard",
                            tint = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp)
                        )
                    }

                    Box(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        if (uiState.isRecording)
                        {
                            repeat(2) { index ->
                                Box(
                                    modifier = Modifier
                                        .matchParentSize()
                                        .scale(scale)
                                        .scale(1f + (index * 0.2f))
                                        .alpha(0.3f - (index * 0.1f))
                                        .background(brush = gradient, shape = CircleShape)
                                )
                            }
                        }

                        Icon(
                            painter = painterResource(if (uiState.isRecording) R.drawable.ic_selected_tick else R.drawable.ic_mic),
                            contentDescription = "Record",
                            tint = Color.White,
                            modifier = Modifier
                                .background(brush = gradient, shape = CircleShape)
                                .size(72.dp)
                                .padding(20.dp)
                                .clickable { onAction(JournalListAction.StartRecording) }
                        )
                    }

                    IconButton(
                        onClick = { if (uiState.isRecording) onAction( JournalListAction.PauseRecording) else navAction(NavigationAction.NavigateToJournalAdd) },
                        enabled = uiState.isRecording || !TextUtils.isEmpty(uiState.recordingTime),
                        modifier = Modifier.background(color = MaterialTheme.colorScheme.onPrimaryContainer, shape = CircleShape)
                    ) {
                        Icon(
                            painter = if (uiState.isRecording) painterResource(R.drawable.ic_pause) else painterResource(R.drawable.ic_selected_tick),
                            contentDescription = "Pause/Done",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Preview
@Composable
private fun EchoJournalListScreenPreview()
{
    EchoJournalTheme {
        EchoJournalListScreen(JournalListState(), {}, {})
    }
}

@Composable
fun EchoJournalListScreenRoot(viewModel: JournalListVM = hiltViewModel())
{
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EchoJournalListScreen(uiState, viewModel::onAction, {})
}