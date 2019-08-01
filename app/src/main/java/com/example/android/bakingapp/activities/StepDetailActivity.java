package com.example.android.bakingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.bakingapp.R;
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

import static com.example.android.bakingapp.Constants.EXTRA_STEP;

public class StepDetailActivity extends AppCompatActivity {
    private static final String TAG = StepDetailActivity.class.getSimpleName();

    private Step step;
    private TextView textViewInstructionDescription;
    private PlayerView playerView;
    private SimpleExoPlayer exoPlayer;
    private DefaultBandwidthMeter bandwidthMeter;
    private AdaptiveTrackSelection.Factory videoTrackSelectionFactory;
    private DefaultTrackSelector trackSelector;
    private DefaultDataSourceFactory dataSourceFactory;
    private ExtractorMediaSource videoSource;
    private long currentPosition;
    private boolean isLandscapeMode = false;

    public void setStep(Step aStep) {
        this.step = aStep;
        setTitle(step.getShortDescription());

        if (!isLandscapeMode) {
            textViewInstructionDescription.setText(step.getDescription());
        }


        if ((step.getVideoURL() != null) && (step.getVideoURL() != "")) {
            initVideoPlayer(Uri.parse(step.getVideoURL()));
        } else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        textViewInstructionDescription = (TextView) findViewById(R.id.textViewStepDescription);
        isLandscapeMode = (textViewInstructionDescription == null);

        playerView = (PlayerView) findViewById(R.id.playerViewStepVideo);

        if (getIntent().hasExtra(EXTRA_STEP)) {
            setStep((Step) getIntent().getSerializableExtra(EXTRA_STEP));
        }
    }

    public void initVideoPlayer(Uri videoUri) {
        if (exoPlayer == null) {

            // 1. Create a default TrackSelector
            bandwidthMeter = new DefaultBandwidthMeter();
            videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

            // 2. Create the player
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), trackSelector);

            // Bind the player to the view.
            playerView.setPlayer(exoPlayer);

            // Produces DataSource instances through which media data is loaded.
            dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(), Util.getUserAgent(getApplicationContext(), getString(R.string.app_name)), bandwidthMeter);

            // This is the MediaSource representing the media to be played.
            videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);

            // Prepare the player with the source.
            if (currentPosition != C.TIME_UNSET) {
                exoPlayer.seekTo(currentPosition);
            }
            exoPlayer.prepare(videoSource);
        }
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
