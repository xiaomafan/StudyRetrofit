package com.xiaoma.studykotlin.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView

import com.xiaoma.studykotlin.R
import com.xiaoma.studykotlin.core.BaseViewHolder
import com.xiaoma.studykotlin.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
    private var list: List<Lesson> = ArrayList()

    //类似public 跨包访问
    internal fun updateAndNotify(list: List<Lesson>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
        //对应的操作符是[]
//        holder.onBind(list.get(position))
    }


    /**
     * 静态内部类
     */
    class LessonViewHolder internal constructor(itemView: View) : BaseViewHolder(itemView) {

        companion object{
             fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_lesson, parent, false))
            }
        }


        internal fun onBind(lesson: Lesson) {

//            val (code,message,body) =lesson

            //如果lesson.date为null，则使日期待定
            var date = lesson.date?:"日期待定"

          /*  var date = lesson.date
            if (date == null) {
                date = "日期待定"
            }*/
            setText(R.id.tv_date, lesson.date?:"日期待定")

            setText(R.id.tv_content, lesson.content)

            val state = lesson.state
            /**
             * let  also    run 里面的修改为this
             */
            lesson.state?.let {
                if (state != null) {
                    setText(R.id.tv_state, it.stateName())
                    var colorRes = when (it) {
                        Lesson.State.PLAYBACK -> R.color.playback
                        Lesson.State.LIVE -> R.color.live
                        Lesson.State.WAIT -> R.color.wait
                    }
                    val backgroundColor = itemView.context.getColor(colorRes)
                    getView<View>(R.id.tv_state).setBackgroundColor(backgroundColor)
                }
            }


         /*   if (state != null) {
                setText(R.id.tv_state, state.stateName())
//                var colorRes = R.color.playback

              var colorRes = when (state) {
                    Lesson.State.PLAYBACK -> R.color.playback
                    Lesson.State.LIVE -> R.color.live
                    Lesson.State.WAIT -> R.color.wait
                }
               *//* when (state) {
                    Lesson.State.PLAYBACK -> {
                        // 即使在 {} 中也是需要 break 的。
                        colorRes = R.color.playback
                    }
                    Lesson.State.LIVE -> colorRes = R.color.live
                    Lesson.State.WAIT -> colorRes = R.color.wait
                }*//*
                val backgroundColor = itemView.context.getColor(colorRes)
                getView<View>(R.id.tv_state).setBackgroundColor(backgroundColor)
            }*/
        }
    }
}
