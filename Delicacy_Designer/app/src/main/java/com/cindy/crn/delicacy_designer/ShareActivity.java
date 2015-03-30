package com.cindy.crn.delicacy_designer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yg on 2015/3/27.
 */
public class ShareActivity extends Activity {

    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.share_layout);

        ImageButton back = (ImageButton)findViewById(R.id.share_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareActivity.this.finish();
            }
        });

        list = (ListView) findViewById(R.id.sharelist);

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
       int head[] = {R.drawable.head,R.drawable.head1,R.drawable.head2,R.drawable.head3};
        String time[] = {"2015-03-27 20:58","2015-03-27 20:55","2015-03-27 20:51","2015-03-27 19:30"};
        String text[] = this.getResources().getStringArray(R.array.share);
        String username[] = this.getResources().getStringArray(R.array.users);

        for (int i = 0; i < head.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("head",head[i]);
            map.put("time",time[i]);
            map.put("user",username[i]);
            map.put("text",text[i]);
            if(i == 3) {
                map.put("image", R.drawable.step);
            }
//            map.put("food", food[i]);
//            map.put("weight", weight[i]);
            listItem.add(map);
        }

        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
                R.layout.share_item_layout,
                new String[]{"head","time","user","text","image"},
                new int[]{R.id.share_head,R.id.sharetime,R.id.share_username,R.id.share_text,R.id.share_image});

        list.setAdapter(listItemAdapter);
    }
}
