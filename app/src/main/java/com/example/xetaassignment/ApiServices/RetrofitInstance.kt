package com.example.xetaassignment.ApiServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceHomePage {

    companion object {
        private val RetrofitBuilderHomePage by lazy {
            Retrofit.Builder()
                .baseUrl("http://52.25.229.242:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val ApiServiceHomePage: ApiInterfaces.ApiInterfaceHomePage by lazy {
            RetrofitBuilderHomePage.create(ApiInterfaces.ApiInterfaceHomePage::class.java)
        }
    }
}

class RetrofitInstanceFoodInfo {

    companion object {
        private val RetrofitBuilderFoodInfo by lazy {
            Retrofit.Builder()
                .baseUrl("http://52.25.229.242:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val ApiServiceFoodInfo: ApiInterfaces.ApiInterfaceFoodInfo by lazy {
            RetrofitBuilderFoodInfo.create(ApiInterfaces.ApiInterfaceFoodInfo::class.java)
        }
    }
}
