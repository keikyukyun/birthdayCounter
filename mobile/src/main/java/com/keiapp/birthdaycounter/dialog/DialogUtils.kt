package com.keiapp.birthdaycounter.dialog

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import com.keiapp.birthdaycounter.fragment.LocalDialogFragment

/**
 * Created by murakamikei on 2017/09/24.
 */
class DialogUtils {
    companion object {
        /**
         * 日付が表示されるダイアログを表示する。
         *
         * @param activity アクティビティ
         * @param layoutId レイアウトのリソースID
         * @param tag ダイアログの識別子
         */
        fun showDatePickerDialog(activity: FragmentActivity, layoutId: Int, tag: String) {
            val fragment = LocalDialogFragment.newInstance(layoutId)
            showDialog(activity, fragment, tag)
        }

        fun showAlertDialog() {

        }

        private fun showDialog(activity: FragmentActivity, fragment: DialogFragment, tag: String) {
            fragment.show(activity.supportFragmentManager, tag)
        }
    }
}