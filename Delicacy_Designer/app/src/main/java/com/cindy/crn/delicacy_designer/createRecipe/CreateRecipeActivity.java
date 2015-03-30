package com.cindy.crn.delicacy_designer.createRecipe;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cindy.crn.delicacy_designer.R;
import com.cindy.crn.delicacy_designer.MainActivity;

public class CreateRecipeActivity extends Activity implements CreateFirstFragment.OnFragmentInteractionListener,CreateSecondFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        CreateFirstFragment firstFragment=CreateFirstFragment.newInstance();
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.createFragmentHolder,firstFragment);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void sendNameToFragment2(String recipeName) {

        CreateSecondFragment secondFragment=CreateSecondFragment.newInstance(recipeName);
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.createFragmentHolder,secondFragment);
        transaction.commit();

    }

    @Override
    public void cancelCreate() {
        Intent intent=new Intent(CreateRecipeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
