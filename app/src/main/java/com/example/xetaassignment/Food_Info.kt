package com.example.xetaassignment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xetaassignment.Adapters.SimilarItemAdapter
import com.example.xetaassignment.ApiServices.RetrofitInstanceFoodInfo
import com.example.xetaassignment.databinding.ActivityFoodInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Food_Info : AppCompatActivity() {
    private lateinit var binding: ActivityFoodInfoBinding
    private lateinit var similarItemAdapter: SimilarItemAdapter
    private lateinit var imageList: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        // Enable edge-to-edge display for the activity
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityFoodInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle back button click to finish the activity and return to the previous screen
        binding.FoodInfoHeader.FoodInfoBackBtn.setOnClickListener {
            finish()
        }

        // Fetch data using Retrofit in a coroutine to ensure smooth UI interaction
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstanceFoodInfo.ApiServiceFoodInfo.getRecipe()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val foodData = response.body()
                        // Prepare the image list for the horizontal RecyclerView
                        imageList = arrayOf(R.raw.img1, R.raw.img3, R.raw.img2)
                        // Initialize the adapter with the image list and similar items data
                        similarItemAdapter = SimilarItemAdapter(imageList, foodData?.data?.similar_items!!)
                        // Set up the horizontal RecyclerView with the adapter
                        binding.HorizontalRecyclerView.layoutManager = LinearLayoutManager(this@Food_Info, LinearLayoutManager.HORIZONTAL, true)
                        binding.HorizontalRecyclerView.adapter = similarItemAdapter

                        // Display the first generic fact and the description of the food item
                        binding.FoodInfoFactCard.FoodInfoFactDescriptionTv.text = foodData.data.generic_facts[0]
                        binding.FoodInfoDescriptionText.text = foodData.data.description
                    } else {
                        // Log an error message if the API call is not successful
                        Log.e("ApiError", "Failed to fetch data: ${response.errorBody()}")
                    }
                }
            } catch (e: Exception) {
                // Log any exceptions that occur during the API call
                Log.d("ERRORINFOODINFO", "onViewCreated: $e")
            }
        }
    }
}
