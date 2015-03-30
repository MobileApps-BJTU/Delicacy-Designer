package com.cindy.crn.delicacy_designer.createRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cindy.crn.delicacy_designer.R;

import java.util.List;
import java.util.Map;

/**
 * Created by crn on 2015/3/30.
 */
public class StepAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String,Object>> listItems;
    private LayoutInflater listContainer;

    public final class StepItem{
        public TextView stepId;
        public ImageView stepImage;
        public EditText stepInstruction;
    }

    /**
     * Constructor
     * @param context
     * @param listItems
     */
    public StepAdapter(Context context, List<Map<String, Object>> listItems){

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
        StepItem  listItemView = null;
        if (convertView==null){
            listItemView=new StepItem();
            convertView=listContainer.inflate(R.layout.stepitem,null);

            listItemView.stepId= (TextView) convertView.findViewById(R.id.stepId);
            listItemView.stepImage= (ImageView) convertView.findViewById(R.id.stepImage);
            listItemView.stepInstruction= (EditText) convertView.findViewById(R.id.stepInstruction);
            convertView.setTag(listItemView);
        }else{
            listItemView = (StepItem)convertView.getTag();
        }

        listItemView.stepId.setText(listItems.get(position).get("stepId").toString());
        listItemView.stepImage.setBackgroundResource((Integer)listItems.get(position).get("stepImage"));
        listItemView.stepInstruction.setText((listItems.get(position).get("instruction").toString()));
        return convertView;
    }

    //TODO:上传图片
    public void uploadImage(){

    }
}
