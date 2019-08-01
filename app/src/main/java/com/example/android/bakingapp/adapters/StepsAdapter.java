package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.entities.Recipe;
import com.example.android.bakingapp.entities.Step;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private static final String TAG = StepsAdapter.class.getSimpleName();
    private final Context mContext;
    private final Recipe mRecipe;
    private final StepsAdapterOnClickHandler mOnClickHandler;

    public StepsAdapter(Context context, Recipe recipe, StepsAdapterOnClickHandler onClickHandler) {
        mContext = context;
        mRecipe = recipe;
        mOnClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_steps, null);
        return new StepsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.ViewHolder holder, int position) {
        Step step = mRecipe.steps.get(position);
        holder.textViewStepShortDescription.setText(step.getShortDescription());
//        if ((step.getVideoURL() == null) || (step.getVideoURL() == ""))
//            holder.imageViewShowVideo.setVisibility(View.INVISIBLE);
//        else
//            holder.imageViewShowVideo.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return mRecipe.steps.size();
    }

    public interface StepsAdapterOnClickHandler {
        void OnClick(Step step);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStepShortDescription = itemView.findViewById(R.id.textViewStepShortDescription);
        ImageView imageViewShowVideo = itemView.findViewById(R.id.imageViewStepShowDetail);


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Step step = mRecipe.getSteps().get(pos);
            mOnClickHandler.OnClick(step);

        }
    }
}
