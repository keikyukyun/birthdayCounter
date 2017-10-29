package com.keiapp.birthdaycounter

/**
 * Created by murakamikei on 2017/10/29.
 */
interface DialogActionInterface {
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