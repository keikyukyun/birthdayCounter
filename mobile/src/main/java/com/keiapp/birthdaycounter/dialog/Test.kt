package com.keiapp.birthdaycounter.dialog

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by murakamikei on 2017/11/18.
 */

class Test<A> where A : AppCompatActivity {
    //                       A : DialogActionInterface{
//                       F : Fragment {
//                       F : DialogActionInterface {
    private var fieldActivity: AppCompatActivity?
    private var fieldFragment: Fragment?

    constructor (activity: A) {
        fieldActivity = activity
        fieldFragment = null
    }

//    constructor (fragment: F) {
//        fieldFragment = fragment
//        fieldActivity = null
//    }
}

fun call() {
    val activity = AppCompatActivity()
    val fragment = Fragment()
    Test(activity)
}
