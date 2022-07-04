package com.smartid.muji_rfid_app.ui.widgets.tableLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartid.muji_rfid_app.R;

public class TableLayout extends LinearLayout implements TableColumn.Callback {

    private int selectedMode;
    private int tableRowHeight;
    private int tableRowWidth;
    private int tableDividerSize;
    private int tableDividerColor;
    private int tableColumnPadding;
    private int tableTextGravity;
    private int tableTextSize;
    private int tableTextColor;
    private int tableTextColorSelected;
    private int backgroundColorSelected;
    private int backgroundColorHeader;
    private int backgroundColorHeaderMode;
    private TableAdapter adapter;

    private int offsetX = -1;
    private int offsetY = -1;

    private Paint paint;

    public TableLayout(Context context) {
        super(context);
        init(null);
    }

    public TableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TableLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setWillNotDraw(false);
        paint = new Paint();
        paint.setAntiAlias(true);

        if (attrs != null) {
            TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.TableLayout);
            selectedMode = typedArray.getInt(R.styleable.TableLayout_selectedMode, 0);
            tableRowHeight = typedArray.getDimensionPixelSize(R.styleable.TableLayout_tableRowHeight, (int) Util.dip2px(getResources(), 36));
            tableRowWidth = typedArray.getDimensionPixelSize(R.styleable.TableLayout_tableRowWidth, -1);
            tableDividerSize = typedArray.getDimensionPixelSize(R.styleable.TableLayout_tableDividerSize, 1);
            tableDividerColor = typedArray.getColor(R.styleable.TableLayout_tableDividerColor, Color.GRAY);
            tableColumnPadding = typedArray.getDimensionPixelSize(R.styleable.TableLayout_tableColumnPadding, 0);
            tableTextGravity = typedArray.getInt(R.styleable.TableLayout_tableTextGravity, 0);
            tableTextSize = typedArray.getDimensionPixelSize(R.styleable.TableLayout_tableTextSize, (int) Util.dip2px(getResources(), 12));
            tableTextColor = typedArray.getColor(R.styleable.TableLayout_tableTextColor, Color.GRAY);
            tableTextColorSelected = typedArray.getColor(R.styleable.TableLayout_tableTextColorSelected, Color.BLACK);
            backgroundColorSelected = typedArray.getColor(R.styleable.TableLayout_backgroundColorSelected, Color.TRANSPARENT);
            backgroundColorHeader = typedArray.getColor(R.styleable.TableLayout_backgroundColorHeader, Color.TRANSPARENT);
            backgroundColorHeaderMode = typedArray.getColor(R.styleable.TableLayout_backgroundColorHeaderMode, 0);
            typedArray.recycle();
        }

        if (isInEditMode()) {
            String[] content = {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa"};
            addView(new TableColumn(getContext(), content, this, 0));
            addView(new TableColumn(getContext(), content, this, 1));
            addView(new TableColumn(getContext(), content, this, 2));
            addView(new TableColumn(getContext(), content, this, 3));
            addView(new TableColumn(getContext(), content, this, 4));
            addView(new TableColumn(getContext(), content, this, 5));
            addView(new TableColumn(getContext(), content, this, 6));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            width += child.getMeasuredWidth();
            height = Math.max(height, child.getMeasuredHeight());
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    void onDrawSelectedBackground(Canvas canvas) {
        if (selectedMode == 0 || offsetX == -1 || offsetY == -1) return;

        TableColumn activeColumn = (TableColumn) getChildAt(offsetX);
        TextView activeCell = (TextView) activeColumn.getChildAt(offsetY);
        float top = 0;
        float left = 0;
        float bottom = 0;
        float right = 0;

        paint.setColor(backgroundColorSelected);
        paint.setStyle(Paint.Style.FILL);

        // 行
        if (selectedMode == 1) {
            left = 0;
            right = getWidth();
            bottom = activeCell.getBottom();
            top = activeCell.getTop();
        }
        // 列
        if (selectedMode == 2) {
            left = activeColumn.getLeft();
            right = activeColumn.getRight();
            bottom = getHeight();
            top = 0;
        }
        // cell
        if (selectedMode == 3) {
            left = activeColumn.getLeft();
            right = activeColumn.getRight();
            bottom = activeCell.getBottom();
            top = activeCell.getTop();
        }

        canvas.drawRect(left, top, right, bottom, paint);
    }

    void onDrawHeaderBackground(Canvas canvas) {
        if (backgroundColorHeaderMode == 0) return;

        float top = 0;
        float left = 0;
        float bottom = 0;
        float right = 0;

        paint.setColor(backgroundColorHeader);
        paint.setStyle(Paint.Style.FILL);

        // 行
        if (backgroundColorHeaderMode == 2) {
            left = 0;
            right = getChildAt(0).getWidth();
            bottom = getHeight();
            top = 0;
        }

        // 列
        if (backgroundColorHeaderMode == 1) {
            left = 0;
            right = getWidth();
            bottom = ((TableColumn) getChildAt(0)).getChildAt(0).getHeight();
            top = 0;
        }

        canvas.drawRect(left, top, right, bottom, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawSelectedBackground(canvas);
        onDrawHeaderBackground(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(tableDividerColor);
        int drawnWidth = 0;
        int maxRowCount = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            TableColumn column = (TableColumn) getChildAt(i);
            maxRowCount = Math.max(maxRowCount, column.getChildCount());
            if (i > 0) {
                if (tableDividerSize > 1) {
                    canvas.drawRect(drawnWidth - tableDividerSize / 2, 0, drawnWidth + tableDividerSize / 2, getHeight(), paint);
                } else {
                    canvas.drawRect(drawnWidth - tableDividerSize, 0, drawnWidth, getHeight(), paint);
                }
            }
            drawnWidth += column.getWidth();
        }
        for (int i = 1; i < maxRowCount; i++) {
            float y = i * tableRowHeight;
            if (tableDividerSize > 1) {
                canvas.drawRect(0, y - tableDividerSize / 2, getWidth(), y + tableDividerSize / 2, paint);
            } else {
                canvas.drawRect(0, y - tableDividerSize, getWidth(), y, paint);
            }
        }

        canvas.drawRect(0, 0, tableDividerSize, getHeight(), paint);
        canvas.drawRect(getWidth() - tableDividerSize, 0, getWidth(), getHeight(), paint);
        canvas.drawRect(0, 0, getWidth(), tableDividerSize, paint);
        canvas.drawRect(0, getHeight() - tableDividerSize, getWidth(), getHeight(), paint);
    }

    @Override
    public TableLayout getTableLayout() {
        return this;
    }

    public int getSelectedMode() {
        return selectedMode;
    }

    public int getTableRowHeight() {
        return tableRowHeight;
    }

    public int getTableRowWidth() {
        return tableRowWidth;
    }

    public int getTableDividerSize() {
        return tableDividerSize;
    }

    public int getTableDividerColor() {
        return tableDividerColor;
    }

    public int getTableColumnPadding() {
        return tableColumnPadding;
    }

    public int getTableTextGravity() {
        return tableTextGravity;
    }

    public int getTableTextSize() {
        return tableTextSize;
    }

    public int getTableTextColor() {
        return tableTextColor;
    }

    public int getTableTextColorSelected() {
        return tableTextColorSelected;
    }

    public int getBackgroundColorSelected() {
        return backgroundColorSelected;
    }

    public TableAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(TableAdapter adapter) {
        this.adapter = adapter;
        useAdapter();
    }

    private void useAdapter() {
        removeAllViews();
        int count = adapter.getColumnCount();

        for (int i = 0; i < count; i++) {
            addView(new TableColumn(getContext(), adapter.getColumnContent(i), this, i));
        }
    }

    public void onClick(float x, float y) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            TableColumn tableColumn = (TableColumn) getChildAt(i);
            if (tableColumn.getRight() >= x) {
                tableColumn.getChildCount();
                onClickChild(tableColumn, y, i);
                return;
            }
        }
    }

    public void onClickChild(TableColumn tableColumn, float y, int xIndex) {
        int childCount = tableColumn.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView cell = (TextView) (tableColumn.getChildAt(i));
            if (cell.getBottom() >= y) {
                setSelected(xIndex, i);
                return;
            }
        }
    }

    void setSelected(int x, int y) {
        if (selectedMode == 1) {
            setSelectedRow(x, y);
        }
        if (selectedMode == 2) {
            setSelectedColumn(x, y);
        }
        if (selectedMode == 3) {
            setSelectedCell(x, y);
        }
    }

    void setSelectedCell(int x, int y) {
        if (this.offsetY != -1 && this.offsetX != -1) {
            TableColumn tableColumn = (TableColumn) getChildAt(this.offsetX);
            TextView cell = (TextView) tableColumn.getChildAt(this.offsetY);
            cell.setActivated(false);
        }

        if (x != this.offsetX || y != this.offsetY) {
            this.offsetX = x;
            this.offsetY = y;
            TableColumn tableColumn = (TableColumn) getChildAt(x);
            TextView cell = (TextView) tableColumn.getChildAt(y);
            cell.setActivated(true);
        } else {
            this.offsetX = -1;
            this.offsetY = -1;
        }
        invalidate();
    }

    void setSelectedRow(int x, int y) {
        if (this.offsetY != -1 && this.offsetX != -1) {
            for (int i = 0; i < getChildCount(); i++) {
                TableColumn tableColumn = (TableColumn) getChildAt(i);
                TextView cell = (TextView) tableColumn.getChildAt(this.offsetY);
                cell.setActivated(false);
            }
        }

        if (y != this.offsetY) {
            this.offsetX = x;
            this.offsetY = y;
            for (int i = 0; i < getChildCount(); i++) {
                TableColumn tableColumn = (TableColumn) getChildAt(i);
                TextView cell = (TextView) tableColumn.getChildAt(y);
                cell.setActivated(true);
            }
        } else {
            this.offsetX = -1;
            this.offsetY = -1;
        }

        invalidate();
    }

    void setSelectedColumn(int x, int y) {
        if (this.offsetY != -1 && this.offsetX != -1) {
            TableColumn tableColumn = (TableColumn) getChildAt(this.offsetX);
            for (int i = 0; i < tableColumn.getChildCount(); i++) {
                TextView cell = (TextView) tableColumn.getChildAt(i);
                cell.setActivated(false);
            }
        }

        if (x != this.offsetX) {
            this.offsetX = x;
            this.offsetY = y;
            TableColumn tableColumn = (TableColumn) getChildAt(x);
            for (int i = 0; i < tableColumn.getChildCount(); i++) {
                TextView cell = (TextView) tableColumn.getChildAt(i);
                cell.setActivated(true);
            }
        } else {
            this.offsetX = -1;
            this.offsetY = -1;
        }

        invalidate();
    }


}
