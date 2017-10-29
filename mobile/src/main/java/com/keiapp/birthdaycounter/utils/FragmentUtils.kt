package com.keiapp.birthdaycounter.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by murakamikei on 2017/10/09.
 */
class FragmentUtils {
    companion object {
        fun addFragment(fragment: Fragment, manager: FragmentManager, tag: String) {
            val transaction = manager.beginTransaction()
            transaction.add(fragment, tag)
        }

        fun addFragment(fragment: Fragment, manager: FragmentManager, layoutId: Int, tag: String) {
            val transaction = manager.beginTransaction()
            transaction.add(layoutId, fragment, tag)
        }
    }
}