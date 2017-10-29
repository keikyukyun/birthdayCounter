package com.keiapp.birthdaycounter

import android.net.Uri
import android.view.View

/**
 * なんちゃらコールバック。
 */
interface IActionEventListener {
    // 適当すぎるので、必要なコールバックがあったら追加していく。
    fun onClickButton(view: View)
    fun onButtonPressed(uri: Uri)
}