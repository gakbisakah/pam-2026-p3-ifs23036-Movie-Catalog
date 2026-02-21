package org.delcom.pam_p3_ifs23024.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.delcom.pam_p3_ifs23024.data.DummyData
import org.delcom.pam_p3_ifs23024.data.PlantData
import org.delcom.pam_p3_ifs23024.helper.RouteHelper
import org.delcom.pam_p3_ifs23024.ui.components.BottomNavComponent
import org.delcom.pam_p3_ifs23024.ui.components.LoadingUI
import org.delcom.pam_p3_ifs23024.ui.components.TopAppBarComponent
import org.delcom.pam_p3_ifs23024.ui.theme.DelcomTheme

@Composable
fun PlantsDetailScreen(
    navController: NavHostController,
    plantName: String
) {
    // Muat data
    var plant by remember { mutableStateOf<PlantData?>(null) }

    LaunchedEffect(Unit) {
        plant = DummyData.getPlantsData().find { it.nama == plantName }
        if (plant == null) {
            RouteHelper.back(navController)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (plant != null) {
            // Top App Bar
            TopAppBarComponent(
                navController = navController,
                title = plantName,
                showBackButton = true
            )
            // Content
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                PlantsDetailUI(
                    plant = plant!!
                )
            }
            // Bottom Nav
            BottomNavComponent(navController = navController)
        } else {
            LoadingUI()
        }
    }
}

@Composable
fun PlantsDetailUI(
    plant: PlantData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Gambar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = plant.gambar),
                contentDescription = plant.nama,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = plant.nama,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Deskripsi
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Deskripsi",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
                Text(
                    text = plant.deskripsi,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

        }

        // Manfaat
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Manfaat",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
                Text(
                    text = plant.manfaat,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

        }

        // Efek Samping
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Efek Samping",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
                Text(
                    text = plant.efekSamping,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true, name = "Plants Detail UI Light Mode")
@Composable
fun PreviewPlantsDetailUI() {
    DelcomTheme {
        PlantsDetailUI(
            plant = DummyData.getPlantsData()[0]
        )
    }
}

@Preview(showBackground = true, name = "Plants Detail Screen Light")
@Composable
fun PreviewPlantsDetailScreen() {
    DelcomTheme {
        PlantsDetailScreen(
            navController = NavHostController(LocalContext.current),
            plantName = "Lidah Buaya"
        )
    }
}