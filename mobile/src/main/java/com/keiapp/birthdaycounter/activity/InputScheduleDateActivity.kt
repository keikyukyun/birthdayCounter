package com.keiapp.birthdaycounter.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.keiapp.birthdaycounter.IActionEventListener
import com.keiapp.birthdaycounter.R
import com.keiapp.birthdaycounter.fragment.InputScheduleFragment

/**
 * スケジュール入力するアクティビティ。
 */
class InputScheduleDateActivity : AppCompatActivity(), IActionEventListener {
    private val TAG : String = "input_fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_input_schedule_date)

        val fragment = InputScheduleFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.input_fragment_container, fragment, TAG)
        transaction.commit()
    }

    override fun onEnd() {

    }
}