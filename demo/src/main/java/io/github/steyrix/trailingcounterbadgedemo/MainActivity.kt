package io.github.steyrix.trailingcounterbadgedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.steyrix.trailingcounterbadge.TrailingBadgeTabData
import io.github.steyrix.trailingcounterbadge.TrailingBadgeTabsViewData
import io.github.steyrix.trailingcounterbadge.TrailingCounterBadgeTabsView
import io.github.steyrix.trailingcounterbadgedemo.ui.theme.TrailingCounterBadgeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tabsData = TrailingBadgeTabsViewData(
            tabs = listOf(
                TrailingBadgeTabData(
                    count = 10,
                    text = "First",
                    isSelected = true
                ),
                TrailingBadgeTabData(
                    count = 5,
                    text = "Second",
                    isSelected = false
                ),
                TrailingBadgeTabData(
                    count = 123,
                    text = "LongLong",
                    isSelected = false
                )
            )
        )

        setContent {
            TrailingCounterBadgeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrailingCounterBadgeTabsView(
                        modifier = Modifier.fillMaxWidth(),
                        initialSelectionIndex = 0,
                        tabsData = tabsData
                    ) {}
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrailingCounterBadgeTheme {
        Greeting("Android")
    }
}