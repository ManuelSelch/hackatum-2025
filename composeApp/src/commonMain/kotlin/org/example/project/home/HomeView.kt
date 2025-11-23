package org.example.project.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.GroupDTO
import org.example.project.home.components.*
import org.example.project.theme.TEXT_REG
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeView(
    groups: List<GroupDTO>,
    current: GroupDTO?,
    groupSelected: (GroupDTO) -> Unit,
    createHouseholdTapped: () -> Unit,
    joinTapped: () -> Unit,
    inviteTapped: () -> Unit,
    refreshTapped: () -> Unit,
    pantryTapped: () -> Unit,
) {
    Column(
        Modifier.fillMaxSize()
    )
    {
        // household Selector
        HouseDropdown(
            items = groups,
            label = "Select a House",
            current,
            groupSelected,
            createHouseholdTapped
        )

        // join & invite
        Row {
            Button(onClick = joinTapped) {
                Text("Join")
            }

            Button(onClick = inviteTapped) {
                Text("Invite")
            }

            Button(onClick = refreshTapped) {
                Text("Refresh")
            }
        }
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
                    PantryWidget(pantryTapped)
                    ShoppingWidget()
                }
            }

        }
    }
}

@Composable
@Preview
fun HomePreview() {
    HomeView(
        emptyList(),
        null,
        {},
        {},
        {}, {},
        {},
        {}
    )
}