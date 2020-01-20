package com.han.fragmentmanagement.Activities


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.han.fragmentmanagement.Fragments.*
import com.han.fragmentmanagement.R
import com.han.fragmentmanagement.Utils.ETCUtil
import com.han.fragmentmanagement.Utils.L
import kotlinx.android.synthetic.main.activity_main.*



// Container Activity must implement this interface
enum class FragmentType{
    PERMISSION, COUPON,HOME,LOGIN,SEARCH,SIGNUP
}
interface MainInterface {
    fun onMainInterface(str:String)
    fun onMoveFragment(type: FragmentType ,animate:Boolean = true)
}

class MainActivity : AppCompatActivity() ,MainInterface {

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

        onMoveFragment(FragmentType.PERMISSION,false)
        mainBottomSheet.setOnNavigationItemSelectedListener(btmListner)


      var lst:List<String> = ETCUtil.getApplicationSignature(this)
      for (st:String in lst){
          L.i(st)
      }
    }



    /**
     * @Type: MainInterface
     */
    override fun onMoveFragment(type: FragmentType, animate: Boolean) {
        when (type) {
            FragmentType.HOME -> changeFragment(HomeFragment.newInstance(index = counts),animate)
            FragmentType.COUPON -> changeFragment(CouponFragment.newInstance(index = counts),animate)
            FragmentType.LOGIN -> changeFragment(LoginFragment.newInstance(index = counts),animate)
            FragmentType.SEARCH -> changeFragment(SearchFragment.newInstance(index = counts),animate)
            FragmentType.SIGNUP -> changeFragment(SignupFragment.newInstance(index = counts),animate)
            FragmentType.PERMISSION -> changeFragment(PermissionFragment.newInstance(index = counts),animate)
        }
    }
    private fun changeFragment(f : Fragment, animate:Boolean){
        val fm = supportFragmentManager
        val t = fm.beginTransaction()
        if (animate) {
            t.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            t.addToBackStack(null)
        }

        t.replace(R.id.mainFrame, f,f::class.simpleName)
        t.commit()
        counts++
    }

    /**
     * @Type: MainInterface
     */
    override fun onMainInterface(str: String) {
        Toast.makeText(this, "counts : $counts , fragment : $str",Toast.LENGTH_LONG).show()
    }

    /**
     * @Type : activity
     */
    override fun onBackPressed() {
        val fm: FragmentManager = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            var index :Int= fm.backStackEntryCount-1
            val first: FragmentManager.BackStackEntry = fm.getBackStackEntryAt(index)
            fm.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        else {
            super.onBackPressed()
        }
    }

    private fun getCallerFragment() {
        val fm: FragmentManager = supportFragmentManager
        val count = fm.backStackEntryCount
        L.i( fm.getBackStackEntryAt(count - 1).name)
    }
}