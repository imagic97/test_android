package com.smartid.muji_rfid_app.ui.replenish_details

import com.smartid.muji_rfid_app.ui.widgets.SizeList

class CommodityState(
    var warehouseName: String,
    var total: String,
    var activityName: String,
    var activityYear: String,
    var categoryName: String,
    var secondaryCategoryName: String,
    var image: String,
    var prefixId: String,
    var suffixId: String,
    var originPrice: String,
    var price: String,
    var spec: String,
    var isUrgent: Boolean,
    var isSameDay: Boolean,
    var isActive: Boolean,
    var sizes: ArrayList<SizeList.SizeState>
)