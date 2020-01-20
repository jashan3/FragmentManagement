package com.han.fragmentmanagement.Utils

import android.util.Log

class L {

    companion object{
        private val PREFIX:String = "## "
        private val TAG:String = "custom log"

        fun i(msg: String?){
            Log.i(TAG,PREFIX+msg)
        }

        fun d(msg:String?){
            Log.d(TAG,PREFIX+msg)
        }

        fun e(msg:String?){
            Log.e(TAG,PREFIX+msg)
        }

        fun v(msg:String?){
            Log.v(TAG,PREFIX+msg)
        }

        fun w(msg:String?){
            Log.w(TAG,PREFIX+msg)
        }

    }


}