package com.example.mbti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.mbti.model.Post
import com.example.mbti.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity() {
    private val splashDuration = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        CoroutineScope(Dispatchers.Main).launch {
            delay(splashDuration)
            if(MyApplication.checkAuth()){
                MyApplication.db.collection("users")
                    .get()
                    .addOnSuccessListener {result ->
                        for(document in result){
                            val user = document.toObject(User::class.java)
                            if(user.email==MyApplication.auth.currentUser?.email){
                                MyApplication.email= user.email
                                MyApplication.nickname = user.nickname
                                MyApplication.mbti = user.mbti
                                MyApplication.gender = user.gender
                                MyApplication.age = user.age
                            }

                        }
                    }
                navigateToMainActivity()
            }else{
                navigateToLoginActivity()
            }

        }

    }
    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}