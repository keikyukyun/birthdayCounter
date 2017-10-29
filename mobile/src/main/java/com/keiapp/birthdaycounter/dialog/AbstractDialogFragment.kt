package com.keiapp.birthdaycounter.dialog

import android.support.v4.app.DialogFragment

/**
 * ダイアログに関しての処理を包括する抽象クラス。
 */
abstract class AbstractDialogFragment<T> : DialogFragment() {
    var value :T? = null
}