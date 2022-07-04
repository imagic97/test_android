package com.smartid.muji_rfid_app.ui.replenish_details

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.ui.common.dialog.OnButtonClickListener

class CheckPermitDialog(private var _context: Context) : Dialog(_context) {
    lateinit var inputView: EditText

    lateinit var negativeBtn: Button

    lateinit var positiveBtn: Button

    var onButtonClickListener: OnButtonClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(false)
        window?.setGravity(Gravity.CENTER)

        val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_check_permit, null, false)
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        view.startAnimation(animation)
        initView(view)
        initEvent()
        setContentView(view)
    }

    private fun initView(view: View) {
        inputView = view.findViewById(R.id.dialog_permit_input)
        inputView.showSoftInput()
        negativeBtn = view.findViewById(R.id.dialog_btn_negative)
        positiveBtn = view.findViewById(R.id.dialog_btn_positive)
    }

    fun setOnClickListener(onButtonClickListener: OnButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener
    }

    private fun initEvent() {
        negativeBtn.setOnClickListener {
            this.dismiss()
            onButtonClickListener?.onNegativeClick()
        }

        positiveBtn.setOnClickListener {
            onButtonClickListener?.onPositiveClick()
        }

    }

    override fun show() {
        super.show()
        val layoutParams = window!!.attributes
        layoutParams.gravity = Gravity.CENTER
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = layoutParams
    }

    private fun EditText.showSoftInput() {
        this.requestFocus()
        this.postDelayed({
            val inputManager =
                this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(this, 0)
        }, 100)
    }
}