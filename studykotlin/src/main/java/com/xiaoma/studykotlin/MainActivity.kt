package com.xiaoma.studykotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.reflect.TypeToken
import com.xiaoma.studykotlin.core.http.EntityCallback
import com.xiaoma.studykotlin.core.http.HttpClient
import com.xiaoma.studykotlin.core.utils.CacheUtils
import com.xiaoma.studykotlin.core.utils.CacheUtils.context
import com.xiaoma.studykotlin.core.utils.Utils
import com.xiaoma.studykotlin.core.utils.log
import com.xiaoma.studykotlin.entity.User
import com.xiaoma.studykotlin.lesson.LessonActivity
import com.xiaoma.studykotlin.lesson.LessonPresenter
import com.xiaoma.studykotlin.lesson.entity.Lesson
import com.xiaoma.studykotlin.widget.CodeView
import kotlinx.coroutines.*
import java.lang.Runnable
import java.lang.reflect.Type
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val usernameKey: String = "username"
    private val passwordKey: String = "password"
    private val TAG:String="MainActivity"

    //晚一点声明的变量， 不能修饰可null类型，并且不能有初始值
    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var et_code: EditText
    private  var et_codenme: EditText?=null  //?表示可null类型，注意控件不可为null

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)


        et_username.setText(CacheUtils.get(usernameKey))
        et_username!!.setText(CacheUtils.get(usernameKey)) //如果是空则抛出异常,即强行调用
        et_username?.setText(CacheUtils.get(usernameKey)) //如果不是null时才调用设置
        et_password.setText(CacheUtils.get(passwordKey))

        val btn_login: Button = findViewById(R.id.btn_login)
        val btn_loginOne: Button = findViewById<Button>(R.id.btn_login)
        val img_code: CodeView = findViewById(R.id.code_view)
        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)

        (this).log("1111")
        (this as Context).log("222222")  //静态解析

        val mThread=CustomThread()
        mThread.start()

        val mRunnable= Runnable {
            run {
                Thread.sleep(5000)
                println("5s后打印输出")
            }
        }

        Thread(mRunnable).start()



       Thread(object :Runnable{
           override fun run() {
//               println("线程开始了啊")
               Log.e(TAG,"线程开始了啊")
           }
       }).start()

      /*  Thread({
            Log.e(TAG,"线程开始了啊,SAM")
        }).start()*/

        Thread{
            Log.e(TAG,"闭包线程开启了啊线程开始了啊")
        }.start()



      /*  val coroutineScope= CoroutineScope()

        coroutineScope.launch(Dispatchers.IO){

        }*/

        //函数未挂起，执行fetchData()获取函数是，同时执行下面内容，线程在main线程，它是线程阻塞的
        runBlocking {
            Log.e(TAG,Thread.currentThread().name+"//1//")
            fetchData()
            Log.e(TAG,Thread.currentThread().name+"//3//")
        }

//        async{}

        //函数挂起，等fetchData()获取数据后继续执行，本身在子线程DefaultDispatcher-worker-2，不会阻塞线程
        GlobalScope.launch {
            Log.e(TAG,Thread.currentThread().name+"//1//")
            fetchData()
            Log.e(TAG,Thread.currentThread().name+"//3//")
        }



      /*  val coroutineScope = CoroutineScope(context)
        coroutineScope.launch(Dispatchers.Main) {
           var img= withContext(Dispatchers.IO){
                fetchData()
            }
            fetchData()
        }*/



//        getImage(1)

//        getBigImg()

    }

    suspend fun getImage(imageId:Int)= withContext(Dispatchers.IO){
            getBigImg()

        launch(Dispatchers.IO) {

        }
    }

    /**
     * 如果创建的suspend函数内没有真正的挂起逻辑，会提示suspend是多余的
     * 所以这个关键字是用来提醒函数调用者这个函数只能在协程里被调用，如果在非协程里被调用，就会编译不通过
     */
    suspend fun getBigImg(){
        delay(5)
    }



    private val type: Type = object : TypeToken<List<Lesson>>() {}.type
    fun fetchData(){
        //匿名内部类，如果是接口不用小括号
        HttpClient.get(LessonPresenter.LESSON_PATH,type,object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                //获取当前类的变量
                Log.e(TAG,Thread.currentThread().name+"//2//")
            }

            override fun onFailure(message: String?) {

            }

        })
    }



    class CustomThread:Thread(){
        override fun run() {
            super.run()
            Thread.sleep(1000)
            println("线程打印")
        }
    }

    override fun onClick(v: View?) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username:String=et_username.text.toString()
        val password:String=et_password.text.toString()
        val code:String=et_code.text.toString()
        val user=User(username,password,code)

        val (usename,passwor)=user  //解构需要类有data关键字或者 operate



        //方法可以写到方法里面，且内部参数可以访问外部方法参数---嵌套函数  生成额外对象
        fun verify():Boolean {
//        if(user.username!=null && user.username!!.length<4){
            if (user.username?.length ?: 0 < 4) {
                Utils.toast("用户名不合法")
                return false
            }
            /**
             * 第二个参数为什么还需要判断，因为涉及多线程
             */
            if (user.password != null && user.password!!.length < 4) {
                Utils.toast("密码不合法")
                return false
            }
            return true
        }

        if (verify()){
            CacheUtils.save(usernameKey,username)
            CacheUtils.save(passwordKey,password)
            //以文件的形式定义，如果是get()类似的，容易重名，可读性低
//            getSum(6);
            //LessonActivity::class kotlin的Class
            startActivity(Intent(this,LessonActivity::class.java))
        }
    }

 /*   private fun verify(user:User):Boolean{
//        if(user.username!=null && user.username!!.length<4){
        if(user.username?.length?:0<4){
            Utils.toast("用户名不合法")
            return false
        }
        if(user.password!=null && user.password!!.length<4){
            Utils.toast("密码不合法")
            return false
        }
        return true
    }*/
}