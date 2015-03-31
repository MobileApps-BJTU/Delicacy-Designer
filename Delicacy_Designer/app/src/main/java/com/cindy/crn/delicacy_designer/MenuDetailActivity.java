package com.cindy.crn.delicacy_designer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class MenuDetailActivity extends Activity {

    private MyListView list, steplist;
    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_detail);

        list = (MyListView) findViewById(R.id.menuitem);
        share = (Button) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuDetailActivity.this, qrActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        String food[] = this.getResources().getStringArray(R.array.food);
        String weight[] = this.getResources().getStringArray(R.array.weight);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("food", this.getResources().getString(R.string.materials));
        listItem.add(map);

        for (int i = 0; i < food.length; i++) {
            map = new HashMap<String, Object>();
            map.put("food", food[i]);
            map.put("weight", weight[i]);
            listItem.add(map);
        }

        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,
                R.layout.menudetailitem,
                new String[]{"food", "weight"},
                new int[]{R.id.item_name, R.id.item_weight});

        list.setAdapter(listItemAdapter);

        steplist = (MyListView) findViewById(R.id.stepitem);

        ArrayList<HashMap<String, Object>> steplistItem = new ArrayList<HashMap<String, Object>>();

        String step[] = this.getResources().getStringArray(R.array.steps);

        map = new HashMap<String, Object>();
        map.put("no", this.getResources().getString(R.string.sweet) + this.getResources().getString(R.string.steps));
        steplistItem.add(map);

        for (int i = 0; i < step.length; i++) {
            map = new HashMap<String, Object>();
            map.put("no", i + 1);
            map.put("text", step[i]);
            if (i == 2) {
                map.put("image", R.drawable.step);
            }
            steplistItem.add(map);
        }

        SimpleAdapter steplistItemAdapter = new SimpleAdapter(this, steplistItem,
                R.layout.howtomakeitem,
                new String[]{"no", "text", "image"},
                new int[]{R.id.stepnum, R.id.step_text, R.id.step_image});

        steplist.setAdapter(steplistItemAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MenuDetailActivity.this, ShareActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
