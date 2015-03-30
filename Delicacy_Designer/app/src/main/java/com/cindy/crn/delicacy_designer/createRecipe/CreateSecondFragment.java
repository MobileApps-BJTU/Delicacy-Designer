package com.cindy.crn.delicacy_designer.createRecipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cindy.crn.delicacy_designer.MyListView;
import com.cindy.crn.delicacy_designer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateSecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateSecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateSecondFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    private String recipeName;
    private EditText name;
    private ImageView plusMaterial;
    private MaterialAdapter materialAdapter;
    private Button addstep;
    private StepAdapter stepAdapter;
    private List<Map<String,Object>> listItems;
    private List<Map<String,Object>> stepItems;
    private MyListView materialList;
    private MyListView stepList;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CreateSecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateSecondFragment newInstance(String param1) {
        CreateSecondFragment fragment = new CreateSecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public CreateSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipeName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_second, container, false);
        name= (EditText) view.findViewById(R.id.recipeNameShow);
        name.setText(recipeName.toCharArray(),0,recipeName.length());

        plusMaterial= (ImageView) view.findViewById(R.id.plusMaterial);
        materialList= (MyListView) view.findViewById(R.id.materialList);
        stepList= (MyListView) view.findViewById(R.id.stepList);
        addstep= (Button) view.findViewById(R.id.addStep);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        listItems=new ArrayList<Map<String,Object>>();
        stepItems=new ArrayList<Map<String,Object>>();
        final Map<String,Object> map1=new HashMap<String, Object>();
        map1.put("stepId",getResources().getString(R.string.stepPrefix)+"1");
        map1.put("stepImage",R.drawable.eg);
        map1.put("instruction",getResources().getString(R.string.instructionHint));
        stepItems.add(map1);
        materialAdapter=new MaterialAdapter(CreateSecondFragment.this.getActivity(),listItems);
        stepAdapter=new StepAdapter(CreateSecondFragment.this.getActivity(),stepItems);
        materialList.setAdapter(materialAdapter);
        stepList.setAdapter(stepAdapter);
        plusMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(CreateSecondFragment.this.getActivity());
                LayoutInflater inflater = CreateSecondFragment.this.getActivity().getLayoutInflater();
                View layout=inflater.inflate(R.layout.materialdialog,null);
                final EditText materialInput= (EditText) layout.findViewById(R.id.material);
                final EditText materialWeight= (EditText) layout.findViewById(R.id.materialWeight);
                builder.setTitle(R.string.material);
                builder.setView(layout);
                builder.setPositiveButton(R.string.confirm,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("material",materialInput.getText().toString());
                        map.put("weight",materialWeight.getText().toString());
                        listItems.add(map);
                        materialAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();

            }
        });

        addstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("stepId",getResources().getString(R.string.stepPrefix)+String.valueOf(stepItems.size()+1));
                map2.put("stepImage",R.drawable.eg);
                map2.put("instruction",CreateSecondFragment.this.getActivity().getResources().getString(R.string.instructionHint));
                stepItems.add(map2);
                stepAdapter.notifyDataSetChanged();
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        public void onFragmentInteraction(Uri uri);
    }

}
