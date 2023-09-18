package com.example.mbti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mbti.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var email:String
    private lateinit var password:String

    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.btnEmail.setOnClickListener{
//            val intent = Intent(this,EmailActivity::class.java)
//            startActivity(intent)
//        }

        binding.btnLogin.setOnClickListener{
            email = binding.etEmail.text.toString()
            password = binding.etPassword.text.toString()
            signIn(email,password)
            if(MyApplication.checkAuth()){
                val intent = Intent(this,InitialSettingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        MyApplication.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(EmailActivity.TAG, "signInWithEmail:success")
                    Toast.makeText(
                        baseContext,
                        "로그인 성공하셨습니다!",
                        Toast.LENGTH_SHORT,
                    ).show()
                    val user = MyApplication.auth.currentUser
                    MyApplication.email = email

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(EmailActivity.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "아이디/비밀번호를 확인해주세요!",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }
        // [END sign_in_with_email]
    }

}