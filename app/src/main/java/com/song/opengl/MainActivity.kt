package com.song.opengl

import android.app.ActivityManager
import android.content.Context
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    private lateinit var glSurfaceView: GLSurfaceView
    var renderSet = false // 记录glSurfaceView是否有效
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glSurfaceView = GLSurfaceView(this)

        // 检查系统是否支持openGL 2.0
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val conFiguration = activityManager.deviceConfigurationInfo
        val supportEs2 = conFiguration.reqGlEsVersion >= 0x20000

        // 渲染表面
        if (supportEs2){
            glSurfaceView.setEGLContextClientVersion(2)
            glSurfaceView.setRenderer(FirsOpenGLProjectRender())
            renderSet = true
        } else {
            Toast.makeText(this, " not support OPENGL 2.0", Toast.LENGTH_SHORT).show()
            return
        }

        // view加入到activity
        setContentView(glSurfaceView)
    }

    override fun onPause() {
        super.onPause()
        if (renderSet) {
            glSurfaceView.onPause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (renderSet) {
            glSurfaceView.onResume()
        }
    }
}