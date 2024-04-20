package com.valentinerutto.myvideoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.valentinerutto.myvideoplayer.ui.MyAppBar
import com.valentinerutto.myvideoplayer.ui.VideoViewModel
import com.valentinerutto.myvideoplayer.ui.navigation.NavGraph
import com.valentinerutto.myvideoplayer.ui.theme.MyVideoPlayerTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    val viewmodel: VideoViewModel by viewModel<VideoViewModel>()
    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewmodel.getVideosList()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyVideoPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val uiState = viewmodel.state.collectAsState()
                    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

                    Scaffold(modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {

                        MyAppBar(navController = navController, scrollBehavior)

                    },

//                        , floatingActionButton = {
//                        IconButton(onClick = {
//                            scope.launch {
//                                viewmodel.refreshData()
//                            }
//
//                        }) {
//                            Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
//                        }
//
//                    },
                        content = {

                            NavGraph(
                                navController = navController,
                                modifier = Modifier.padding(it),
                                uiState = uiState.value
                            )
                        })
                }

            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyVideoPlayerTheme {
        Greeting("Android")
    }
}