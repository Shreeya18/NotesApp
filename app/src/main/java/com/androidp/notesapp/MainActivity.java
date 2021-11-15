package com.androidp.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText noteedit;
    private Button btnIncrese, btnReduce, btnbold, btnitalic, btnun;
    private TextView size;
    float currentsize;
    stickyNote note = new stickyNote();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Variables
        noteedit = findViewById(R.id.idEdit);
        btnIncrese = findViewById(R.id.btnIncrease);
        btnReduce = findViewById(R.id.btnReduce);
        btnbold = findViewById(R.id.idbtnbold);
        btnitalic = findViewById(R.id.idbtnitalic);
        btnun = findViewById(R.id.idbtn_un);
        size = findViewById(R.id.TVsize);
        currentsize = noteedit.getTextSize();
        size.setText(""+currentsize);

        //On Click Listener for Increase Button
        btnIncrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size.setText("" + currentsize);
                currentsize++;
                noteedit.setTextSize(currentsize);
            }
        });

        //On Click listener for decrease Button
        btnReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size.setText(""+ currentsize);
                currentsize--;
                noteedit.setTextSize(currentsize);
            }
        });


        //On click listener for making text bold button
        btnbold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnitalic.setTextColor(getResources().getColor(R.color.white));
                btnitalic.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteedit.getTypeface().isBold()){
                    noteedit.setTypeface(Typeface.DEFAULT);
                    btnbold.setTextColor(getResources().getColor(R.color.white));
                    btnbold.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                else{
                    btnbold.setTextColor(getResources().getColor(R.color.purple_200));
                    btnbold.setBackgroundColor(getResources().getColor(R.color.white));
                    noteedit.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }
        });

        //On click listener for making text Italic button
        btnitalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnbold.setTextColor(getResources().getColor(R.color.white));
                btnbold.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteedit.getTypeface().isItalic()){
                    noteedit.setTypeface(Typeface.DEFAULT);
                    btnitalic.setTextColor(getResources().getColor(R.color.white));
                    btnbold.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                else{
                    btnitalic.setTextColor(getResources().getColor(R.color.purple_200));
                    btnitalic.setBackgroundColor(getResources().getColor(R.color.white));
                    noteedit.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }

            }
        });

        //On click listener for making text Underlined button
        btnun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteedit.getPaintFlags() == 8){
                    btnun.setTextColor(getResources().getColor(R.color.white));
                    btnun.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteedit.setPaintFlags(noteedit.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                }
                else{
                    btnun.setTextColor(getResources().getColor(R.color.purple_200));
                    btnun.setBackgroundColor(getResources().getColor(R.color.white));
                    noteedit.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });


    }


    public void savebtn(View view) {
        note.setStick(noteedit.getText().toString(),this);
        updateWidget();
        Toast.makeText(this,"Note has been Updated",Toast.LENGTH_SHORT).show();

    }
    private void updateWidget(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(),R.layout.widget_layout);
        ComponentName componentName = new ComponentName(this, AppWidget.class);
        remoteViews.setTextViewText(R.id.widgetTV,noteedit.getText().toString());
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }
}