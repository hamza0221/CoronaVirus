package com.training.coronavirus;


import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.fragment.app.DialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DialogFragmentAdvices.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DialogFragmentAdvices#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentAdvices extends DialogFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static final String SCORE_KEY ="SCORE_KEY" ;



    // TODO: Rename and change types of parameters
     int percentage;
    ImageButton BtnClose;
    private View view;
    TextView tvAdvices;

    Record record;


    private OnFragmentInteractionListener mListener;


    public DialogFragmentAdvices() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogFragmentAdvices.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogFragmentAdvices newInstance(String param1, String param2) {
        DialogFragmentAdvices fragment = new DialogFragmentAdvices();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            percentage = getArguments() .getInt(SCORE_KEY, 0);


        }



        //setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog_theme);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.advices_fragment, container, false);
        record = new Record();
        BtnClose= (ImageButton) view.findViewById(R.id.btnClose);
        tvAdvices =(TextView) view.findViewById(R.id.tvAdvices_list);
        BtnClose.setOnClickListener(this);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> advicesList = GetAdvicesaccordingtoPercentage(percentage);

            for(int i =0; i<advicesList.size();i++){
            stringBuilder.append(" • ").append(advicesList.get(i)).append("\n\n");

        }
        tvAdvices.setText(stringBuilder);
        return view;

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClose:
               this.dismiss();


               break;








        }
    }

    ArrayList<String> GetAdvicesaccordingtoPercentage(int score){
     if (score < 50){
         return record.getLessof50percent();

     }

     else if (isBetween(score,50,80)){
         return record.getBetween50And80();

     }
     else if (isBetween(score,80,100)){
         return record.getBetween80And100();


     }
        return record.getBetween80And100();




    }

     boolean isBetween(int x, int lower, int upper) {
        return lower < x && x < upper;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStart() {
        super.onStart();
       Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }

    }
}
