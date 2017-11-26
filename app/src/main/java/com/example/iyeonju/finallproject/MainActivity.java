package com.example.iyeonju.finallproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity {

    ImageView drawingImageView;

    TextView idView;
    EditText productBox;
    EditText quantityBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout =
                (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("스레드"));
        tabLayout.addTab(tabLayout.newTab().setText("데이터베이스"));
        tabLayout.addTab(tabLayout.newTab().setText("맵"));

        final ViewPager viewPager =
                (ViewPager) findViewById(R.id.pager);

        final PagerAdapter adapter = new Main2Activity
                (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        Thread myThread = new Thread(){

            public void run(){

                int deg=10;
                RectF rectF = new RectF(200, 200, 500, 500);
                Paint paint;
                paint = new Paint();
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(10);


                final Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                        .getDefaultDisplay().getWidth(), (int) getWindowManager()
                        .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);


                for(int i=0;i<=35;i++){
                    canvas.drawArc (rectF, 0, deg, false, paint);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            drawingImageView = (ImageView) findViewById(R.id.DrawingImageView);
                            drawingImageView.setImageBitmap(bitmap);

                        }
                    });
                    deg=deg+10;
                }
            }
        };
        myThread.start();

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        quantityBox = (EditText) findViewById(R.id.productQuantity);


    }

    public void newProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        int quantity =
                Integer.parseInt(quantityBox.getText().toString());
        Product product =
                new Product(productBox.getText().toString(), quantity);
        dbHandler.addProduct(product);
        productBox.setText("");
        quantityBox.setText("");
    }

    public void lookupProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Product product =
                dbHandler.findProduct(productBox.getText().toString());
        if (product != null) {
            idView.setText(String.valueOf(product.getID()));
            quantityBox.setText(String.valueOf(product.getQuantity()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void removeProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean result = dbHandler.deleteProduct(
                productBox.getText().toString());
        if (result)
        {
            idView.setText("Record Deleted");
            productBox.setText("");
            quantityBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }
}
