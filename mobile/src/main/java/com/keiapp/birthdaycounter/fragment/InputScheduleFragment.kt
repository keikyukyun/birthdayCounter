package com.keiapp.birthdaycounter.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.gms.plus.PlusOneButton
import com.keiapp.birthdaycounter.IActionEventListener
import com.keiapp.birthdaycounter.R
import com.keiapp.birthdaycounter.dialog.DialogUtils

/**
 * create an instance of this fragment.
 */
class InputScheduleFragment : Fragment() {
    // Plusonebuttonは使用しないが、よく知らないコードなのであとで勉強するために残しておく。
    // The URL to +1.  Must be a valid URL.
    private val PLUS_ONE_URL = "http://developer.android.com"
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mPlusOneButton: PlusOneButton? = null

    private var mEventListener: IActionEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_input_schedule, container, false)

        //Find the +1 button
        view?:return null

//        mPlusOneButton = view.findViewById<View>(R.id.plus_one_button) as PlusOneButton

        val edit: EditText = view.findViewById<View>(R.id.schedule_edit_text) as EditText
        edit.setOnClickListener({
            DialogUtils.showDatePickerDialog(activity, R.layout.fragment_dialog, "dialog_fragment")
        })


        return view
    }

    override fun onResume() {
        super.onResume()

        // Refresh the state of the +1 button each time the activity receives focus.
        mPlusOneButton?.let {
            it.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IActionEventListener) {
            mEventListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mEventListener = null
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        // The request code must be 0 or greater.
        private val PLUS_ONE_REQUEST_CODE = 0
    }

}// Required empty public constructor
