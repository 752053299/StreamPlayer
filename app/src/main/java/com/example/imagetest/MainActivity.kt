package com.example.imagetest

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var surfaceImage : SurfaceView? = null
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        surfaceImage = findViewById(R.id.surface_image)

        surfaceImage?.holder?.addCallback(object :SurfaceHolder.Callback {
            override fun surfaceCreated(p0: SurfaceHolder) {
                Log.i(TAG, "surfaceCreated: ")
                readFileFromSDCard(p0.surface)
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {

            }

        })
    }

    private fun readFileFromSDCard(surface: Surface) {
        val filePath = "/sdcard/dog.jpg" // 修改为实际路径
        val file = File(filePath)
        if (file.exists()) {
            try {
                FileInputStream(file).use { fis ->
                    val bitmap = BitmapFactory.decodeStream(fis)
                    var nativeWindowSample = NativeWindowSample()
                    Log.i(TAG, "draw " + bitmap.width + "x" + bitmap.height)
                    nativeWindowSample.drawBitmap(surface,bitmap)

                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            // 文件不存在处理逻辑
        }
    }


}