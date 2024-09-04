package com.cjra.composenavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.cjra.composenavigation.views.DetailView
import com.cjra.composenavigation.views.HomeView

@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        graph = navController.createAppGraph(navController)
    )
}

fun NavController.createAppGraph(navController: NavController): NavGraph {
    return createGraph(startDestination = "Home") {
        composable("Home") {
            HomeView(navController)
        }
        composable(
            "Detail/{id}/?{optional}", arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("optional") { type = NavType.StringType }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            val optional = it.arguments?.getString("optional") ?: ""
            DetailView(navController, id, optional)
        }
    }
}