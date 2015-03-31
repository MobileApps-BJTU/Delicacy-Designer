package com.cindy.crn.delicacy_designer;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class MarketListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局

    /* 构造函数 */
    public MarketListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    private ArrayList<HashMap<String, Object>> getDate() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		/* 为动态数组添加数据 */
        for (int i = 0; i < 30; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", "第" + i + "道菜谱");
            map.put("price", "厨师" + i);
            map.put("sale", i);
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
            convertView = mInflater.inflate(R.layout.market_item, null);
            holder = new ViewHolder();
			/* 得到各个控件的对象 */
            holder.imgMaterial = (ImageView) convertView
                    .findViewById(R.id.img_material);
            holder.textMaterialName = (TextView) convertView.findViewById(R.id.text_material_name);
            holder.textMaterialPrice = (TextView) convertView.findViewById(R.id.text_material_price);
            holder.textMaterialSale = (TextView) convertView.findViewById(R.id.text_material_sale);

            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
        }

		/* 设置TextView显示的内容，即我们存放在动态数组中的数据 */

        holder.textMaterialName.setText(getDate().get(position).get("name").toString());
        holder.textMaterialPrice.setText(getDate().get(position).get("price").toString());
        holder.textMaterialSale.setText(getDate().get(position).get("sale").toString());

        return convertView;
    }

    /* 存放控件 */
    public final class ViewHolder {
        public ImageView imgMaterial = null;
        public TextView textMaterialName = null;
        public TextView textMaterialPrice = null;
        public TextView textMaterialSale = null;
    }
}
