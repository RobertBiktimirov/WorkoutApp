package dev.susu.susuproject.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import dev.susu.susuproject.R
import dev.susu.susuproject.app.theme_app.ThemeEnum
import dev.susu.susuproject.app.theme_app.observeThemeAppProvide
import dev.susu.susuproject.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity :
    AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationVisible {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException()

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeThemeAppProvide(viewModel.themeEnumFlowable) {
            when (it) {
                ThemeEnum.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                ThemeEnum.DAY -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                ThemeEnum.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

            }
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            return@setOnItemSelectedListener when (item.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.daysListFragment)
                    true
                }
                R.id.nav_exercises -> {
                    navController.navigate(R.id.exerciseListFragment)
                    true
                }
                R.id.nav_add -> {
                    navController.navigate(R.id.addFragment)
                    true
                }
                R.id.nav_setting -> {
                    navController.navigate(R.id.settingsFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

    override fun showBottomSheet() {
        binding.bottomNavigation.isVisible = true
    }

    override fun hideBottomSheet() {
        binding.bottomNavigation.isVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}