package com.levp.dailyreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.levp.dailyreminder.ui.editreminder.EditReminderScreen
import com.levp.dailyreminder.ui.list.ReminderListEvent
import com.levp.dailyreminder.ui.tabs.TabScreen
import com.levp.dailyreminder.ui.theme.DailyReMinderTheme
import com.levp.dailyreminder.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyReMinderTheme {
                val viewModel: ReMinderViewModel = hiltViewModel()
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                NavHost(navController = navController, startDestination = Routes.REMINDER_BASE) {
                    composable(Routes.REMINDER_BASE) {
                        Scaffold(
                            scaffoldState = scaffoldState,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background),
                            floatingActionButton = {
                                FloatingActionButton(onClick = {
                                    viewModel.onEvent(ReminderListEvent.OnAddReminderClick)
                                }) {
                                    Text(text = "+")
                                }
                            },
                            content = { padding ->
                                padding
                                TabScreen(
                                    onNavigate = { navController.navigate(it.route) },
                                    scaffoldState = scaffoldState,
                                    viewModel = viewModel,
                                )
                            }
                        )
                    }
                    composable(Routes.EDIT_REMINDER) {
                        EditReminderScreen(onPopBackStack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
