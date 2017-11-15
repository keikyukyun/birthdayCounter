package com.keiapp.birthdaycounter.dialog

import android.support.v4.app.FragmentActivity
import com.keiapp.birthdaycounter.DialogActionInterface
import com.keiapp.birthdaycounter.fragment.LocalDialogFragment

/**
 * Created by murakamikei on 2017/09/24.
 */
class DialogUtils {
    companion object {
        private var sDialog: LocalDialogFragment? = null

        /**
         * 日付が表示されるダイアログを表示する。
         *
         * @param activity アクティビティ
         * @param layoutId レイアウトのリソースID
         * @param tag ダイアログの識別子
         */
        fun showDatePickerDialog(activity: FragmentActivity, listener: DialogActionInterface, layoutId: Int, tag: String) {
            sDialog = LocalDialogFragment.newInstance(listener, layoutId)
            showDialog(activity, tag)
        }


        fun showAlertDialog() {

        }

        private fun showDialog(activity: FragmentActivity, tag: String) {
            sDialog?.show(activity.supportFragmentManager, tag)
        }

        fun dismiss() {
            sDialog?.dismiss()
        }
    }
}