package com.smartid.muji_rfid_app.ui.search

import android.os.Bundle
import android.widget.Button
import com.smartid.muji_rfid_app.R
import com.smartid.muji_rfid_app.base.BaseActivity

class SearchActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        replaceFragment(MainFragment())

        initEvents()
    }

    private fun initEvents() {
        findViewById<Button>(R.id.search_tab_search).setOnClickListener {
            supportFragmentManager.popBackStack(null, 1)
            replaceFragment(MainFragment())
        }
        findViewById<Button>(R.id.search_tab_report).setOnClickListener {
            supportFragmentManager.popBackStack(null, 1)
            replaceFragment(ReportFilterFragment())
        }
        findViewById<Button>(R.id.search_tab_locks).setOnClickListener {
            supportFragmentManager.popBackStack(null, 1)
            replaceFragment(InsideDetailsFragment())
        }
        findViewById<Button>(R.id.search_tab_settings).setOnClickListener {
            supportFragmentManager.popBackStack(null, 1)
            replaceFragment(MainFragment())
        }
        findViewById<Button>(R.id.search_tab_more).setOnClickListener {
            supportFragmentManager.popBackStack(null, 1)
            replaceFragment(MainFragment())
        }
    }
}