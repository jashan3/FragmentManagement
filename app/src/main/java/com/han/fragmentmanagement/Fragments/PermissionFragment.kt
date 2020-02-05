package com.han.fragmentmanagement.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.han.fragmentmanagement.Activities.FragmentType
import com.han.fragmentmanagement.Activities.MainInterface
import com.han.fragmentmanagement.Adapters.PermissionPagerAdaptor
import com.han.fragmentmanagement.R
import com.han.fragmentmanagement.Utils.L
import com.han.fragmentmanagement.Widget.CoAlert

class PermissionFragment : Fragment() {
    var listener: MainInterface? = null

    val shownIndex: Int by lazy {
        arguments?.getInt("index", 0) ?: 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MainInterface
        if (listener == null) {
            throw ClassCastException("$context must implement OnArticleSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_permission,container,false)
        val pager = v.findViewById<ViewPager2>(R.id.myPager)
        pager.adapter = context?.let { PermissionPagerAdaptor(it) }
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL



        pager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                L.d("onPageScrollStateChanged : ${state}")
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

            if (position == 9){
                kotlin.run {
                    Toast.makeText(context,"tda",Toast.LENGTH_SHORT)
                }

            }
                L.d("onPageScrolled : ${position}")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                L.d("onPageSelected : ${position}")
            }
        })

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//
//            LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
//            int totalItemCount = layoutManager.getItemCount();
//            int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
//
//            if (lastVisible >= totalItemCount - 1) {
//                Log.d(TAG, "lastVisibled");
//            }
//        }
//    };


    companion object {
        fun newInstance(index: Int): PermissionFragment {
            val f = PermissionFragment()

            // Supply index input as an argument.
            val args = Bundle()
            args.putInt("index", index)
            f.arguments = args

            return f
        }
    }

}