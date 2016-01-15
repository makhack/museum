package com.example.matthieu.sidenav;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhotosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhotosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeoFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    MapView mMapView;
    private GoogleMap googleMap;
    protected int mDpi = 0;

    ArrayList<Theme> themeList;
    ArrayList<Item> itemsList;
    GridView gridview;
    private GoogleMap mMap;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Intent intent;

    public GeoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeoFragment newInstance(String param1, String param2) {
        GeoFragment fragment = new GeoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public void addMarkerToMap(double lat, double lng, String title, String snippet) {
        LatLng pos = new LatLng(lat, lng);

        googleMap.addMarker(new MarkerOptions()
                        .title(title)
                        .snippet(snippet)
                        .position(pos)
        );

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mDpi = getResources().getDisplayMetrics().densityDpi;

        }







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_geo, container, false);

        //view.findViewById(R.id.map);

        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();


        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(48.860294,2.338629)) // Position que l'on veut atteindre
                .zoom(18)             // Niveau de zoom
                .bearing(180)         // Orientation de la caméra, ici au sud
                .tilt(30)               // Inclinaison de la caméra

                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.860294, 2.338629))
                .title("Cour Carrée")
                );

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.860294, 2.337460))
                .title("Oeuvre N°1")
                .snippet("Portrait de Mona Lisa, est un tableau de l'artiste italien Léonard de Vinci")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tableau1a1))
                .anchor(0.5f,0.5f)

        );

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.860050, 2.339550))
                .title("Oeuvre N°4")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tableau4a1))
                .anchor(1f,1f)
        );

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.85960461831141, 2.338762879371643))
                .title("Oeuvre N°3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tableau3a1))
                .anchor(1f, 1f)
        );

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.85983049202817, 2.3377329111099243))
                .title("Oeuvre N°2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tableau6a1))
                .anchor(1f, 1f)
        );

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.86085396953378, 2.3358339071273804))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.louvre))
                .title("Entrée Musée du Louvre")
                .anchor(1f, 1f)
        );

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.86116453787939, 2.3379796743392944))
                .title("Oeuvre N°5")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tableau5a1))
                .anchor(1f, 1f)
        );


        return mMapView;
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}



