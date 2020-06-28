package com.xiaoma.hencoder.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.xiaoma.studyretrofit.R

class KotlinActivity : AppCompatActivity(), View.OnClickListener {

    private val username: String = "username"
    private val password: String = "password"

    private lateinit var etUsername: EditText
    private lateinit var etCode: EditText
    private var etPassword: EditText? = null
    private lateinit var login: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        login = findViewById(R.id.btn_login)
        etCode = findViewById(R.id.et_code)
        etUsername.setText(CacheUtils.get(username))
        etPassword?.setText(CacheUtils.get(password))
        login.setOnClickListener(this)
//        BuildTypeUtils.drawBadge(this)
        Utils.drawBadge(this)
        val user = User()
    }


    override fun onClick(v: View?) {
        if (v is Button) {
            login()
        } else if (v is CodeView) {
//           val codeView:CodeView =  v as CodeView
            v.updateCode()
        }
    }

    private fun login() {
        val username: String = etUsername.text.toString()
        val code: String = etCode.text.toString()
        val password: String = etPassword?.text.toString()
        val user = User(username, password, code)
        if (varify(user)) {
            CacheUtils.save(username, username)
            CacheUtils.save(password, password)
//            startActivity(Intent(this,LessionActivity::class))
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun varify(user: User): Boolean {
        if (user.userName != null && user.userName!!.length < 4) {
            Utils.toast("用户名不合法")
            return false
        }
        if (user.passWorld != null && user.passWorld!!.length < 4) {
            Utils.toast("密码不合法")
            return false
        }
        return true
    }
}