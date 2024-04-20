package com.example.xetaassignment.ApiServices

import HomePageResponse
import com.example.xetaassignment.DataModel.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

class ApiInterfaces {

    // ApiInterface For HomePage
    interface ApiInterfaceHomePage {

        @GET("homepage_v2/")
        suspend fun getHomePageData(): Response<HomePageResponse>
    }
    // ApiInterface For Food Info
    interface ApiInterfaceFoodInfo {

        @GET("/food_info/")
        suspend fun getRecipe(): Response<RecipeResponse>
    }
}
