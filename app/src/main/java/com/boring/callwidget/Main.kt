package com.boring.callwidget

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class Main :AppCompatActivity() {

    private var onoff = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.intro)
        if (!onoff)
            requestPermissions()
        else
            Toast.makeText(this, "위젯을 사용하시면 됩니다!", Toast.LENGTH_SHORT).show()
            finish()
    }
    private fun requestPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ContextCompat.checkSelfPermission(baseContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 102)
        } else
            Toast.makeText(this, "위젯을 사용하시면 됩니다!", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            102 -> {
                Toast.makeText(this, "위젯을 이용하세요!", Toast.LENGTH_SHORT).show()
                onoff = true
                finish()
            }

        }
    }
}