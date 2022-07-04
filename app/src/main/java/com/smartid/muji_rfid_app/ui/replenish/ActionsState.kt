package com.smartid.muji_rfid_app.ui.replenish

interface ActionsState {
    var firstBtnText: String
    var secondBtnText: String
    fun firstBtnOnClickCallback()
    fun secondBtnOnClickCallback()
}