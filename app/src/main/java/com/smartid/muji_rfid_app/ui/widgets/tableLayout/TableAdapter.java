package com.smartid.muji_rfid_app.ui.widgets.tableLayout;

import android.widget.TextView;

public interface TableAdapter {

    int getColumnCount();

    String[] getColumnContent(int position);

    /**
     * 自定义 TableCell 属性
     *
     * @param view 当前 cell 的渲染 view
     * @param x    当前 cell 所在行
     * @param y    当前 cell 所在列
     */
    void onCreateCellView(TextView view, int x, int y);

    /**
     * 自定义 TableCell 宽度
     *
     * @param x 当前 cell 所在行
     */
    int setCellViewWidth(int x);

    /**
     * 点击事件
     *
     * @param x 被点击 x 轴
     * @param y 被点击 y 轴
     */
    void onClick(int x, int y);
}
