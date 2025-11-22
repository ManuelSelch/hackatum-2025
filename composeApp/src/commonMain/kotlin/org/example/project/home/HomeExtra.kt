package org.example.project.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.AppTheme
import org.example.project.theme.components.HouseDropdown

@Composable
fun HomeExtra(){
    AppTheme {
        Column(Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(16.dp, 16.dp)

            )
            {
                // Household Selector
                HouseDropdown(
                    items = listOf("House of Awesome", "Boring House"),
                    label = "Select a House",
                ){

                }

                // Dashboard/Summary

                // Portal
                Column(Modifier.align(Alignment.BottomCenter).padding(0.dp, 32.dp)) {
                    Row(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp).align(Alignment.CenterHorizontally)) {
                        Box(modifier = Modifier
                            .background(AppTheme.brushes.primaryBackground, shape = RoundedCornerShape(36.dp))
                            .size(196.dp)
                            .border(3.dp,
                                color=MaterialTheme.colorScheme.onBackground,
                                shape= RoundedCornerShape(36.dp))
                        ){
                            Text("Expense Manager", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp))
                        }
                        Column(Modifier.padding(16.dp, 0.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                            Box(modifier = Modifier
                                .background(AppTheme.brushes.primaryBackground, shape = RoundedCornerShape(24.dp))
                                .width(160.dp)
                                .height(88.dp)
                                .border(3.dp,
                                    color=MaterialTheme.colorScheme.onBackground.copy(0.7f),
                                    shape= RoundedCornerShape(24.dp))
                            ){
                                Text("Shopping List", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp))
                            }
                            Box(modifier = Modifier
                                .background(AppTheme.brushes.primaryBackground, shape = RoundedCornerShape(24.dp))
                                .width(160.dp)
                                .height(88.dp)
                                .border(3.dp,
                                    color=MaterialTheme.colorScheme.onBackground.copy(0.7f),
                                    shape= RoundedCornerShape(24.dp))
                            ){
                                Text("Pantry", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp))
                            }
                        }



                    }

                }
            }

        }
    }


}