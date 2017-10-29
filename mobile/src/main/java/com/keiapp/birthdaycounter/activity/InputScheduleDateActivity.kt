package com.keiapp.birthdaycounter.activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.keiapp.birthdaycounter.IActionEventListener
import com.keiapp.birthdaycounter.R
import com.keiapp.birthdaycounter.dialog.DialogUtils
import com.keiapp.birthdaycounter.fragment.InputScheduleFragment

/**
 * Created by murakamikei on 2017/09/23.
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

    override fun onClickButton(view: View) {
        DialogUtils.showDatePickerDialog(this, R.layout.fragment_dialog, "dialog_fragment")
    }

    override fun onButtonPressed(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}