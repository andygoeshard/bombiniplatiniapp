package com.andyl.bombiniplantini.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andyl.bombiniplantini.presentation.viewmodel.RiegoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ControlRiegoScreen() {

    val viewModel = koinViewModel<RiegoViewModel>()

    val estado by viewModel.estado.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = estado)
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(onClick = { viewModel.encenderBomba() }) {
                Text("Encender")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { viewModel.apagarBomba() }) {
                Text("Apagar")
            }
        }
    }
}