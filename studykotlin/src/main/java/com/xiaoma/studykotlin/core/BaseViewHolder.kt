package com.xiaoma.studykotlin.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder :RecyclerView.ViewHolder{

    constructor(itemView: View):super(itemView)

    private val  viewHashMap = HashMap<Int, View>()

    protected fun <T:View> getView(id:Int):T{
        var view:View? =viewHashMap.get(id)
        if(view==null){
            view=itemView.findViewById(id)
            viewHashMap.put(id,view)
        }
        return view as T
    }

    protected fun setText(@IdRes id:Int,text:String?){
        getView<TextView>(id).text = text
    }

}