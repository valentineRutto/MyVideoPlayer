package com.valentinerutto.myvideoplayer.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.valentinerutto.myvideoplayer.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(navController: NavController, scrollBehavior: TopAppBarScrollBehavior) {

    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },

        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_play), contentDescription = "Back"
                )

            }
        }, scrollBehavior = scrollBehavior, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )


    )
}