package com.han.fragmentmanagement.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.han.fragmentmanagement.Activities.FragmentType
import com.han.fragmentmanagement.Activities.MainInterface
import com.han.fragmentmanagement.R
import com.han.fragmentmanagement.Widget.CoAlert

class PermissionFragment : Fragment(),CoAlert.NoticeDialogListener{
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
        v.findViewById<Button>(R.id.tst_btn).setOnClickListener{
           listener?.onMoveFragment(FragmentType.HOME,true)
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


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

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}