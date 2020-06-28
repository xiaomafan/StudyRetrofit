package com.xiaoma.studykotlin.lesson

import com.google.gson.reflect.TypeToken
import com.xiaoma.studykotlin.core.http.EntityCallback
import com.xiaoma.studykotlin.core.http.HttpClient
import com.xiaoma.studykotlin.core.utils.Utils
import com.xiaoma.studykotlin.lesson.entity.Lesson
import java.lang.reflect.Type

class LessonPresenter(private var activity: LessonActivity) {

    //静态变量声明
    companion object {
        //const  把静态变量声明为常量
        const val LESSON_PATH: String = "lessons"
    }

    /*  private var activity: LessonActivity

    constructor(activity: LessonActivity) {
        this.activity = activity
    }*/

    private var lessons: List<Lesson> = ArrayList()

    private val type: Type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData(){
        //匿名内部类，如果是接口不用小括号
        HttpClient.get(LESSON_PATH,type,object :EntityCallback<List<Lesson>>{
            override fun onSuccess(entity: List<Lesson>) {
                //获取当前类的变量
                println(entity)
                this@LessonPresenter.lessons=entity
                activity.runOnUiThread(object :Runnable{
                    override fun run() {
                        activity.showResult(lessons)
                    }
                })
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread(object :Runnable{
                    override fun run() {
                        Utils.toast(message)
                    }
                })
            }

        })
    }

    fun showPlayback(){
         //filter类似Rxjava的
        val  playbackLessons=lessons.filter {
            it.state === Lesson.State.PLAYBACK
        }
    /*   val playbackLessons = ArrayList<Lesson>()

        //for循环使用  in
        lessons.forEach {
            if (it.state === Lesson.State.PLAYBACK) {
                playbackLessons.add(it)
            }
        }*/

       /* for (lesson in lessons) {
            if (lesson.state === Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }*/
        activity.showResult(playbackLessons)
    }
}