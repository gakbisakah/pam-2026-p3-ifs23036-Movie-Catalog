package org.delcom.pam_p3_ifs23024.helper

import androidx.navigation.NavHostController

object RouteHelper {
    fun to(
        navController: NavHostController,
        destination: String,
        removeBackStack: Boolean = false
    ) {
        if (removeBackStack) {
            navController.navigate(destination) {
                launchSingleTop = true
                popUpTo(0) { inclusive = true } // hapus semua stack
            }
        } else {
            navController.navigate(destination) {
                launchSingleTop = true
            }
        }
    }

    fun back(
        navController: NavHostController,
    ) {
        navController.popBackStack()
    }
}