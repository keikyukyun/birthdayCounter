package com.keiapp.birthdaycounter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.keiapp.birthdaycounter.R

class DevModeAdapter(private val mContext : Context?, private val mLayoutId : Int) : BaseAdapter() {
    private var mInflater : LayoutInflater? = null

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return 10
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        val holder:ViewHolder

        if (convertView == null) {
            convertView = mInflater?.inflate(mLayoutId, parent)
            holder = ViewHolder()
            convertView?.let {
                holder.textView = it.findViewById<TextView>(R.id.text) as TextView
                holder.textView?.text = position.toString()
                it.tag = holder
            } ?.run {
                return null
            }
        } else {
            holder = convertView.tag as ViewHolder
        }

        return convertView
    }

    private inner class ViewHolder {
        internal var textView : TextView? = null
    }
}