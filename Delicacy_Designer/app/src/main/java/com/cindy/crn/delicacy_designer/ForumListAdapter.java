package com.cindy.crn.delicacy_designer;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ForumListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局

    /* 构造函数 */
    public ForumListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    private ArrayList<HashMap<String, Object>> getDate() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		/* 为动态数组添加数据 */
        for (int i = 0; i < 30; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("book", "第" + i + "道菜谱");
            map.put("cooker", "厨师" + i);
            listItem.add(map);
        }
        return listItem;
    }

    @Override
    public int getCount() {
        // TODO 自动生成的方法存根
        return getDate().size();// 返回数组的长度
    }

    @Override
    public Object getItem(int position) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO 自动生成的方法存根
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO 自动生成的方法存根
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.forum_item, null);
            holder = new ViewHolder();
			/* 得到各个控件的对象 */
            holder.imgCookbook = (ImageView) convertView
                    .findViewById(R.id.img_book);
            holder.textBookName = (TextView) convertView.findViewById(R.id.text_book_name);
            holder.textCookerName = (TextView) convertView.findViewById(R.id.text_cooker_name);
            holder.img_praise = (ImageView) convertView
                    .findViewById(R.id.button_praise);
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
        }

		/* 设置TextView显示的内容，即我们存放在动态数组中的数据 */

        holder.textBookName.setText(getDate().get(position).get("book").toString());
        holder.textCookerName.setText(getDate().get(position).get("cooker").toString());

		/* 为Button添加点击事件 */
        holder.img_praise.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MyListViewBase", "你点击了按钮");
            }
        });
        return convertView;
    }

    /* 存放控件 */
    public final class ViewHolder {
        public ImageView imgCookbook = null;
        public TextView textBookName = null;
        public TextView textCookerName = null;
        public ImageView img_praise = null;
    }
}
