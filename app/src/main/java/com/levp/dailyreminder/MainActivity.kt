package com.levp.dailyreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.levp.dailyreminder.ui.tabs.TabScreen
import com.levp.dailyreminder.ui.theme.DailyReMinderTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ReMinderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDataFromDB()
        setContent {
            DailyReMinderTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Text(text = "+")
                        }
                    },
                    content = { padding ->
                        padding
                        TabScreen(viewModel)
                    }
                )
            }
        }
    }
}
