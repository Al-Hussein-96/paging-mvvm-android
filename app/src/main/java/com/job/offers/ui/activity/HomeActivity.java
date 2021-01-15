package com.job.offers.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.job.offers.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        View view1 = findViewById(R.id.item_view1);

        ((CircleImageView) view1.findViewById(R.id.image_view)).setImageResource(R.drawable.ic_build);
        ((TextView) view1.findViewById(R.id.textView_title)).setText("Looking for a job?");

        TextView textView1 = view1.findViewById(R.id.text_clickable);
        textView1.setText("Add a job request");
        textView1.setPaintFlags(textView1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        View view2 = findViewById(R.id.item_view2);

        ((CircleImageView) view2.findViewById(R.id.image_view)).setImageResource(R.drawable.ic_groups);
        ((TextView) view2.findViewById(R.id.textView_title)).setText("Looking for an Employee?");
        TextView textView2 = view2.findViewById(R.id.text_clickable);
        textView2.setText("Add a job offer");
        textView2.setPaintFlags(textView1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        view1.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }
}