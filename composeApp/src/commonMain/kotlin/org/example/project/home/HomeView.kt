package org.example.project.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import org.example.project.theme.icons.Refresh
import org.example.project.theme.icons.SettingsGear
import org.example.project.theme.icons.add_household
import org.example.project.theme.icons.invite_household
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun HomeView(
    groups: List<GroupDTO>,
    current: GroupDTO?,
    groupSelected: (GroupDTO) -> Unit,
    createHouseholdTapped: () -> Unit,
    joinTapped: () -> Unit,
    inviteTapped: () -> Unit,
    refreshTapped: () -> Unit,
    pantryTapped: () -> Unit,
    settingsTapped: () -> Unit
)
{
    Column(
        Modifier.fillMaxSize()
    )
    {
        Row {
            // household Selector
            HouseDropdown(
                items = groups,
                label = "Select a House",
                current,
                groupSelected,
                createHouseholdTapped
            )

            Button(settingsTapped) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(SettingsGear, contentDescription = "Settings", modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
        // join & invite
        Row(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = joinTapped) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text("Join")
                    Icon(add_household,
                        contentDescription = "Join",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }


            }

            Button(onClick = inviteTapped) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text("Invite")
                    Icon(invite_household, contentDescription = "Invite",  modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.onPrimary)
                }
            }

            Button(onClick = refreshTapped) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Refresh")
                    Icon(Refresh, contentDescription = "Refresh", modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.onPrimary)
                }
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
                        // TODO Implement Filtered View
                    }
                }
                Column(Modifier.wrapContentHeight()) {
                    Text("To Buy", fontSize = TEXT_REG.sp)
                    FlowRow(
                        Modifier.fillMaxWidth(0.5f),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                    }
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
