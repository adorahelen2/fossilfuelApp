package dcu.fossilfuel.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dcu.fossilfuel.R

@Composable
fun MemoriesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Our Memories", style = MaterialTheme.typography.headlineMedium)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Image(painter = painterResource(id = R.drawable.assemble1), contentDescription = "Assemble1")
            Image(painter = painterResource(id = R.drawable.assemble2), contentDescription = "Assemble2")
            Image(painter = painterResource(id = R.drawable.assemble3), contentDescription = "Assemble3")
        }
    }
}