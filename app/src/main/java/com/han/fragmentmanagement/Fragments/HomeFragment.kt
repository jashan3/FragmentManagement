package com.han.fragmentmanagement.Fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.han.fragmentmanagement.R

class HomeFragment : Fragment(){
    val shownIndex: Int by lazy {
        arguments?.getInt("index", 0) ?: 0
    }
    lateinit var v :ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home,container,false) as ViewGroup

        val url = "https://i.pinimg.com/236x/6b/5a/8c/6b5a8cc63ce660cd4dd0bc7752f31a98--phone-wallpapers-ryan-kakao.jpg"
        ImageLoadingTask().execute(url)

        prgress()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun prgress(){
        val layout = RelativeLayout(context)
        val progressBar = ProgressBar(
            context,
            null,
            android.R.attr.progressBarStyleLarge
        )
        progressBar.isIndeterminate = true
        progressBar.visibility = View.VISIBLE
        val params = RelativeLayout.LayoutParams(100, 100)
        params.addRule(RelativeLayout.CENTER_HORIZONTAL)
        params.addRule(RelativeLayout.CENTER_VERTICAL)
        layout.addView(progressBar, params)
        v.addView(layout)
    }


    companion object {
        fun newInstance(index: Int): HomeFragment {
            val f = HomeFragment()

            // Supply index input as an argument.
            val args = Bundle()
            args.putInt("index", index)
            f.arguments = args

            return f
        }
    }
    inner class ImageLoadingTask : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.i(TAG,"## onPreExecute")
        }


        override fun doInBackground(vararg p0: String?): String {
           Log.i(TAG,"## doInBackground : "+ p0[0])
            return ""
        }


        override fun onProgressUpdate(vararg values: Void?) {
            Log.i(TAG, "## onProgressUpdate : $values")
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.i(TAG,"## onPostExecute")
        }
    }
}