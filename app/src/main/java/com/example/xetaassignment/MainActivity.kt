package com.example.xetaassignment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.xetaassignment.Fragment.ActivityFragment
import com.example.xetaassignment.Fragment.CameraFragment
import com.example.xetaassignment.Fragment.FeedFragment
import com.example.xetaassignment.Fragment.GoalFragment
import com.example.xetaassignment.Fragment.PersonFragment
import com.example.xetaassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Enable edge-to-edge display for the activity
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initially replace the fragment with ActivityFragment
        replaceFragment(ActivityFragment())

        // Set item selected listener for the bottom navigation view
        binding.navView.setOnItemSelectedListener {
            // Replace the fragment based on the selected item
            when (it.itemId) {
                R.id.activity -> replaceFragment(ActivityFragment())
                R.id.goal -> replaceFragment(GoalFragment())
                R.id.camera -> replaceFragment(CameraFragment())
                R.id.feed -> replaceFragment(FeedFragment())
                R.id.person -> replaceFragment(PersonFragment())
            }
            true
        }
    }

    // Function to replace the current fragment with a new one
    private fun replaceFragment(currFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, currFragment)
        fragmentTransaction.commit()
    }
}
