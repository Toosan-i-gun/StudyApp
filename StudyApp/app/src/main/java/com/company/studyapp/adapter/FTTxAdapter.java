package com.company.studyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.company.studyapp.R;

import java.util.List;

public class FTTxAdapter extends BaseAdapter {

    private List<String> dataList;
    private Context mContext;
    private ItemClickCallBack callBack;

    public FTTxAdapter(Context context, List<String> dataList, ItemClickCallBack callBack) {
        this.dataList = dataList;
        this.mContext = context;
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fttx_data_list, null);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(dataList.get(position));
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.itemClick(position);
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView name;
    }
    public interface ItemClickCallBack {
       void itemClick(int position);
    }
}
