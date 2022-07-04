package com.smartid.muji_rfid_app.ui.receipt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity
import kotlin.math.max

class ReceiptCommodityEditActivity : BaseActivity() {
    private lateinit var tabSignal: ImageView
    private lateinit var tabReadOrScan: Button
    private lateinit var rfidActualCount: LinearLayout
    private lateinit var notRfidActualCount: LinearLayout
    private lateinit var rfidInsetBtn: Button

    private lateinit var image: ImageView
    private lateinit var code: TextView
    private lateinit var name: TextView
    private lateinit var spec: TextView
    private lateinit var packageCode1: TextView
    private lateinit var packageCode2: TextView
    private lateinit var expectCount: TextView
    private lateinit var actualCount: TextView

    private var commodity: State = State()

    private var isRfid = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isRfid = intent.getBooleanExtra("value", false)
        setContentView(R.layout.activity_receipt_commodity_edit)
        initToolbar()
        initState()
        initView()
    }

    private fun initView() {
        tabSignal = findViewById(R.id.tab_signal)
        tabReadOrScan = findViewById(R.id.tab_read)
        rfidActualCount = findViewById(R.id.rfid_actual_count)
        notRfidActualCount = findViewById(R.id.not_rfid_actual_count)
        rfidInsetBtn = findViewById(R.id.rfid_inset_btn)

        image = findViewById(R.id.commodity_image)
        code = findViewById(R.id.commodity_code)
        name = findViewById(R.id.commodity_name)
        spec = findViewById(R.id.commodity_spec)
        packageCode1 = findViewById(R.id.commodity_package_id_1)
        packageCode2 = findViewById(R.id.commodity_package_id_2)
        expectCount = findViewById(R.id.commodity_expect_count)
        if (isRfid) {
            actualCount = findViewById(R.id.commodity_actual_count_rfid)
            findViewById<Button>(R.id.rfid_inset_btn).setOnClickListener {
                val intent = Intent(this, ReceiptCommodityRFIDInsetActivity::class.java)
                startActivity(intent)
            }
        } else {
            actualCount = findViewById(R.id.commodity_actual_count)
            findViewById<Button>(R.id.commodity_count_decrease_btn).setOnClickListener {
                commodity.actualCount = max(0, commodity.actualCount - 1)
                actualCount.text = commodity.actualCount.toString()
            }
            findViewById<Button>(R.id.commodity_count_increase_btn).setOnClickListener {
                commodity.actualCount += 1
                actualCount.text = commodity.actualCount.toString()
            }
        }

        setShowView()

        // TODO
        // image.setImageURI()
        code.text = commodity.code
        name.text = commodity.name
        spec.text = commodity.spec
        packageCode1.text = commodity.packageCode1
        packageCode2.text = commodity.packageCode2
        expectCount.text = commodity.expectCount.toString()
        actualCount.text = commodity.actualCount.toString()

    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.toolbar_title).text = "收货入库"
        findViewById<TextView>(R.id.toolbar_back_btn)?.setOnClickListener {
            this.onBackPressed()
        }
        findViewById<TextView>(R.id.toolbar_menu).text = "确定"
    }

    private fun initState() {
        commodity = State("", "4550344730828", "纯棉衬衫", "S", "50061211020389173", "50061211020389173", 100, 5)
    }

    /**
     * 控制 rfid 和 非rfid 下 view 的显示
     */
    private fun setShowView() {
        if (isRfid) {
            tabSignal.visibility = View.VISIBLE
            tabReadOrScan.text = "Read"
            rfidActualCount.visibility = View.VISIBLE
            notRfidActualCount.visibility = View.GONE
            rfidInsetBtn.visibility = View.VISIBLE
        } else {
            tabSignal.visibility = View.GONE
            tabReadOrScan.text = "Scan"
            rfidActualCount.visibility = View.GONE
            notRfidActualCount.visibility = View.VISIBLE
            rfidInsetBtn.visibility = View.GONE
        }
    }

    class State(
        var image: String = "",
        var code: String = "",
        var name: String = "",
        var spec: String = "",
        var packageCode1: String = "",
        var packageCode2: String = "",
        var expectCount: Int = 0,
        var actualCount: Int = 0,
    )
}