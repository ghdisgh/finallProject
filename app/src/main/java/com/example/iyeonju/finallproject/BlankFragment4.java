package com.example.iyeonju.finallproject;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment4 extends Fragment implements OnMapReadyCallback {

    private TextView height, width;
    private EditText address, hedit, wedit;
    private Button mapV;

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private Geocoder geocoder;

    public BlankFragment4() {
        // Required empty public constructor
    }

    public static BlankFragment4 newInstance() {
        BlankFragment4 fragment = new BlankFragment4();
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
        View view = inflater.inflate(R.layout.fragment_blank_fragment4, container, false);

        height = (TextView) view.findViewById(R.id.Lheight);
        width = (TextView) view.findViewById(R.id.LWidth);
        hedit = (EditText) view.findViewById(R.id.editText2);
        wedit = (EditText) view.findViewById(R.id.editText3);
        address=(EditText) view.findViewById(R.id.address);

        mapV = (Button) view.findViewById(R.id.mapbutton);

        mapV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMap();
            }
        });

        mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);


        return view;
    }

    private void onMap() {
        String location = address.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {

            geocoder = new Geocoder(getActivity());
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions.position(sydney).title("원하는 위치(위도, 경도)에 마커를 표시했습니다.");

        // 마커를 생성한다.
        mMap.addMarker(makerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }

}