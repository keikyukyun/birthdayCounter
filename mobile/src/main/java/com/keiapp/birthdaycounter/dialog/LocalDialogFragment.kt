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
            DialogInterface.BUTTON_POSITIVE -> mEventListener?.agreed(getRequestCode(), which, arguments.getBundle(ARG_PARAMS))
            DialogInterface.BUTTON_NEGATIVE -> mEventListener?.cancel(getRequestCode(), arguments.getBundle(ARG_PARAMS))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
//                .setPositiveButton("OK", this)
//                .setNegativeButton("キャンセル", this)
//                .setView(mLayoutId)
//        return dialogBuilder.create()

        var title: String? = null
        var message: String? = null
        var items: Array<String>? = null
        var positiveButtonLabel: String? = null
        var negativeButtonLabel: String? = null

        arguments.apply {
            title = getString(ARG_TITLE)
            message = getString(ARG_MESSAGE)
            items = getStringArray(ARG_ITEMS)
            positiveButtonLabel = getString(ARG_POSITIVE_BUTTON_LABEL)
            negativeButtonLabel = getString(ARG_NEGATIVE_BUTTON_LABEL)
            isCancelable = getBoolean(ARG_CANCELABLE)
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity).apply {
            title?.let {
                setTitle(title)
            }

            message?.let { setMessage(it) }
            items?.let {
                if (it.isNotEmpty()) {
                    setItems(items, this@LocalDialogFragment)
                }
            }
            positiveButtonLabel?.let {
                setPositiveButton(it, this@LocalDialogFragment)
            }
            negativeButtonLabel?.let {
                setNegativeButton(it, this@LocalDialogFragment)
            }
        }

        return builder.create()
    }

    override fun onCancel(dialog: DialogInterface?) {
        mEventListener?.let {
            it.cancel(getRequestCode(), arguments.getBundle(ARG_PARAMS))
        }
    }

    private fun getRequestCode(): Int {
        arguments.also { args ->
            if (args.containsKey(ARG_REQUEST_CODE)) {
                return args.getInt(ARG_REQUEST_CODE)
            }
        }
        return targetRequestCode
    }

    companion object {
        private val ARG_LAYOUT_ID = "param1"
        private val ARG_LISTENER_ID = "litener"

        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"
        private const val ARG_ITEMS = "items"
        private const val ARG_POSITIVE_BUTTON_LABEL = "positive_button_label"
        private const val ARG_NEGATIVE_BUTTON_LABEL = "negative_button_label"
        private const val ARG_REQUEST_CODE = "request_code"
        private const val ARG_PARAMS = "params"
        private const val ARG_CANCELABLE = "cancelable"


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
        class Builder {
            private var fieldActivity: AppCompatActivity? = null
            private var fieldFragment: Fragment? = null
            private var fieldTitle: String? = null
            private var fieldMessage: String? = null
            private var fieldItems: Array<String?>? = null
            private var fieldPositiveButtonLabel: String? = null
            private var fieldNegativeButtonLabel: String? = null
            private var fieldRequestCode: Int = -1
            private var fieldParams: Bundle? = null
            private var fieldTags: String? = "default"
            private var fieldCancelable = true

            fun <A> newInstance(activity: A): Builder where A : AppCompatActivity,
                                                            A : DialogActionInterface {
                fieldActivity = activity
                fieldFragment = null
                return this@Builder
            }

            fun <F> newInstance(fragment: F): Builder where F : Fragment,
                                                            F : DialogActionInterface {
                fieldActivity = null
                fieldFragment = fragment
                return this@Builder
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

            fun title(text: String): Builder {
                fieldTitle = text
                return this@Builder
            }

            fun title(@StringRes id: Int): Builder {
                getContext()?.getString(id)?.let {
                    return title(it)
                }
                return this@Builder
            }

            fun message(text: String): Builder {
                fieldMessage = text
                return this@Builder
            }

            fun message(@StringRes id: Int): Builder {
                getContext()?.getString(id)?.let {
                    return message(it)
                }
                return this@Builder
            }

            fun items(vararg items: String?) {
                fieldItems = arrayOf(*items)
            }

            fun positive(text: String): Builder {
                fieldPositiveButtonLabel = text
                return this@Builder
            }

            fun positive(@StringRes id: Int): Builder {
                getContext()?.getString(id)?.let {
                    return positive(it)
                }
                return this@Builder
            }

            fun negative(text: String): Builder {
                fieldNegativeButtonLabel = text
                return this@Builder
            }

            fun negative(@StringRes id: Int): Builder {
                getContext()?.getString(id)?.let {
                    return negative(it)
                }
                return this@Builder
            }

            fun requestCode(requestCode: Int): Builder {
                fieldRequestCode = requestCode
                return this@Builder
            }

            fun tag(tag: String): Builder {
                fieldTags = tag
                return this@Builder
            }

            fun params(param: Bundle): Builder {
                fieldParams = param
                return this@Builder
            }

            fun cancelable(cancel: Boolean): Builder {
                fieldCancelable = cancel
                return this@Builder
            }

            fun show() {
                LocalDialogFragment().also { f ->
                    Bundle().apply {
                        putString(ARG_TITLE, fieldTitle)
                        putString(ARG_MESSAGE, fieldMessage)
                        putStringArray(ARG_ITEMS, fieldItems)
                        putString(ARG_POSITIVE_BUTTON_LABEL, fieldPositiveButtonLabel)
                        putString(ARG_NEGATIVE_BUTTON_LABEL, fieldNegativeButtonLabel)
                        putBoolean(ARG_CANCELABLE, fieldCancelable)

                        fieldParams?.let {
                            putBundle(ARG_PARAMS, it)
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