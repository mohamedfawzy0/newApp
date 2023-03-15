package com.app.torch.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.torch.R
import com.app.torch.base.BaseActivity
import com.app.torch.base.BaseFragment
import com.app.torch.databinding.ActivityMainBinding
import com.app.torch.ui.home.HomeFragment
import com.app.torch.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private var homeFragment = HomeFragment()
    private var settingsFragment = SettingsFragment()

    private var activeFragment: BaseFragment = homeFragment

    private lateinit var fragmentManager: FragmentManager

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    displayFragment(homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    displayFragment(settingsFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupFragments()

        bottom_nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun setupFragments() {
        fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction().add(R.id.container, settingsFragment, "2")
            .hide(settingsFragment).commit()

        fragmentManager.beginTransaction().add(R.id.container, homeFragment, "1").commit()
    }

    private fun displayFragment(fragmentToShow: Fragment) {
        supportFragmentManager?.beginTransaction()?.hide(activeFragment)?.show(fragmentToShow)
            ?.commit()
        activeFragment = fragmentToShow as BaseFragment
    }

}
