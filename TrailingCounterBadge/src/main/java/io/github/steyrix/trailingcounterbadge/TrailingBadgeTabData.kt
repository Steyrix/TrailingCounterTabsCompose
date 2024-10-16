package io.github.steyrix.trailingcounterbadge

import androidx.compose.runtime.Immutable

@Immutable
data class TrailingBadgeTabsViewData(
    val tabs: List<TrailingBadgeTabData>
)

@Immutable
data class TrailingBadgeTabData(
    val count: Int,
    val text: String,
    val isSelected: Boolean
)
