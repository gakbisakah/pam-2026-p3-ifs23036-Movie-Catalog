package org.delcom.pam_p3_ifs23024.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.delcom.pam_p3_ifs23024.helper.ConstHelper
import org.delcom.pam_p3_ifs23024.ui.components.BottomNavComponent
import org.delcom.pam_p3_ifs23024.ui.screens.HomeScreen
import org.delcom.pam_p3_ifs23024.ui.screens.PlantsDetailScreen
import org.delcom.pam_p3_ifs23024.ui.screens.PlantsScreen
import org.delcom.pam_p3_ifs23024.ui.screens.ProfileScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UIApp(
    navController: NavHostController = rememberNavController(),
) {
    // Mendapatkan route saat ini untuk menentukan apakah bottom nav perlu ditampilkan
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Daftar route yang akan menampilkan bottom navigation
    val routesWithBottomNav = listOf(
        ConstHelper.RouteNames.Home.path,
        ConstHelper.RouteNames.Plants.path,
        ConstHelper.RouteNames.Profile.path
    )

    // Cek apakah route saat ini perlu menampilkan bottom nav
    val shouldShowBottomNav = currentRoute?.let { route ->
        routesWithBottomNav.any { route.contains(it) }
    } ?: true

    Scaffold(
        bottomBar = {
            if (shouldShowBottomNav) {
                BottomNavComponent(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ConstHelper.RouteNames.Home.path,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F8FA))
                .padding(innerPadding)
        ) {
            // Home
            composable(
                route = ConstHelper.RouteNames.Home.path,
            ) { _ ->
                HomeScreen(
                    navController = navController,
                )
            }

            // Profile
            composable(
                route = ConstHelper.RouteNames.Profile.path,
            ) { _ ->
                ProfileScreen(
                    navController = navController,
                )
            }

            // Plants
            composable(
                route = ConstHelper.RouteNames.Plants.path,
            ) { _ ->
                PlantsScreen(
                    navController = navController,
                )
            }

            // Plants Detail
            composable(
                route = ConstHelper.RouteNames.PlantsDetail.path,
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                )
            ) { backStackEntry ->
                val plantName = backStackEntry.arguments?.getString("name") ?: ""

                PlantsDetailScreen(
                    navController = navController,
                    plantName = plantName
                )
            }
        }
    }
}