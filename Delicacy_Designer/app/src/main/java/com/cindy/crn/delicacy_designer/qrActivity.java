package com.cindy.crn.delicacy_designer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.cindy.crn.delicacy_designer.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;


public class qrActivity extends Activity {

    final static int QR_WIDTH = 600, QR_HEIGHT = 600;
    String content;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_layout);
        content = this.getResources().getString(R.string.sweet).toString();
        iv = (ImageView) findViewById(R.id.qrcode);
        iv.setBackgroundResource(R.drawable.erweima);
    }


}
