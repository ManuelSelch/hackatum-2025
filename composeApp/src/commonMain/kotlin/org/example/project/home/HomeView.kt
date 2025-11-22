package org.example.project.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.home.components.*
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.TEXT_REG
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeView(households: List<String>) {
    AppTheme() {
        Surface {
            Column(
                Modifier.fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .safeContentPadding()
            )
            {
                // Household Selector
                HouseDropdown(
                    items = households,
                    label = "Select a House",
                ) {}
                Column(Modifier.fillMaxWidth().wrapContentHeight()) {
                    // Debts
                    Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(32.dp, 16.dp)) {
                        Text(
                            "Your Debts",
                            Modifier.fillMaxWidth(),
                            fontSize = TEXT_REG.sp,
                            textAlign = TextAlign.Center
                        )
                        FlowRow(
                            Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            DebtUser("Manuel", 123.35)
                            DebtUser("Yorick S", 409.56)
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(24.dp), horizontalArrangement = Arrangement.Start
                    )
                    {
                        Column(Modifier.wrapContentHeight()) {
                            Text("Running Low", fontSize = TEXT_REG.sp)

                            FlowRow(
                                Modifier.fillMaxWidth(0.5f),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                ListItem("Banana", 2, "", "food")
                                ListItem("Milk", 100, "ml", "drink")
                                ListItem("Banana", 2, "", "misc")
                                ListItem("Milk", 100, "ml", "drink")
                            }

                            // Items that have minimum value alerts set
                        }
                        Column(Modifier.wrapContentHeight()) {
                            Text("To Buy", fontSize = TEXT_REG.sp)
                            FlowRow(
                                Modifier.fillMaxWidth(0.5f),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                ListItem("Milk", 1, "L", "")
                                ListItem("Cherry", 200, "G", "")
                                ListItem("Milk", 1, "L", "")
                                ListItem("Cherry", 200, "G", "")
                            }

                            // Items that have minimum value alerts set
                        }
                    }
                }
                // Portal
                Column(Modifier.padding(0.dp, 16.dp).fillMaxWidth().wrapContentHeight().align(Alignment.End)) {
                    Row(
                        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp).align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ExpenseWidget()
                        Column(Modifier.padding(0.dp, 0.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            PantryWidget()
                            ShoppingWidget()
                        }


                    }

                }
            }
        }
    }
}

@Composable
@Preview
fun HomePreview() {
    HomeView(
        listOf("a", "b", "c")
    )
}