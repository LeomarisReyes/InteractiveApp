package com.example.interactiveapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                InteractiveApplication()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InteractiveApplication(){
    MaterialTheme (
        colorScheme = if(isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ){
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold( topBar ={
            TopAppBar(
                title = {  Text(text = stringResource(id = R.string.app_name), color = Color.White )},
                colors =  TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.black))
            ) },
            snackbarHost = {  SnackbarHost(hostState = snackbarHostState) }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                InteractiveApp(snackbarHostState)
            }
        }
    }
}