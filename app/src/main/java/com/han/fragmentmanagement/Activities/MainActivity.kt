package com.han.fragmentmanagement.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.han.fragmentmanagement.Fragments.CouponFragment
import com.han.fragmentmanagement.Fragments.HomeFragment
import com.han.fragmentmanagement.Fragments.PermissionFragment
import com.han.fragmentmanagement.Fragments.SearchFragment
import com.han.fragmentmanagement.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var counts:Int = 0
    private val btmListner = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.bottom_home -> {
                changeFragment(HomeFragment.newInstance(counts),true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_search -> {
                changeFragment(SearchFragment.newInstance(counts),true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_coupon -> {
                changeFragment(CouponFragment.newInstance(counts),true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(PermissionFragment.newInstance(counts),false)

        mainBottomSheet.setOnNavigationItemSelectedListener(btmListner)
    }


    private fun changeFragment(f : Fragment, animate:Boolean){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrame, f)
        if (animate) {
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onBackPressed() {
        val fm: FragmentManager = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            val first: FragmentManager.BackStackEntry = fm.getBackStackEntryAt(0)
            fm.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
        else {
            super.onBackPressed()
        }
    }
}
