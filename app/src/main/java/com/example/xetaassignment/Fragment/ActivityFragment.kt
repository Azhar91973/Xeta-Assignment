package com.example.xetaassignment.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xetaassignment.Adapters.GoalCardAdapter
import com.example.xetaassignment.ApiServices.RetrofitInstanceHomePage.Companion.ApiServiceHomePage
import com.example.xetaassignment.Food_Info
import com.example.xetaassignment.databinding.FragmentActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityFragment : Fragment() {

    private lateinit var binding: FragmentActivityBinding
    private lateinit var goalCardAdapter: GoalCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set a click listener for a button
        binding.exploreCard2.HomePageToFoodInfo.setOnClickListener {
            // Start a new activity
            startActivity(Intent(requireContext(), Food_Info::class.java))
        }

        // Fetch data from the API using a coroutine
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Make the API request
                val response = ApiServiceHomePage.getHomePageData()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Parse the response data
                        val homePageResponse = response.body()
                        // Initialize and set up the adapter
                        goalCardAdapter = GoalCardAdapter(homePageResponse?.data?.section_1!!)
                        binding.goalCardRecyclerview.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = goalCardAdapter
                        }
                    } else {
                        // Show a toast message if the request fails
                        Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                // Log and show an error message if an exception occurs
                Log.e("ERROR", "Failed to fetch data", e)
            }
        }
    }
}
