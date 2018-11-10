package com.boring.callwidget

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class First:AppCompatActivity() {
    private val requestCode = 100
    private var phoneNo = ""
    private var pressTime:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro)
        var intent = Intent(Intent.ACTION_PICK)
        intent.data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        startActivityForResult(intent, requestCode) // 결과를 받아오려고!
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            var contentURL = data?.dataString
            var addressUri = data?.data
            val queryField = arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER)
            var cursor = contentResolver.query(addressUri,
                    queryField,
                    null,
                    null,
                    null)
            if (cursor != null) {
                cursor.moveToFirst()

                var name = cursor.getString(0)
                var phone = cursor.getString(1)
                cursor.close()

                Toast.makeText(this, name + "에게 전화합니다", Toast.LENGTH_LONG).show()

                phoneNo = "tel:" + phone
                startActivity(Intent("android.intent.action.CALL", Uri.parse(phoneNo)))
                finish()
            }
        } else if (requestCode == 100 && resultCode == RESULT_CANCELED) {
            finish()
        }
    }
}