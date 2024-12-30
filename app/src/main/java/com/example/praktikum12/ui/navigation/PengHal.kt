package com.example.praktikum12.ui.navigation



import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.praktikum12.ui.view.DestinasiDetail
import com.example.praktikum12.ui.view.DestinasiEntry
import com.example.praktikum12.ui.view.DestinasiHome
import com.example.praktikum12.ui.view.DestinasiUpdate
import com.example.praktikum12.ui.view.DetailView
import com.example.praktikum12.ui.view.EntryMhsScreen
import com.example.praktikum12.ui.view.HomeScreen
import com.example.praktikum12.ui.view.UpdateView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(route = DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                }
            )
        }
        composable(route = DestinasiEntry.route){
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetail.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM){
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let {
                DetailView(
                    navigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToEdit =  {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    }
                )
            }
        }
        composable(
            DestinasiUpdate.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.NIM){
                    type = NavType.StringType
                }
            )
        ){
            val nim = it.arguments?.getString(DestinasiUpdate.NIM)
            nim?.let {
                UpdateView(
                    navigateBack = {
                        navController.navigate("${DestinasiDetail.route}/$it") {
                            popUpTo("${DestinasiDetail.route}/$it") {
                                inclusive = true
                            }
                        }
                    },
                )
            }
        }
    }
}