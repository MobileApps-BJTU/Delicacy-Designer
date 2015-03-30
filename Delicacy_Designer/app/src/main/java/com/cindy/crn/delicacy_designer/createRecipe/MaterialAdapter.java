package com.cindy.crn.delicacy_designer.createRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cindy.crn.delicacy_designer.R;

import java.util.List;
import java.util.Map;

/**
 * Created by crn on 2015/3/30.
 */
public class MaterialAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String,Object>> listItems;
    private LayoutInflater listContainer;
    public final class MaterialItem{
        public TextView material;
        public TextView weight;
    }

    /**
     * Constructor
     * @param context
     * @param listItems
     */
    public MaterialAdapter(Context context, List<Map<String, Object>> listItems){

        this.context=context;
        listContainer=LayoutInflater.from(context);
        this.listItems=listItems;
    }
    @Override
    public int getCount() {
        return listItems.size();
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

        MaterialItem  listItemView = null;
        if (convertView==null){
            listItemView=new MaterialItem();
            convertView=listContainer.inflate(R.layout.materialitem,null);

            listItemView.material= (TextView) convertView.findViewById(R.id.goods);
            listItemView.weight= (TextView) convertView.findViewById(R.id.goodsweight);
            convertView.setTag(listItemView);
        }else{
            listItemView = (MaterialItem)convertView.getTag();
        }

        listItemView.material.setText((String)listItems.get(position).get("material"));
        listItemView.weight.setText((String)listItems.get(position).get("weight"));

        return convertView;
    }




}
