package com.cindy.crn.delicacy_designer.chat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cindy.crn.delicacy_designer.R;

public class ChatActivity extends Activity implements OnClickListener {
    /**
     * Called when the activity is first created.
     */

    private Button mBtnSend;
    private Button mBtnBack;
    private EditText mEditTextContent;
    private TextView name;
    private ListView mListView;
    private ChatMsgViewAdapter mAdapter;
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();
    String name1 = "bjtu";
    String name2 = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chat_layout);
        Intent i = getIntent();
        name2 = i.getStringExtra("name").toString();
        //����activityʱ���Զ����������
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();

        initData();


        name.setText(i.getStringExtra("name"));
    }


    public void initView() {
        name = (TextView) findViewById(R.id.chatname);
        mListView = (ListView) findViewById(R.id.listview);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);

        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);

    }

    private String[] msgArray = new String[]{"hello", "hello", "how are you", "i am fine,thank you,and you?", "i am fine,too",};

    private String[] dataArray = new String[]{"2012-09-01 18:00", "2012-09-01 18:10",
            "2012-09-01 18:11", "2012-09-01 18:20",
            "2012-09-01 18:30", "2012-09-01 18:35",
            "2012-09-01 18:40", "2012-09-01 18:50"};
    private final static int COUNT = 5;

    public void initData() {
        for (int i = 0; i < COUNT; i++) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(dataArray[i]);
            if (i % 2 == 0) {
                entity.setName(name2);
                entity.setMsgType(true);
            } else {
                entity.setName(name1);
                entity.setMsgType(false);
            }

            entity.setText(msgArray[i]);
            mDataArrays.add(entity);
        }

        mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);

    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_send:
                send();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void send() {
        String contString = mEditTextContent.getText().toString();
        if (contString.length() > 0) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(getDate());
            entity.setName(name1);
            entity.setMsgType(false);
            entity.setText(contString);

            mDataArrays.add(entity);
            mAdapter.notifyDataSetChanged();

            mEditTextContent.setText("");

            mListView.setSelection(mListView.getCount() - 1);
        }
    }

    private String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));


        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);


        return sbBuffer.toString();
    }


}