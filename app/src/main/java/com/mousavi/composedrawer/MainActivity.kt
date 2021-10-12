package com.mousavi.composedrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mousavi.composedrawer.ui.theme.ComposeDrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDrawerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    var titleSize by remember {
        mutableStateOf(0)
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.primary),
                elevation = 18.dp
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                        .padding(start = 16.dp)
                )
                Text(
                    text = "Drawer Sample",
                    modifier = Modifier.padding(16.dp)
                )
            }
        },
        drawerScrimColor = Color.Magenta.copy(alpha = 0.2f),
        drawerShape = CutCornerShape(topEnd = with(LocalDensity.current) { (titleSize).toDp() }),
        drawerContent = {
            Text(
                text = "Jetpack Compose",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .onGloballyPositioned {
                        titleSize = it.size.height
                    }
                    .padding(24.dp),
                fontSize = 25.sp
            )

            Divider()

            Text(text = "Text 1", modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        scaffoldState.drawerState.close()
                        scaffoldState.snackbarHostState.showSnackbar("Text 1")
                    }
                }
                .padding(10.dp)
            )
            Text(
                text = "Text 2", modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                    .padding(10.dp)
            )
            Text(
                text = "Text 3", modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                    .padding(10.dp)
            )
            Text(
                text = "Text 4", modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                    .padding(10.dp)
            )
        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDrawerTheme {
        MainScreen()
    }
}