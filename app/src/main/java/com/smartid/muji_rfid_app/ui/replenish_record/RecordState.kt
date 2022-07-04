package com.smartid.muji_rfid_app.ui.replenish_record

class RecordState(
    var state: Int,
    var date: String,
    val sell_count: String,
    val replenish_count: String,
) {
    companion object {
        const val FINISH = 0
        const val PART = 1
        const val READY = 2
    }
}