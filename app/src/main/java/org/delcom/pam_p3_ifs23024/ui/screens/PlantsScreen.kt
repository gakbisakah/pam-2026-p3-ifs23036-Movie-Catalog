package org.delcom.pam_p3_ifs23024.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.delcom.pam_p3_ifs23024.data.DummyData
import org.delcom.pam_p3_ifs23024.data.PlantData
import org.delcom.pam_p3_ifs23024.helper.ConstHelper
import org.delcom.pam_p3_ifs23024.helper.RouteHelper
import org.delcom.pam_p3_ifs23024.ui.components.TopAppBarComponent
import org.delcom.pam_p3_ifs23024.ui.theme.DelcomTheme

@Composable
fun PlantsScreen(
    navController: NavHostController,
) {
    // Muat data
    var plants by remember { mutableStateOf(DummyData.getPlantsData()) }
    var searchQuery by remember { mutableStateOf("") }

    fun onOpen(plantName: String) {
        RouteHelper.to(
            navController = navController,
            destination = ConstHelper.RouteNames.PlantsDetail.path.replace("{name}", plantName)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBarComponent(
            navController = navController,
            title = "Plants",
            showBackButton = false,
            withSearch = true,
            searchQuery = searchQuery,
            onSearchQueryChange = { query ->
                searchQuery = query
                plants = if (query.isBlank()) {
                    DummyData.getPlantsData()
                } else {
                    DummyData.getPlantsData().filter {
                        it.nama
                            .lowercase()
                            .contains(query.lowercase())
                    }
                }
            }
        )

        // Content
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            PlantsUI(
                plants = plants,
                onOpen = ::onOpen
            )
        }
        // BOTTOM NAV COMPONENT TELAH DIHAPUS DARI SINI!
        // Bottom navigation sekarang hanya dari UIApp.kt
    }
}

@Composable
fun PlantsUI(
    plants: List<PlantData>,
    onOpen: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(plants) { plant ->
            PlantItemUI(
                plant = plant,
                onOpen = onOpen
            )
        }
    }

    if(plants.isEmpty()){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Text(
                text = "Tidak ada data!",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun PlantItemUI(
    plant: PlantData,
    onOpen: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onOpen(plant.nama)
            },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = plant.gambar),
                contentDescription = plant.nama,
                modifier = Modifier
                    .size(70.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = plant.nama,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = plant.deskripsi,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Plants UI Light Mode")
@Composable
fun PreviewPlantsUI() {
    DelcomTheme {
        PlantsUI(
            plants = DummyData.getPlantsData(),
            onOpen = {}
        )
    }
}

@Preview(showBackground = true, name = "Plants Screen Light")
@Composable
fun PreviewPlantsScreen() {
    DelcomTheme {
        PlantsScreen(
            navController = NavHostController(LocalContext.current)
        )
    }
}