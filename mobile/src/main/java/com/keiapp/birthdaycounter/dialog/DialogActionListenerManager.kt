package com.keiapp.birthdaycounter.dialog

import android.support.v4.app.FragmentManager

/**
 * ダイアログを管理する。
 */
open class DialogActionListenerManager : DialogActionListener {
    override fun show(manager: FragmentManager): Boolean {
        return true
    }

    override fun hide(manager: FragmentManager): Boolean {
        return true
    }

    override fun cancel(): Boolean {
        return true
    }

    override fun getCurrentDialog(manager: FragmentManager): String? {
        return null
    }

    companion object {
        fun newInterface() {

        }
    }
}