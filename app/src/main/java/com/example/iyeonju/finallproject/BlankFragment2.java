package com.example.iyeonju.finallproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {

    public BlankFragment2() {
        // Required empty public constructor
    }

    public static BlankFragment2 newInstance() {
        BlankFragment2 fragment = new BlankFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);

        final MyDBHandler dbHelper = new MyDBHandler(getActivity().getApplicationContext(), "MoneyBook.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) view.findViewById(R.id.result);
        final EditText etNumber = (EditText) view.findViewById(R.id.number);
        final EditText etItem = (EditText) view.findViewById(R.id.item);
        final EditText etPrice = (EditText) view.findViewById(R.id.price);

        // DB에 데이터 추가
        Button insert = (Button) view.findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etNumber.getText().toString();
                String item = etItem.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());

                dbHelper.insert(number, item, price);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 수정
        Button update = (Button) view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());

                dbHelper.update(item, price);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 삭제
        Button delete = (Button) view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();

                dbHelper.delete(item);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 조회
        Button select = (Button) view.findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(dbHelper.getResult());
            }
        });

        return view;

    }

}
