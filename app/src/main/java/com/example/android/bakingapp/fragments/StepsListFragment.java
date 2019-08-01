package com.example.android.bakingapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.StepDetailActivity;
import com.example.android.bakingapp.adapters.StepsAdapter;
import com.example.android.bakingapp.entities.Recipe;
import com.example.android.bakingapp.entities.Step;

import static com.example.android.bakingapp.Constants.EXTRA_RECIPE;
import static com.example.android.bakingapp.Constants.EXTRA_STEP;

public class StepsListFragment extends Fragment implements StepsAdapter.StepsAdapterOnClickHandler {
    private static final String TAG = StepsListFragment.class.getSimpleName();

    private Recipe mRecipe;
    private StepsAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public StepsListFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecipe = (Recipe) getActivity().getIntent().getSerializableExtra(EXTRA_RECIPE);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        StepsAdapter mAdapter = new StepsAdapter(view.getContext(), mRecipe, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView = view.findViewById(R.id.recyclerViewSteps);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        return view;
    }


    @Override
    public void OnClick(Step step) {
        Intent intent = new Intent(getActivity().getApplicationContext(), StepDetailActivity.class);
        intent.putExtra(EXTRA_STEP, step);
        startActivity(intent);
    }
}
