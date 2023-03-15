package com.app.torch.utils.managers

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

interface RuntimePermissionsManagerInterface {

    fun requestPermissions(
        vararg permissions: String,
        requestCode: Int,
        requestPermissionsResult: (Boolean, Int) -> Unit
    )

    fun areAllPermissionsGranted(permissions: Array<String>): Boolean

}

class RuntimePermissionsManager(private val context: Context) : RuntimePermissionsManagerInterface {


    override fun areAllPermissionsGranted(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            return permissions.count { context.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED } == 0
        }

        return true
    }

    override fun requestPermissions(
        vararg permissions: String,
        requestCode: Int,
        requestPermissionsResult: (Boolean, Int) -> Unit
    ) {
        if (arePermissionsGranted(permissions)) {
            requestPermissionsResult.invoke(true, requestCode)
        } else {
            context.let {
                ActivityCompat.requestPermissions(it as Activity, permissions, requestCode)
                requestPermissionsResult.invoke(false, requestCode)
            }
        }
    }

    private fun arePermissionsGranted(permissions: Array<out String>): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            return permissions.count { context?.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED } == 0
        }

        return true
    }
}