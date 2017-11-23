package com.keiapp.birthdaycounter.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

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

        // Builderパターンでのダイアログの使い方
        class Builder<A, F> where A : AppCompatActivity,
                                  A : DialogActionInterface,
                                  F : Fragment,
                                  F : com.keiapp.birthdaycounter.dialog.DialogActionInterface {
            private var fieldActivity: AppCompatActivity?
            private var fieldFragment: Fragment?
            private var fieldTitle: String? = null
            private var fieldMessage: String? = null
            private var fieldItems: Array<String?>? = null
            private var fieldPositiveButtonLabel: String? = null
            private var fieldNegativeButtonLabel: String? = null
            private var fieldRequestCode: Int = -1
            private var fieldParams: Bundle? = null
            private var fieldTags: String? = "default"
            private var fieldCancelable = true

            constructor(activity: A) {
                fieldActivity = activity
                fieldFragment = null
            }

            constructor(fragment: F) {
                fieldFragment = fragment
                fieldActivity = null
            }

            private fun getContext(): Context? {
                fieldActivity?.let { return it.applicationContext }
                fieldFragment?.let {
                    if (fieldFragment is Fragment) {
                        return it.activity.applicationContext
                    }
                }
                return null
            }

            fun title(text: String): Builder<A, F> {
                fieldTitle = text
                return this@Builder
            }

            fun title(@StringRes id: Int): Builder<A, F> {
                getContext()?.getString(id)?.let {
                    return title(it)
                }
                return this@Builder
            }

            fun message(text: String): Builder<A, F> {
                fieldMessage = text
                return this@Builder
            }

            fun message(@StringRes id: Int): Builder<A, F> {
                getContext()?.getString(id)?.let {
                    return message(it)
                }
                return this@Builder
            }

            fun items(vararg items: String?) {
                fieldItems = arrayOf(*items)
            }

            fun positive(text: String): Builder<A, F> {
                fieldPositiveButtonLabel = text
                return this@Builder
            }

            fun positive(@StringRes id: Int): Builder<A, F> {
                getContext()?.getString(id)?.let {
                    return positive(it)
                }
                return this@Builder
            }

            fun negative(text: String): Builder<A, F> {
                fieldNegativeButtonLabel = text
                return this@Builder
            }

            fun negative(@StringRes id: Int): Builder<A, F> {
                getContext()?.getString(id)?.let {
                    return negative(it)
                }
                return this@Builder
            }

            fun requestCode(requestCode: Int): Builder<A, F> {
                fieldRequestCode = requestCode
                return this@Builder
            }

            fun tag(tag: String): Builder<A, F> {
                fieldTags = tag
                return this@Builder
            }

            fun params(param: Bundle): Builder<A, F> {
                fieldParams = param
                return this@Builder
            }

            fun cancelable(cancel: Boolean): Builder<A, F> {
                fieldCancelable = cancel
                return this@Builder
            }

            fun show() {
                LocalDialogFragment().also { f ->
                    Bundle().apply {
                        putString("title", fieldTitle)
                        putString("message", fieldMessage)
                        putStringArray("items", fieldItems)
                        putString("positive_label", fieldPositiveButtonLabel)
                        putString("negative_label", fieldNegativeButtonLabel)
                        putBoolean("cancelable", fieldCancelable)

                        fieldParams?.let {
                            putBundle("params", it)
                        }
                        fieldFragment?.let {
                            f.setTargetFragment(it, fieldRequestCode)
                            f.show(it.childFragmentManager, fieldTags)
                        }?.run {
                            putInt("request_code", fieldRequestCode)
                            f.arguments = this@apply
                            f.show(fieldActivity?.supportFragmentManager, fieldTags)
                        }
                    }
                }
            }
        }
    }
}// Required empty public constructor
