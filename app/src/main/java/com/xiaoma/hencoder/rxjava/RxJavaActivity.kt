package com.xiaoma.hencoder.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.xiaoma.studyretrofit.R

class RxJavaActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var mOperater: Button
    private lateinit var mContent: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        mOperater = findViewById(R.id.btn_operate)
        mContent = findViewById(R.id.tv_content)

    }

    override fun onClick(v: View?) {
        if(v is Button){
            setData()
        }
    }

    private fun setData() {
//        Observable.create(ObservableOnSubscribe {  })
    }
}