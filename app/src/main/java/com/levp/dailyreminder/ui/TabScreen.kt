package com.levp.dailyreminder.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.levp.dailyreminder.classes.TabItem
import com.levp.dailyreminder.ui.calendar.ReMinderCalendar
import com.levp.dailyreminder.ui.list.ReMinderList
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(
    content: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Text(text = content)
        val tabs = listOf(
            TabItem(
                title = "List",
                icon = Icons.Filled.List,
                screen = { ReMinderList() }
            ),
            TabItem(
                title = "Calendar",
                icon = Icons.Filled.DateRange,
                screen = { ReMinderCalendar() }
            )
        )
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = { tabs.size }
        )
        val coroutineScope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, item ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        text = { Text(text = item.title) },
                        icon = { Icon(item.icon, "") },
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    )
                }
            }
            HorizontalPager(state = pagerState) { page ->
                tabs[page].screen()
            }
        }
    }
}

/*
HorizontalPager(
modifier = Modifier.fillMaxSize(),
state = pagerState,
pageSpacing = 0.dp,
userScrollEnabled = true,
reverseLayout = false,
contentPadding = PaddingValues(0.dp),
beyondBoundsPageCount = 0,
pageSize = PageSize.Fill,
key = null,
pageContent = {
    tabs[pagerState.currentPage].screen()
}
)*/
