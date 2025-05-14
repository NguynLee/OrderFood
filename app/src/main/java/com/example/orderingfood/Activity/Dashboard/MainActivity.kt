package com.example.orderingfood.Activity.Dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.orderingfood.Activity.BaseActivity
import com.example.orderingfood.Domain.BannerModel
import com.example.orderingfood.Domain.CategoryModel
import com.example.orderingfood.ViewModel.MainViewModel
import com.example.orderingfood.ui.theme.OrderingFoodTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()

    val viewModel = MainViewModel()

    val banners = remember { mutableStateListOf<BannerModel>() }

    val categories = remember { mutableStateListOf<CategoryModel>() }

    var showBannerLoading by remember { mutableStateOf(true) }

    var showCategoryLoading by remember { mutableStateOf(true) }



    LaunchedEffect(Unit) {
        viewModel.loadBanner().observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

    Scaffold(bottomBar = { MyBottomBar() }
    , scaffoldState = scaffoldState) {
        paddingValues ->
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues=paddingValues)
        ) {

            item {
                TopBar()
            }
            item {
                Banner(banners, showBannerLoading)
            }
            item {
                Search()
            }
            item {
                CategorySection(categories, showCategoryLoading)
            }

        }
    }
}

