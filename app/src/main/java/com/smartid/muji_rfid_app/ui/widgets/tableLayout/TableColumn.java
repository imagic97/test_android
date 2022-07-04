package com.smartid.muji_rfid_app.ui.widgets.tableLayout;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class TableColumn extends LinearLayout {

    private final String[] content;
    private final Callback callback;
    private final int columnIndex;
    private float maxTextViewWidth;
    private Map<Integer, Integer> columnColors;

    public TableColumn(Context context, String[] content, Callback callback, int columnIndex) {
        super(context);
        this.content = content;
        this.callback = callback;
        this.columnIndex = columnIndex;
        init();
    }

    int getLayoutWidth() {
        int adaptersWidth = callback.getTableLayout().getAdapter().setCellViewWidth(columnIndex);

        return adaptersWidth == -1 ? (int) (callback.getTableLayout().getTableColumnPadding() * 2 + maxTextViewWidth) : adaptersWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getLayoutWidth(), callback.getTableLayout().getTableRowHeight() * getChildCount());
    }

    private void init() {
        setOrientation(VERTICAL);
        initContent();
    }

    private void initContent() {
        int padding = callback.getTableLayout().getTableColumnPadding();
        maxTextViewWidth = 0;
        ArrayList<TextView> textViews = new ArrayList<>();

        for (int i = 0; i < content.length; i++) {
            String text = content[i];
            if (TextUtils.isEmpty(text)) {
                text = "";
            }
            TextView textView = new TextView(getContext());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, callback.getTableLayout().getTableTextSize());
            textView.setTextColor(callback.getTableLayout().getTableTextColor());
            maxTextViewWidth = Math.max(maxTextViewWidth, Util.measureTextViewWidth(textView, text));
            textView.setGravity(getTextGravity(callback.getTableLayout().getTableTextGravity()));
            textView.setPadding(padding, 0, padding, 0);
            textView.setText(text);

            // 调用 adapter 的自定义 TextView 属性方法
            callback.getTableLayout().getAdapter().onCreateCellView(textView, i, columnIndex);

            textViews.add(textView);
        }

        LayoutParams layoutParams = new LayoutParams(getLayoutWidth(), callback.getTableLayout().getTableRowHeight());
        for (TextView textView : textViews) {
            addView(textView, layoutParams);
        }
    }

    private int getTextGravity(int tableTextGravity) {
        switch (tableTextGravity) {
            case 1:
                return Gravity.CENTER_VERTICAL;
            case 2:
                return Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        }
        return Gravity.CENTER;
    }


    public interface Callback {
        TableLayout getTableLayout();
    }

}
