package com.example.iyeonju.finallproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends Fragment {

    private Button urlV;

    private EditText tvURL;
    private WebView webView;

    public BlankFragment3() {
        // Required empty public constructor
    }

    public static BlankFragment3 newInstance() {
        BlankFragment3 fragment = new BlankFragment3();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);

        urlV = (Button) view.findViewById(R.id.urlbutton);
        tvURL = (EditText)view.findViewById(R.id.txtURL);
        webView = (WebView) view.findViewById(R.id.webView);

        urlV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goURL();
            }
        });

        return view;
    }

    private void goURL() {
        String url = tvURL.getText().toString();

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

}