package com.keiapp.birthdaycounter.dialog

import android.os.Bundle
import java.io.Serializable

/**
 * Created by murakamikei on 2017/10/29.
 */
interface DialogActionInterface : Serializable {
    /**
     * ダイアログでキャンセルをした。
     */
    fun cancel(requestCode: Int, params: Bundle)

    /**
     * ダイアログで決定した。
     */
    fun agreed(requestCode: Int, resultCode: Int, params: Bundle)

    /**
     * ダイアログの第三ボタンを選択した
     */
    fun continued(requestCode: Int, params: Bundle)
}