package com.keiapp.birthdaycounter.dialog

import android.support.v4.app.FragmentManager

/**
 * ダイアログ操作を行うメソッド群を保有するインタフェース
 * Created by murakamikei on 2017/10/01.
 */
interface DialogActionListener {
    /**
     * ダイアログを表示する。
     *
     * @param manager フラグメントを管理するマネージャークラス
     * @raturn 結果を返す。
     */
    fun show(manager: FragmentManager) :Boolean

    /**
     * ダイアログを非表示にする。
     *
     * @param manager フラグメントを管理するマネージャークラス
     * @return 結果を返す
     */
    fun hide(manager: FragmentManager) :Boolean

    /**
     * 処理をキャンセルする
     *
     * @return 結果を返す。
     */
    fun cancel() :Boolean

    /**
     * 現在表示中のダイアログ名を取得する。
     *
     * @param manager フラグメントを管理するマネージャークラス
     * @return ダイアログのタグ名を返却する。<br>
     *     正常出ない場合はnull
     */
    fun getCurrentDialog(manager: FragmentManager) :String?
}