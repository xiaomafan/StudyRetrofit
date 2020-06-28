package com.xiaoma.hencoder.kotlin

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LessonPresenter {

    companion object {
        const val LESSON_PATH = "lessons"
    }

    private var activity: LessonActivity

    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    var lessons: List<Lesson> = ArrayList()

    private val type: Type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread(object : Runnable {
                    override fun run() {
                        activity.showResult(lessons)
                    }
                })
            }

            override fun onFailure(message: String?) {
                Utils.toast(message)
            }

        })
    }

    fun showPlayback() {
        var playbackLessons = ArrayList<Lesson>()
        for (lesson in lessons) {
            if (lesson.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }


}