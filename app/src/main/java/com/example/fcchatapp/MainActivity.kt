package com.example.fcchatapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.fcchatapp.chatlist.ChatListFragment
import com.example.fcchatapp.databinding.ActivityMainBinding
import com.example.fcchatapp.mypage.MyPageFragment
import com.example.fcchatapp.userlist.UserFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userFragment = UserFragment()
    private val chatFragment = ChatListFragment()
    private val myPageFragment = MyPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = Firebase.auth.currentUser

        if (currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.userList -> {
                    replaceFragment(userFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.chatRoomList -> {
                    replaceFragment(chatFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.myPage -> {
                    replaceFragment(myPageFragment)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }

        replaceFragment(userFragment)

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.frameLayout, fragment)
                commit()
            }
    }
}