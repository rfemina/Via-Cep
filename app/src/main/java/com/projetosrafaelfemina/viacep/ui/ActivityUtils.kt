package com.projetosrafaelfemina.viacep.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.projetosrafaelfemina.viacep.R

class ActivityUtils {

    companion object {
        fun initActionBar(activity: AppCompatActivity) {

            activity.enableEdgeToEdge()

            ViewCompat.setOnApplyWindowInsetsListener(activity.findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            activity.window.statusBarColor = Color.parseColor("#FF018786")
            activity.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF018786")))
        }
    }
}