package com.han.fragmentmanagement.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.han.fragmentmanagement.R
import com.han.fragmentmanagement.Utils.L
import java.util.*


class PermissionPagerAdaptor : Adapter<PermissionPagerAdaptor.PermissionPagerHolder> {
     lateinit var mcontext:Context

    constructor(mcontext: Context) : super() {
        this.mcontext = mcontext
    }

    inner class PermissionPagerHolder : RecyclerView.ViewHolder {
        lateinit var permission_pager:ImageView
        constructor(itemView: View) :super(itemView) {
            permission_pager = itemView.findViewById(R.id.permission_pager)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionPagerHolder {
        val myview = LayoutInflater.from(mcontext).inflate(R.layout.pager_permission,parent,false)
        return PermissionPagerHolder(myview)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: PermissionPagerHolder, position: Int) {
        holder.permission_pager.setBackgroundColor(randomColor())

        if (position==9){
            Toast.makeText(mcontext,"last!",Toast.LENGTH_SHORT)
        }
    }

    fun randomColor():Int{
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}