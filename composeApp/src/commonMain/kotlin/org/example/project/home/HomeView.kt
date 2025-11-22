package org.example.project.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.home.components.HouseDropdown
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.AppTheme
import org.example.project.theme.TEXT_L
import org.example.project.theme.TEXT_M
import org.example.project.theme.icons.Grocery
import org.example.project.theme.icons.Money
import org.example.project.theme.icons.Receipt_long
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeView(
    households: List<String>,
    createHouseholdTapped: () -> Unit,
) {
    AppTheme(darkTheme = false) {
        Column(Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .safeContentPadding())
        {
                    // Household Selector
                    HouseDropdown(
                        items = households,
                        label = "Select a House",
                        {} ,
                        createHouseholdTapped,
                    )

                    // Dashboard Summary
                    // Monthly In/Out
                    // Running Low?
                    Column(Modifier.fillMaxWidth().wrapContentHeight()) {
                        // Debts
                        Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(0.dp, 32.dp)) {
                            Text("Your debts", Modifier.fillMaxWidth(), fontSize = TEXT_L.sp, textAlign = TextAlign.Center)
                            Column{

                            }
                        }
                        Row(Modifier.fillMaxWidth().wrapContentHeight().padding(0.dp, 32.dp)){
                            Column(Modifier.wrapContentHeight()) {
                                Text("Running low on...", color = MaterialTheme.colorScheme.onBackground, fontSize = TEXT_L.sp)

                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)){
                                }

                                // Items that have minimum value alerts set
                            }
                            Column(Modifier.wrapContentHeight()) {
                                Text("To Buy", fontSize = TEXT_L.sp)

                                Text("- Eggs", color = MaterialTheme.colorScheme.onBackground, fontSize = TEXT_M.sp)
                                Text("- Ground Beef", color = MaterialTheme.colorScheme.onBackground, fontSize = TEXT_M.sp)
                                Text("- Bread",color = MaterialTheme.colorScheme.onBackground,  fontSize = TEXT_M.sp)
                                Text("- Butter", color = MaterialTheme.colorScheme.onBackground,  fontSize = TEXT_M.sp)
                                // Items that have minimum value alerts set
                            }
                        }
                        // Running Low On
                    }
                    // Portal
                    Column(Modifier.padding(0.dp, 16.dp).fillMaxWidth().wrapContentHeight()) {
                        Row(
                            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp).align(Alignment.CenterHorizontally),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(AppTheme.brushes.gradient, shape = RoundedCornerShape(36.dp))
                                    .size(196.dp)
                                    .border(
                                        2.dp,
                                        color = MaterialTheme.colorScheme.outline,
                                        shape = RoundedCornerShape(36.dp)
                                    )
                            ) {
                                Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.Center) {
                                    Row(Modifier.fillMaxWidth()) {
                                        Icon(
                                            Money,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.tertiary,
                                            modifier = Modifier.size(32.dp)
                                        )
                                        Icon(
                                            Money,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.tertiary,
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                    Text(
                                        "Expense Manager",
                                        textAlign = TextAlign.Start,
                                        fontSize = TEXT_M.sp,
                                        color = MaterialTheme.colorScheme.tertiary,
                                        modifier = Modifier.padding(0.dp, 16.dp)
                                    )
                                    Box(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                                        Column(Modifier.padding(4.dp), verticalArrangement = Arrangement.Center) {
                                            Text(
                                                "November",
                                                fontSize = TEXT_M.sp,
                                                color = MaterialTheme.colorScheme.onBackground
                                            )
                                            Text("$-392", color = MaterialTheme.colorScheme.error, fontSize = TEXT_M.sp)
                                        }

                                    }
                                }


                            }
                            Column(Modifier.padding(0.dp, 0.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                                Box(
                                    modifier = Modifier
                                        .background(AppTheme.brushes.gradient, shape = RoundedCornerShape(24.dp))
                                        .width(160.dp)
                                        .height(88.dp)
                                        .border(
                                            2.dp,
                                            color = MaterialTheme.colorScheme.outline,
                                            shape = RoundedCornerShape(24.dp)
                                        )
                                ) {
                                    Row(
                                        Modifier.fillMaxWidth().fillMaxHeight().padding(4.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            "Pantry",
                                            color = MaterialTheme.colorScheme.secondary,
                                            fontSize = TEXT_M.sp
                                        )
                                        Icon(
                                            Grocery,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.secondary,
                                            modifier = Modifier.size(32.dp)
                                        )

                                    }

                                }
                                Box(
                                    modifier = Modifier
                                        .background(AppTheme.brushes.gradient, shape = RoundedCornerShape(24.dp))
                                        .width(160.dp)
                                        .height(88.dp)
                                        .border(
                                            2.dp,
                                            color = MaterialTheme.colorScheme.outline,
                                            shape = RoundedCornerShape(24.dp)
                                        )
                                ) {
                                    Row(
                                        Modifier.fillMaxWidth().fillMaxHeight().padding(4.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            "Shopping List",
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = TEXT_M.sp
                                        )
                                        Icon(
                                            Receipt_long,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(32.dp)
                                        )

                                    }

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
        listOf("a", "b", "c"),
        {}
    )
}