package com.smartid.muji_rfid_app.ui.common.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import com.imagic.androidtest.ui.dialog.DialogState
import com.smartid.muji_rfid_app.R

class AppDialog(_context: Context) : Dialog(_context) {

    var titleView: TextView? = null

    var contentView: TextView? = null

    var negativeBtn: Button? = null

    var positiveBtn: Button? = null

    var btnSpace: View? = null

    var mContext: Context = _context

    var mState: DialogState? = null

    var onButtonClickListener: OnButtonClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(false)
        window?.setGravity(Gravity.CENTER)

        val view: View = LayoutInflater.from(mContext).inflate(R.layout.common_dialog, null)
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        view.startAnimation(animation)
        initView(view)
        initEvent()
        setContentView(view)
    }

    private fun initView(view: View) {
        titleView = view.findViewById(R.id.dialog_title)
        contentView = view.findViewById(R.id.dialog_content)
        negativeBtn = view.findViewById(R.id.dialog_btn_negative)
        positiveBtn = view.findViewById(R.id.dialog_btn_positive)
        btnSpace = view.findViewById(R.id.btn_space)
    }

    fun setOnClickListener(onButtonClickListener: OnButtonClickListener): AppDialog {
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

    private fun refreshState(state: DialogState?): AppDialog {
        if (state != null) {
            // 判断标题存在
            if (!TextUtils.isEmpty(state.title)) {
                titleView?.text = state.title
                titleView?.visibility = View.VISIBLE
            } else {
                titleView?.visibility = View.GONE
            }
            // 判断内容存在
            if (!TextUtils.isEmpty(state.content)) {
                contentView?.text = state.content
                contentView?.visibility = View.VISIBLE
            } else {
                contentView?.visibility = View.GONE
            }

            if (state.buttonsState == DialogState.BUTTON_ALL || state.buttonsState == DialogState.BUTTON_NEGATIVE) {
                if (!TextUtils.isEmpty(state.negativeText)) {
                    negativeBtn?.text = state.negativeText
                }
                negativeBtn?.visibility = View.VISIBLE
            } else {
                negativeBtn?.visibility = View.GONE
            }

            if (state.buttonsState == DialogState.BUTTON_ALL || state.buttonsState == DialogState.BUTTON_POSITIVE) {
                if (!TextUtils.isEmpty(state.positiveText)) {
                    positiveBtn?.text = state.positiveText
                }
                positiveBtn?.visibility = View.VISIBLE
            } else {
                positiveBtn?.visibility = View.GONE
            }

            if (state.buttonsState == DialogState.BUTTON_ALL) {
                btnSpace?.visibility = View.VISIBLE
            } else {
                btnSpace?.visibility = View.GONE
            }

        }
        return this
    }

    override fun show() {
        super.show()
        val layoutParams = window!!.attributes
        layoutParams.gravity = Gravity.CENTER
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.decorView.setPadding(10, 16, 10, 16)
        window!!.attributes = layoutParams

        refreshState(mState)
    }


    companion object {
        fun alert(context: Context, title: String, content: String): AppDialog {
            var state = DialogState()
            state.title = title
            state.content = content
            state.buttonsState = DialogState.BUTTON_POSITIVE

            var dialog = AppDialog(context)

            dialog.mState = state

            return dialog
        }

        fun confirm(context: Context, title: String, content: String): AppDialog {
            var state = DialogState()
            state.title = title
            state.content = content
            state.buttonsState = DialogState.BUTTON_ALL

            var dialog = AppDialog(context)

            dialog.mState = state

            return dialog
        }
    }

}