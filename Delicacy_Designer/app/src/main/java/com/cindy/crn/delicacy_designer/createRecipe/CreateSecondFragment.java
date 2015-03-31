package com.cindy.crn.delicacy_designer.createRecipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private Button addstep,publish;
    private StepAdapter stepAdapter;
    private List<Map<String,Object>> listItems;
    private List<Map<String,Object>> stepItems;
    private MyListView materialList;
    private MyListView stepList;
    private File mPhotoFile;
    private String mPhotoPath;
    private int position;
    private Handler mHandler;
    private TextView cancel;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CreateSecondFragment.
     */

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
        publish= (Button) view.findViewById(R.id.publish);
        cancel= (TextView) view.findViewById(R.id.create_recipe_cancel2);


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
        map1.put("stepImage",CreateSecondFragment.this.getActivity().getResources().getDrawable(R.drawable.eg));
        map1.put("instruction",getResources().getString(R.string.instructionHint));
        stepItems.add(map1);
        materialAdapter=new MaterialAdapter(CreateSecondFragment.this.getActivity(),listItems);

        mHandler=new Handler(){
            @Override
            public  void handleMessage(Message msg){
                position=msg.arg1;
                if (msg.what==2){
                    AlertDialog.Builder builder=new AlertDialog.Builder(CreateSecondFragment.this.getActivity());
                    builder.setTitle(R.string.alert);
                    builder.setPositiveButton(R.string.openFile, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                            getAlbum.setType("image/*");
                            CreateSecondFragment.this.startActivityForResult(getAlbum, 0);
                        }
                    });
                    builder.setNegativeButton(R.string.TakePhoto, new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            mPhotoPath = "mnt/sdcard/DCIM/Camera/" + getPhotoFileName();
                            mPhotoFile = new File(mPhotoPath);
                            if (!mPhotoFile.exists()) {
                                try {
                                    mPhotoFile.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    Uri.fromFile(mPhotoFile));
                            startActivityForResult(intent, 1);
                        }
                    });
                    builder.create().show();

                }
            }
        };
        stepAdapter=new StepAdapter(CreateSecondFragment.this.getActivity(),stepItems,mHandler);
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
                map2.put("stepImage",CreateSecondFragment.this.getActivity().getResources().getDrawable(R.drawable.eg));
                map2.put("instruction",CreateSecondFragment.this.getActivity().getResources().getString(R.string.instructionHint));
                stepItems.add(map2);
                stepAdapter.notifyDataSetChanged();
            }
        });

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.turnToShowList();
                }

            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.cancelRecipe();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap bm = null;
        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver = CreateSecondFragment.this.getActivity().getContentResolver();
        // 此处的用于判断接收的Activity是不是你想要的那个
        if ((requestCode == 0)&&( data.getData()!=null)) {
            try {
                Uri originalUri = data.getData(); // 获得图片的uri
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); // 显得到bitmap图片这里开始的第二部分，获取图片的路径：
                String[] proj = { MediaStore.Images.Media.DATA };
                // 好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = CreateSecondFragment.this.getActivity().managedQuery(originalUri, proj, null, null,
                        null);
                // 按我个人理解 这个是获得用户选择的图片的索引值
                int column_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                // 最后根据索引值获取图片路径
                String path = cursor.getString(column_index);
                BitmapDrawable bd = new BitmapDrawable(bm);
                stepItems.get(position).remove("stepImage");
                stepItems.get(position).put("stepImage",bd);
                stepAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                // Log.e(TAG, e.toString());
            }

        }else if(requestCode == 1){
            Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath, null);
            BitmapDrawable bd=new BitmapDrawable(bitmap);
            stepItems.get(position).remove("stepImage");
            stepItems.get(position).put("stepImage",bd);
            stepAdapter.notifyDataSetChanged();
        }


    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
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
        public void turnToShowList();
        public void cancelRecipe();
    }

}
