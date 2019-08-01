package com.example.android.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.entities.Recipe;
import com.example.android.bakingapp.entities.Step;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static com.example.android.bakingapp.Constants.EXTRA_RECIPE;
import static com.example.android.bakingapp.Constants.EXTRA_STEP_ID;

public class StepDetailActivity extends AppCompatActivity {
    private static final String TAG = StepDetailActivity.class.getSimpleName();

    private Recipe mRecipe;
    private Step mCurrentStep;

    private TextView mTextViewInstructionDescription;
    private PlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private DefaultBandwidthMeter mBandwidthMeter;
    private AdaptiveTrackSelection.Factory mVideoTrackSelectionFactory;
    private DefaultTrackSelector mTrackSelector;
    private DefaultDataSourceFactory mDataSourceFactory;
    private ExtractorMediaSource mVideoSource;
    private long mCurrentPlayerPosition;
    private boolean mIsLandscapeMode = false;
    private Button mButtonPreviousStep;
    private Button mButtonNextStep;
    private ImageView mImageViewNoVideo;


    private void setUI() {
        if (mCurrentStep != null) {
            setTitle(mCurrentStep.getShortDescription());
            if (!mIsLandscapeMode) {
                mTextViewInstructionDescription.setText(mCurrentStep.getDescription());
            }
            if ((mCurrentStep.getVideoURL() != null) && (mCurrentStep.getVideoURL() != "")) {
                mImageViewNoVideo.setVisibility(View.GONE);
                initVideoPlayer(Uri.parse(mCurrentStep.getVideoURL()));
            } else {
                mImageViewNoVideo.setVisibility(View.VISIBLE);
                freeAndNilExoPlayer();
            }
            mButtonPreviousStep.setEnabled(mRecipe.steps.indexOf(mCurrentStep) != 0);
            mButtonNextStep.setEnabled(mRecipe.steps.indexOf(mCurrentStep) != mRecipe.steps.size() - 1);

        }
    }

    public void setCurrentStepFromId(int id) {
        if (mRecipe != null) {
            for (Step step : mRecipe.steps)
                if (step.getId() == id) {
                    mCurrentStep = step;
                    break;
                }
            setUI();
        }
    }


    public void previous(View view) {
        if (mCurrentStep != null) {
            int idx = mRecipe.steps.indexOf(mCurrentStep);
            idx--;
            if (idx > -1) {
                mCurrentStep = mRecipe.steps.get(idx);
                setUI();
            }
        }
    }

    public void next(View view) {
        if (mCurrentStep != null) {
            int idx = mRecipe.steps.indexOf(mCurrentStep);
            idx++;
            if (idx <= mRecipe.steps.size() - 1) {
                mCurrentStep = mRecipe.steps.get(idx);
                setUI();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        mTextViewInstructionDescription = (TextView) findViewById(R.id.textViewStepDescription);
        mIsLandscapeMode = (mTextViewInstructionDescription == null);
        mButtonPreviousStep = (Button) findViewById(R.id.buttonPreviousStep);
        mButtonNextStep = (Button) findViewById(R.id.buttonNextStep);
        mImageViewNoVideo = (ImageView) findViewById(R.id.imageViewNoVideo);

        mPlayerView = (PlayerView) findViewById(R.id.playerViewStepVideo);

        if (getIntent().hasExtra(EXTRA_RECIPE)) {
            mRecipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
        }

        if (getIntent().hasExtra(EXTRA_STEP_ID)) {
            setCurrentStepFromId((int) getIntent().getIntExtra(EXTRA_STEP_ID, 0));
        }
    }

    public void initVideoPlayer(Uri videoUri) {

        freeAndNilExoPlayer();

        mBandwidthMeter = new DefaultBandwidthMeter();
        mVideoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(mBandwidthMeter);
        mTrackSelector = new DefaultTrackSelector(mVideoTrackSelectionFactory);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), mTrackSelector);
        mPlayerView.setPlayer(mExoPlayer);
        mDataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(), Util.getUserAgent(getApplicationContext(), getString(R.string.app_name)), mBandwidthMeter);
        mVideoSource = new ExtractorMediaSource.Factory(mDataSourceFactory).createMediaSource(videoUri);
        if (mCurrentPlayerPosition != C.TIME_UNSET) {
            mExoPlayer.seekTo(mCurrentPlayerPosition);
        }
        mExoPlayer.prepare(mVideoSource);
    }

    private void freeAndNilExoPlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
        mCurrentPlayerPosition = C.TIME_UNSET;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
