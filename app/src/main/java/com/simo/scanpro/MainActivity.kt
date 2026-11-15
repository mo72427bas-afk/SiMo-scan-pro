
package com.simo.scanpro

import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var preview: ImageView
    private lateinit var status: TextView

    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                preview.setImageURI(uri)
                preview.visibility = ImageView.VISIBLE
                status.text = "تم اختيار الصورة بنجاح"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutDirection = LinearLayout.LAYOUT_DIRECTION_RTL
            setPadding(24, 32, 24, 32)
            setBackgroundColor(Color.rgb(244, 247, 251))
        }

        val title = TextView(this).apply {
            text = "SiMo Scan Pro"
            textSize = 30f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(23, 59, 103))
        }

        val subtitle = TextView(this).apply {
            text = "ماسحك الضوئي الذكي للمستندات"
            textSize = 17f
            gravity = Gravity.CENTER
            setTextColor(Color.DKGRAY)
            setPadding(0, 12, 0, 28)
        }

        val galleryButton = Button(this).apply {
            text = "اختيار صورة من الهاتف"
            isAllCaps = false
            setOnClickListener {
                imagePicker.launch("image/*")
            }
        }

        val cameraButton = Button(this).apply {
            text = "مسح مستند بالكاميرا"
            isAllCaps = false
            setOnClickListener {
                status.text = "سيتم تجهيز محرك الكاميرا في المرحلة التالية"
            }
        }

        preview = ImageView(this).apply {
            visibility = ImageView.GONE
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_CENTER
        }

        status = TextView(this).apply {
            text = "مرحبًا بك في SiMo Scan Pro"
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(22, 120, 120))
            setPadding(0, 20, 0, 20)
        }

        page.addView(title)
        page.addView(subtitle)
        page.addView(galleryButton)
        page.addView(cameraButton)
        page.addView(status)

        page.addView(
            preview,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(page)
    }
}
