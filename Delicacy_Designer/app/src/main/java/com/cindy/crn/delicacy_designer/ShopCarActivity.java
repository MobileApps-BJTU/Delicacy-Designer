package com.cindy.crn.delicacy_designer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


public class ShopCarActivity extends Activity {

    private ListView listView;
    private static ShopCarListViewAdapter listViewAdapter;
    private static List<Map<String, Object>> listItems;
    //	private Integer[] imgeIDs = { R.drawable.cake, R.drawable.gift,
//			R.drawable.letter, R.drawable.love, R.drawable.mouse,
//			R.drawable.music };
    private String[] goodsNames = {"XXX蛋糕店", "Cheese芝士", "牛肉"};
    private String[] foodsInfo = {"水果", "普通", "香辣"};
    private static TextView sum;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcar);

        ImageButton back = (ImageButton) findViewById(R.id.sc_back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopCarActivity.this.finish();
            }
        });

        sum = (TextView) findViewById(R.id.summoney);
        Button selectall = (Button) findViewById(R.id.selectall);

        listView = (ListView) findViewById(R.id.list_goods);
        listItems = getListItems();
        listViewAdapter = new ShopCarListViewAdapter(this, listItems); // 创建适配器
        listView.setAdapter(listViewAdapter);
        selectall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listItems = getAllListItems();
                listViewAdapter = new ShopCarListViewAdapter(ShopCarActivity.this, listItems); // 创建适配器
                listView.setAdapter(listViewAdapter);
                sum.setText("￥" + 3 * 10);
            }
        });

    }

    /**
     * 初始化商品信息
     */
    private List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < goodsNames.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            //map.put("image", imgeIDs[i]); // 图片资源
            map.put("name", goodsNames[i]); // 物品标题
            map.put("info", foodsInfo[i]); // 物品名称
            //     map.put("time", goodsDetails[i]); // 物品详情
            map.put("check", "false");
            listItems.add(map);
        }
        return listItems;
    }

    private List<Map<String, Object>> getAllListItems() {

        listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < goodsNames.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            //map.put("image", imgeIDs[i]); // 图片资源
            map.put("name", goodsNames[i]); // 物品标题
            map.put("info", foodsInfo[i]); // 物品名称
            //     map.put("time", goodsDetails[i]); // 物品详情
            map.put("check", "true");
            listItems.add(map);
        }
        return listItems;
    }


    public static void update() {
        int sum1 = 0;
        for (int i = 0; i < listItems.size(); i++) {
            sum1 += listViewAdapter.hasChecked(i) ? 1 : 0;
        }
        sum.setText("￥" + sum1 * 10);
    }

}
