package com.smartid.muji_rfid_app.ui.search

import android.graphics.Color
import android.widget.TextView
import com.smartid.muji_rfid_app.ui.widgets.tableLayout.TableAdapter
import com.smartid.muji_rfid_app.ui.widgets.tableLayout.TableLayout
import com.smartid.muji_rfid_app.ui.widgets.tableLayout.Util

class SearchTable(private var view: TableLayout, var data: ArrayList<ArrayList<String>>, var type: Type = Type.CUSTOMER) {

    enum class Type {
        INSIDE, CUSTOMER
    }

    init {
        firstRowAsTitle()
    }

    private fun firstRowAsTitle() {
        view.adapter = object : TableAdapter {
            override fun getColumnCount(): Int {
                return data[0].size
            }

            override fun getColumnContent(position: Int): Array<String?> {
                val rowCount = data.size
                val contents = arrayOfNulls<String>(rowCount)
                try {
                    for (i in 0 until rowCount) {
                        contents[i] = data[i][position]
                    }
                } catch (e: NoSuchFieldException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
                return contents
            }

            override fun onCreateCellView(view: TextView, x: Int, y: Int) {
                when (x) {
                    0 -> {
                        view.setTextColor(Color.parseColor("#92959B"))
                    }
                    1 -> {
                        view.setTextColor(Color.parseColor("#FF9900"))
                    }
                    else -> {
                        when (y) {
                            0 -> {
                                view.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            else -> {
                                view.setTextColor(Color.parseColor("#000000"))
                            }
                        }
                    }
                }
            }

            override fun setCellViewWidth(x: Int): Int {
                return when (x) {
                    0 -> {
                        Util.dip2px(view.resources, 73f).toInt()
                    }
                    else -> {
                        Util.dip2px(view.resources, 43f).toInt()
                    }
                }
            }

            override fun onClick(x: Int, y: Int) {}

        }
    }
}