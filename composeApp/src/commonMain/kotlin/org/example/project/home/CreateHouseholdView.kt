package org.example.project.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.TEXT_L
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CreateHouseholdView(
    createHousehold: (name: String) -> Unit,
    backTapped: () -> Unit
) {
    var name by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Text("Create Household", Modifier.fillMaxWidth().padding(32.dp, 32.dp), fontSize = TEXT_L.sp, textAlign = TextAlign.Start)
                TextField(name, onValueChange = { name = it }, label = { Text("Name") })

                Row (Modifier.fillMaxWidth().padding(32.dp, 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp) ) {
                    Button(onClick = { createHousehold(name) }) {
                        Text("Create")
                    }
                    Button(backTapped) {
                        Text("Back")
                    }
                }
            }
}

@Composable
@Preview
fun CreateHouseholdPreview() {
    CreateHouseholdView({}, {})
}