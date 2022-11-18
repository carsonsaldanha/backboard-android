package com.jrtc.backboard

import android.hardware.SensorManager
import android.os.Bundle
import android.view.OrientationEventListener
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jrtc.backboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var cutoutDepth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edge to edge content and insets
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            cutoutDepth = insets.top
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }
            binding.bottomNavigationView.updatePadding(bottom = insets.bottom)
            WindowInsetsCompat.CONSUMED
        }

        val bottomNavigationView: BottomNavigationView = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        // Hides the bottom navigation bar on unspecified fragments
        navController.addOnDestinationChangedListener { _, navDest: NavDestination, _ ->
            if (navDest.id == R.id.navigation_games || navDest.id == R.id.navigation_tweets
                || navDest.id == R.id.navigation_highlights
            ) {
                bottomNavigationView.visibility = View.VISIBLE
            } else {
                bottomNavigationView.visibility = View.GONE
            }
        }
        bottomNavigationView.setupWithNavController(navController)

        // App shortcuts navigation
        if ("com.jrtc.backboard.TWEETS" == intent.action) {
            navHostFragment.findNavController().navigate(R.id.navigation_tweets)
        } else if ("com.jrtc.backboard.HIGHLIGHTS" == intent.action) {
            navHostFragment.findNavController().navigate(R.id.navigation_highlights)
        }
    }

    /**
     * Listens for orientation change and updates the padding for the display cutout.
     */
    private val orientationListener by lazy {
        object : OrientationEventListener(applicationContext, SensorManager.SENSOR_DELAY_NORMAL) {
            override fun onOrientationChanged(orientation: Int) {
                if (orientation == ORIENTATION_UNKNOWN)
                    return
                when (display?.rotation) {
                    Surface.ROTATION_0 -> {
                        // Bottom - reset the padding in portrait
                        binding.navHostFragmentActivityMain.setPadding(0, 0, 0, 0)
                    }
                    Surface.ROTATION_90 -> {
                        // Left
                        binding.navHostFragmentActivityMain.setPadding(cutoutDepth, 0, 0, 0)
                    }
                    Surface.ROTATION_180 -> {
                        // Top - reset the padding if upside down
                        binding.navHostFragmentActivityMain.setPadding(0, 0, 0, 0)
                    }
                    Surface.ROTATION_270 -> {
                        // Right
                        binding.navHostFragmentActivityMain.setPadding(0, 0, cutoutDepth, 0)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Starts the orientation listener
        if (orientationListener.canDetectOrientation())
            orientationListener.enable()
    }

    override fun onPause() {
        super.onPause()
        // Stops the orientation listener
        orientationListener.disable()
    }

}
