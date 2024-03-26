package com.vendor.accessfiler

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Environment
import android.provider.Settings
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textBox: TextView
    private lateinit var filePath: EditText
    private lateinit var message: EditText
    private lateinit var btWrite: Button
    private lateinit var btreadd: Button
    private lateinit var btDeletee: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textBox = findViewById(R.id.tvAcess)
        filePath = findViewById(R.id.etPath)
        message = findViewById(R.id.etText)
        btWrite = findViewById(R.id.btWritte)
        btreadd = findViewById(R.id.btRead)
        btDeletee = findViewById(R.id.btDelete)

        btWrite.setOnClickListener {
            if (checkPermission()) {
                Log.i(TAG,"Writing the message in a file "+ filePath.text.toString()
                        +" through JNI")
                textBox.text = writeFile(filePath.text.toString(), message.text.toString())
            } else {
                Log.i(TAG,"Storage permission was not granted, request")
                requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) Environment.isExternalStorageManager()
        else {
            val write =
                ContextCompat.checkSelfPermission(this, Manifest.permission.)
            val read =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermission() {
        Log.d(TAG, "Solicitando permissão")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val intent = Intent()
            intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
            intent.data = Uri.fromParts("package", this.packageName, null)
            storageActivityResultLauncher.launch(intent)
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), STORAGE_PERMISSION_CODE
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private val storageActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.i(TAG, "storageActivityResultLauncher: ")
            if (Environment.isExternalStorageManager()) {
                Log.i(TAG,"storageActivityResultLauncher:  Permissão de acesso ao armazenamento concedida.")
            } else {
                Log.i(TAG,"storageActivityResultLauncher: Permissão de acesso ao armazenamento negada....")
                Toast.makeText(this, "Permissão de acesso ao armazenamento negada....", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out   String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty()) {
                val write = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val read = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (write && read) Log.d(TAG, "onRequestPermissionsResult:   Permissão de acesso ao armazenamento concedida.")
                else {
                    Log.d(TAG, "onRequestPermissionsResult: Permissão de acesso ao armazenamento negada....")
                    Toast.makeText(this, "Permissão de acesso ao armazenamento negada....", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    /**
     * A native method that is implemented by the 'fileaccessjni' native library,
     * which is packaged with this application.
     */
    external fun readFile(yourFilepath: String): String
    external fun writeFile(yourFilepath: String, text: String): String
    external fun removeFile(yourFilepath: String): String


    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
        private const val TAG = "FileAccessApp - Java"
        init {
            System.loadLibrary("fileaccessjni")
        }
    }
}