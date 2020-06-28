package com.xiaoma.hencoder.kotlin

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView

abstract class BaseViewHolder : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView)

    private val viewHashMap = HashMap<Int, View>()

    @Suppress("UNCHECKED_CAST")
    protected fun <T : View> getView(@IdRes id: Int): T {
        var view: View? = viewHashMap.get(id)
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap.put(id, view)
        }
        return view as T
    }

    protected fun setText(@IdRes id: Int, text: String?) {
        getView<TextView>(id).text = text
    }

}