package com.example.modao.moguindext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by modao on 16/7/12.
 */
public class actIndicator  extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_indicator);
        String s=getIntent().getExtras().getString("msg");
        TextView textView = (TextView) findViewById(R.id.textmsg);
        textView.setText(s);
    }
}
