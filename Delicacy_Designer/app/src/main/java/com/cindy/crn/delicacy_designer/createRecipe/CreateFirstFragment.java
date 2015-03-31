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
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.cindy.crn.delicacy_designer.R;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateFirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFirstFragment extends Fragment {


    private OnFragmentInteractionListener mListener;


    private EditText recipeName;
    private ImageView coverImage;
    private TextView cancel;
    private TextView next;
    private File mPhotoFile;
    private String mPhotoPath;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateFirstFragment.
     */

    public static CreateFirstFragment newInstance() {
        CreateFirstFragment fragment = new CreateFirstFragment();
        return fragment;
    }

    public CreateFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_first, container, false);
        // Inflate the layout for this fragment
        recipeName = (EditText) view.findViewById(R.id.recipeNameInput);
        coverImage = (ImageView) view.findViewById(R.id.coverImage);
        cancel = (TextView) view.findViewById(R.id.create_recipe_cancel);
        next = (TextView) view.findViewById(R.id.create_recipe_next);
        cancel.setOnClickListener(cancelListener);

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bm = null;
        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver = CreateFirstFragment.this.getActivity().getContentResolver();
        // 此处的用于判断接收的Activity是不是你想要的那个
        if ((requestCode == 0) && (data.getData() != null)) {
            try {
                Uri originalUri = data.getData(); // 获得图片的uri
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); // 显得到bitmap图片这里开始的第二部分，获取图片的路径：
                String[] proj = {MediaStore.Images.Media.DATA};
                // 好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = CreateFirstFragment.this.getActivity().managedQuery(originalUri, proj, null, null,
                        null);
                // 按我个人理解 这个是获得用户选择的图片的索引值
                int column_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                // 最后根据索引值获取图片路径
                String path = cursor.getString(column_index);
                BitmapDrawable bd = new BitmapDrawable(bm);
                coverImage.setImageDrawable(bd);
            } catch (IOException e) {
                // Log.e(TAG, e.toString());
            }

        } else if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath, null);
            coverImage.setImageBitmap(bitmap);
        }


    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateFirstFragment.this.getActivity());
                builder.setTitle(R.string.alert);
                builder.setPositiveButton(R.string.openFile, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                        getAlbum.setType("image/*");
                        CreateFirstFragment.this.startActivityForResult(getAlbum, 0);
                    }
                });
                builder.setNegativeButton(R.string.TakePhoto, new DialogInterface.OnClickListener() {

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
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateFirstFragment.this.getActivity());
                if ((recipeName.getText().length() == 0) || (recipeName.getText().charAt(0) == ' ')) {
                    builder.setTitle(R.string.alert);
                    builder.setMessage(R.string.createAlert);
                    builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                } else {
                    if (mListener != null)
                        mListener.sendNameToFragment2(recipeName.getText().toString());
                }
            }

        });
    }

    private View.OnClickListener cancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null)
                mListener.cancelCreate();
        }
    };

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
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

        public void sendNameToFragment2(String recipeName);

        public void cancelCreate();
    }

}
