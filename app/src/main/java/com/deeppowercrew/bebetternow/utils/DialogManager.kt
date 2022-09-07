package com.deeppowercrew.bebetternow.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.deeppowercrew.bebetternow.R

object DialogManager {
    fun showDialog(context: Context, messageId: Int, listener: Listener) {
        val builderDialog = AlertDialog.Builder(context)
        var dialog: AlertDialog? = null
        builderDialog.setTitle(R.string.attention)
        builderDialog.setMessage(messageId)
        builderDialog.setPositiveButton(R.string.reset) { _, _ ->

            listener.onClick()
            dialog?.dismiss()

        }
        builderDialog.setNegativeButton(R.string.back) { _, _ ->

            dialog?.dismiss()

        }

        dialog = builderDialog.create()
        dialog.show()
    }

    interface Listener {
        fun onClick()
    }
}