@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)

package io.github.steyrix.trailingcounterbadge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val HORIZONTAL_OFFSET = 1.35f
private const val VERTICAL_OFFSET = 5f
private const val TEXT_SIZE_MULTIPLIER = 2f

@Composable
fun TrailingCounterBadgeTabsView(
    modifier: Modifier = Modifier,
    initialSelectionIndex: Int,
    tabsData: TrailingBadgeTabsViewData,
    tabTextStyle: TextStyle = TextStyle.Default,
    badgeTextStyle: TextStyle = TextStyle.Default,
    onTabSelection: @Composable (Int) -> Unit,
) {
    var selectedTabIndex by remember { mutableStateOf(initialSelectionIndex) }

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex
    ) {
        tabsData.tabs.forEachIndexed { index, data ->
            TrailingCounterBadgeTab(
                modifier = modifier,
                data = data,
                onClick = { selectedTabIndex = index },
                tabTextStyle = tabTextStyle,
                badgeTextStyle = badgeTextStyle
            )
        }
    }

    onTabSelection(selectedTabIndex)
}

@Composable
fun TrailingCounterBadgeTab(
    modifier: Modifier = Modifier,
    data: TrailingBadgeTabData,
    onClick: () -> Unit,
    tabTextStyle: TextStyle,
    badgeTextStyle: TextStyle
) {
    val badgeId = "badge"
    val annotatedText = buildAnnotatedString {
        append(data.text)
        appendInlineContent(badgeId, "[badge]")
    }

    val textDimensions = measureText(text = data.text, style = tabTextStyle)
    val badgeTextDimensions = measureText(
        text = data.count.toString(),
        style = badgeTextStyle
    )

    val inlineContent = buildInlineContent(
        badgeId,
        data.count,
        textDimensions,
        badgeTextDimensions
    )

    Tab(
        modifier = modifier,
        selected = data.isSelected,
        onClick = onClick,
    ) {
        Text(
            text = annotatedText,
            inlineContent = mapOf(inlineContent),
            style = tabTextStyle
        )
    }
}

private fun buildInlineContent(
    id: String,
    count: Int,
    textDimensions: Pair<Dp, Dp>,
    badgeTextDimensions: Pair<Dp, Dp>
): Pair<String, InlineTextContent> {
    val badgeTextDimensionsSp = Pair(
        badgeTextDimensions.first.value.sp,
        badgeTextDimensions.second.value.sp,
    )

    return Pair(
        id,
        InlineTextContent(
            Placeholder(
                width = badgeTextDimensionsSp.first * TEXT_SIZE_MULTIPLIER,
                height = badgeTextDimensionsSp.second * TEXT_SIZE_MULTIPLIER,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        ) {
            TrailingBadgeBox(
                text = count.toString(),
                tabTextWidth = textDimensions.first,
                tabTextHeight = textDimensions.second
            )
        }
    )
}

@Composable
private fun TrailingBadgeBox(
    modifier: Modifier = Modifier,
    text: String,
    tabTextWidth: Dp = 0.dp,
    tabTextHeight: Dp = 0.dp
) {
    BadgedBox(
        modifier = modifier
            .offset(
                x = tabTextWidth - tabTextWidth / HORIZONTAL_OFFSET,
                y = tabTextHeight + tabTextHeight / VERTICAL_OFFSET
            )
            .background(
                MaterialTheme.colorScheme.primary
            ),
        badge = {
            Badge {
                Text(
                    text = text
                )
            }
        }
    ) {}
}

@Composable
private fun measureText(text: String, style: TextStyle): Pair<Dp, Dp> {
    val textMeasurer = rememberTextMeasurer()
    val widthInPixels = textMeasurer.measure(text, style).size.width
    val heightInPixels = textMeasurer.measure(text, style).size.height
    return with(LocalDensity.current) {
        widthInPixels.toDp() to heightInPixels.toDp()
    }
}