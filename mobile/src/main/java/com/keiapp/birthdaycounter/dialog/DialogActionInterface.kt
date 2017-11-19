package com.keiapp.birthdaycounter.dialog

import java.io.Serializable

/**
 * Created by murakamikei on 2017/10/29.
 */
interface DialogActionInterface : Serializable {
    /**
     * ダイアログでキャンセルをした。
     */
    fun cancel()

    /**
     * ダイアログで決定した。
     */
    fun agreed()

    /**
     *
     */
    fun continued()
}