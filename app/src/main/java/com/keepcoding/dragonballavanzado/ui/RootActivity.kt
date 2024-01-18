package com.keepcoding.dragonballavanzado.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keepcoding.dragonballavanzado.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }
}