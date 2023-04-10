package com.vitorthemyth.composecrashcourse.observe_connectivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vitorthemyth.composecrashcourse.observe_connectivity.ui.theme.ComposeCrashCourseTheme
import kotlinx.coroutines.flow.collect

class ConnectivityChecker : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectionObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectionObserver(this)
        setContent {
            ComposeCrashCourseTheme {

                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectionObserver.Status.Unavailable
                )

                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Network Status: $status")
                }
            }
        }
    }
}

