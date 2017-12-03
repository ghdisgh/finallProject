package com.example.iyeonju.finallproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private ImageView drawingImageView;
    private Bitmap bitmap;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        drawingImageView = (ImageView) view.findViewById(R.id.DrawingImageView);

        Thread myThread = new Thread(){

            public void run(){

                int deg=10;
                RectF rectF = new RectF(200, 200, 600, 600);
                RectF rectF2 = new RectF(300, 300, 500, 500);
                Paint paint;
                paint = new Paint();
                paint.setColor(Color.parseColor("#FF8E99"));
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(10);


                bitmap = bitmap.createBitmap((int) getActivity().getWindowManager()
                        .getDefaultDisplay().getWidth(), (int) getActivity().getWindowManager()
                        .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                Canvas canvas2 = new Canvas(bitmap);


                for(int i=0;i<=35;i++){
                    canvas.drawArc (rectF, 0, deg, false, paint);
                    canvas2.drawArc (rectF2, 0, deg, false, paint);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            drawingImageView.setImageBitmap(bitmap);

                        }
                    });
                    deg=deg+10;
                }
            }
        };
        myThread.start();

        return view;
    }

}

