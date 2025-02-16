package milori.junis.weather.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import milori.junis.weather.R
import milori.junis.weather.data.model.unit.Unit

@Composable
fun SettingsScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val radioOptions = listOf(Unit(1, R.string.metric), Unit(2, R.string.imperial))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(Modifier.fillMaxSize()) {
        IconButton(
            onClick = { navController.navigateUp() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_back),
                contentDescription = stringResource(
                    id = R.string.navigate_back
                )
            )
        }
        Text(
            text = stringResource(id = R.string.select_unit_of_measurement),
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(start = 16.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .selectableGroup()
        ) {
            radioOptions.forEach { radio ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (radio == selectedOption),
                            onClick = {
                                onOptionSelected(radio)
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (radio == selectedOption),
                        onClick = null // null recommended for accessibility with screen readers
                    )
                    Text(
                        text = stringResource(id = radio.name),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}