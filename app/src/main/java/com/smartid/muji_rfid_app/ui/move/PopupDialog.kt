package com.smartid.muji_rfid_app.ui.move

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.dialog.OnButtonClickListener


class PopupDialog(_context: Context) : Dialog(_context) {

    var mContext: Context = _context

    var contentView: TextView? = null

    var negativeBtn: Button? = null

    var positiveBtn: Button? = null

    var onButtonClickListener: OnButtonClickListener? = null

    var content: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(false)
        window?.setGravity(Gravity.CENTER)

        val view: View = LayoutInflater.from(mContext).inflate(R.layout.activity_move_dialog, null)
        initView(view)
        initEvent()
        setContentView(view)
    }

    private fun initView(view: View) {
        contentView = view.findViewById(R.id.dialog_content)
        negativeBtn = view.findViewById(R.id.dialog_btn_negative)
        positiveBtn = view.findViewById(R.id.dialog_btn_positive)
    }

    fun setOnClickListener(onButtonClickListener: OnButtonClickListener): Dialog {
        this.onButtonClickListener = onButtonClickListener
        return this
    }

    private fun initEvent() {
        negativeBtn?.setOnClickListener {
            this.dismiss()
            onButtonClickListener?.onNegativeClick()
        }

        positiveBtn!!.setOnClickListener {
            this.dismiss()
            onButtonClickListener?.onPositiveClick()
        }
    }

    private fun refreshState(): Dialog {
        if (!TextUtils.isEmpty(content)) {
            // 判断内容存在
            contentView?.text = content
        }
        return this
    }

    override fun show() {
        super.show()
        val layoutParams = window!!.attributes
        layoutParams.gravity = Gravity.CENTER
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = layoutParams
        refreshState()
    }

    companion object {
        fun confirm(context: Context, content: String): PopupDialog {
            var dialog = PopupDialog(context)
            dialog.content = content
            return dialog
        }
    }

}