package com.xiaoma.studykotlin.lesson

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xiaoma.studykotlin.R
import com.xiaoma.studykotlin.core.BaseView
import com.xiaoma.studykotlin.core.utils.CacheUtils
import com.xiaoma.studykotlin.core.utils.firstChild
import com.xiaoma.studykotlin.lesson.entity.Lesson
import okhttp3.Response
import kotlin.reflect.KProperty


class Saver(var token: String) {
    operator fun getValue(lessonActivity: LessonActivity, property: KProperty<*>): String {
        return CacheUtils.get(token)
    }

    operator fun setValue(lessonActivity: LessonActivity, property: KProperty<*>, value: String) {
        CacheUtils.save(token, value)
    }
}

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {


    /*  var token:String
          set(value) {
              CacheUtils.save("token",value)
          }
          get() {
              return CacheUtils.get("token")
          }*/


    //属性委托   Saver跟operator一起组成属性委托
    var token: String by Saver("token")

    var token1: String by Saver("token1")

    //实现抽象变量  //保证只创建一次
    override val presenter: LessonPresenter by lazy {
        //        LessonPresenter(this)
        //保证只创建一次
        return@lazy LessonPresenter(this)
    }

    /**
     *
     */
    /* private val lessonPresenter=LessonPresenter(this)


     override fun getPresenter(): LessonPresenter {
         return lessonPresenter
     }*/

    val lessonAdapter = LessonAdapter()

    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        /*   val toolbar = findViewById<Toolbar>(R.id.toolbar)
           toolbar.inflateMenu(R.menu.menu_lesson)
           toolbar.setOnMenuItemClickListener(this)*/

        //扩展函数，可以早点判断控件是否为空
        findViewById<Toolbar>(R.id.toolbar)?.run {
            inflateMenu(R.menu.menu_lesson)
            setOnMenuItemClickListener(this@LessonActivity)
        }


        /* val recyclerView = findViewById<RecyclerView>(R.id.list)
         recyclerView.layoutManager = LinearLayoutManager(this)
         recyclerView.adapter = lessonAdapter
         recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))*/
        //普通函数，需要内部逐个判断控件是否为null
        with(findViewById<RecyclerView>(R.id.list)) {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }


        refreshLayout = findViewById(R.id.swipe_refresh_layout)
        refreshLayout.setOnRefreshListener { presenter.fetchData() }
        refreshLayout.isRefreshing = true

//        getPresenter().fetchData()
        presenter.fetchData()
        /**
         * 可以扩展属性和扩展方法
         */
//        refreshLayout.firstChild


        Log.e("xiaoma", token)

        Log.e("xiaoma", token1)

    }

    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        presenter.showPlayback()
        return false
    }


}



