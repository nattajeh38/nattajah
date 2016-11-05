package com.example.jah.workshop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by jah on 5/11/2559.
 */
class CustomAdapter extends BaseAdapter {

    Context mContext;
    String[] NameTopic;
    int[] resId;
    String[] DateName;

    public CustomAdapter(Context context, String[] NameTopic, String[] DateName, int[] resId) {
        this.mContext = context;         //รับค่าจาก activity หลักมาไว้ที่ตัวเอง
        this.NameTopic = NameTopic;
        this.resId = resId;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
