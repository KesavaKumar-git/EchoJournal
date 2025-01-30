package com.kesavakumar.echojournal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kesavakumar.echojournal.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CustomBottomSheet(
    dismiss: () -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {
            dismiss()
        },
        sheetState = sheetState,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background,
        properties = ModalBottomSheetDefaults.properties(shouldDismissOnBackPress = false),
    ) {
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.statusBarsIgnoringVisibility))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bottom_sheet_drag_handle),
                contentDescription = "drag handle",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.outlineVariant)
            )
        }

        content()

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBarsIgnoringVisibility))
    }
}