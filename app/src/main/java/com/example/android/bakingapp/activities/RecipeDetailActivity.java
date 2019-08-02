package com.example.android.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.entities.Recipe;
import com.example.android.bakingapp.entities.Step;
import com.example.android.bakingapp.fragments.IngredientsListFragment;
import com.example.android.bakingapp.fragments.StepDetailFragment;
import com.example.android.bakingapp.fragments.StepsListFragment;

import static com.example.android.bakingapp.Constants.EXTRA_RECIPE;
import static com.example.android.bakingapp.Constants.EXTRA_STEP_ID;

public class RecipeDetailActivity extends AppCompatActivity implements StepsListFragment.OnStepSelectedListener {
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
    private Recipe mRecipe;
    private boolean mIsTwoPane = false;
    private StepDetailFragment mStepsDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        if ((intent != null) && (intent.hasExtra(EXTRA_RECIPE))) {
            mRecipe = (Recipe) intent.getSerializableExtra(EXTRA_RECIPE);

            setTitle(mRecipe.getName());
            mIsTwoPane = findViewById(R.id.layoutActivityTabletRecipeDetail) != null;

            if (mIsTwoPane) {
                Log.d(TAG, "onCreate: two pane layout");
                createFragmentIngredientsList();
                createFragmentStepsList();
                createFragmentStepsDetail();
            } else {
                Log.d(TAG, "onCreate: single pane layout");
                createFragmentIngredientsList();
                createFragmentStepsList();
            }

        }
    }


    private void createFragmentIngredientsList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        IngredientsListFragment ingredientsListFragment = new IngredientsListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.ingredients_list_container, ingredientsListFragment)
                .commit();

    }

    private void createFragmentStepsList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        StepsListFragment stepsListFragment = new StepsListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.steps_list_container, stepsListFragment)
                .commit();
        stepsListFragment.setOnStepSelectedListener(this);
    }

    @Override
    public void onStepSelected(Step step) {
        if ((mIsTwoPane) && (mStepsDetailFragment != null)) {
            mStepsDetailFragment.setStep(step);
        } else {
            Intent intent = new Intent(getApplicationContext(), StepDetailActivity.class);
            intent.putExtra(EXTRA_RECIPE, mRecipe);
            intent.putExtra(EXTRA_STEP_ID, step.getId());
            startActivity(intent);
        }
    }


    private void createFragmentStepsDetail() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mStepsDetailFragment = new StepDetailFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.step_detail_container, mStepsDetailFragment)
                .commit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

}
