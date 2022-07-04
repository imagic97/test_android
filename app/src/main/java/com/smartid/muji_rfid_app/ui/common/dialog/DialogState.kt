package com.imagic.androidtest.ui.dialog

class DialogState {
    companion object {
        const val BUTTON_ALL: Int = 0
        const val BUTTON_POSITIVE: Int = 1
        const val BUTTON_NEGATIVE: Int = 2
    }

    var title: String? = null
    var content: String? = null

    var negativeText: String = "取消"
    var positiveText: String = "确认"

    var buttonsState: Int = BUTTON_ALL
}