package com.keiapp.birthdaycounter.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.keiapp.birthdaycounter.DialogActionInterface
import com.keiapp.birthdaycounter.R
import com.keiapp.birthdaycounter.dialog.AbstractDialogFragment

class LocalDialogFragment : AbstractDialogFragment<DialogFragment>() {
    private val mLayoutId: Int by lazy { arguments.getInt(ARG_LAYOUT_ID) }

    private var mEventListener: DialogActionInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = activity.applicationContext
        mEventListener = if (context is DialogActionInterface) {
            context
        } else {
            null
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(mLayoutId, container, false)
        val negativeButton = view.findViewById<Button>(R.id.negative_button) as Button
        negativeButton.setOnClickListener { mEventListener?.cancel() }
        val positiveButton = view.findViewById<Button>(R.id.positive_button) as Button
        positiveButton.setOnClickListener { mEventListener?.agreed() }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        mEventListener = null
    }

    companion object {
        private val ARG_LAYOUT_ID = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id layoutId.
         * @return A new instance of fragment DialogFragment.
         */
        fun newInstance(id: Int): LocalDialogFragment {
            val fragment = LocalDialogFragment()
            val args = Bundle()
            args.putInt(ARG_LAYOUT_ID, id)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
