package com.keiapp.birthdaycounter.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.keiapp.birthdaycounter.DialogActionInterface
import com.keiapp.birthdaycounter.dialog.AbstractDialogFragment

class LocalDialogFragment : AbstractDialogFragment<DialogFragment>(), DialogInterface.OnClickListener {
    private val mLayoutId: Int by lazy { arguments.getInt(ARG_LAYOUT_ID) }

    private val mEventListener: DialogActionInterface? by lazy {
        arguments.getSerializable(ARG_LISTENER_ID) as DialogActionInterface
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> mEventListener?.agreed()
            DialogInterface.BUTTON_NEGATIVE -> mEventListener?.cancel()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
                .setPositiveButton("OK", this)
                .setNegativeButton("キャンセル", this)
                .setView(mLayoutId)
        return dialogBuilder.create()
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        private val ARG_LAYOUT_ID = "param1"
        private val ARG_LISTENER_ID = "litener"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id layoutId.
         * @return A new instance of fragment DialogFragment.
         */
        fun newInstance(listener: DialogActionInterface, id: Int): LocalDialogFragment {
            val fragment = LocalDialogFragment()
            val args = Bundle().also { bundle ->
                bundle.putInt(ARG_LAYOUT_ID, id)
                bundle.putSerializable(ARG_LISTENER_ID, listener)
            }
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
