package com.example.android.bakingapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class StepDetailFragment extends Fragment {

    private TextView mTextViewInstructionDescription;
    private ImageView mImageViewNoVideo;
    private PlayerView mPlayerView;
    private DefaultBandwidthMeter mBandwidthMeter;
    private AdaptiveTrackSelection.Factory mVideoTrackSelectionFactory;
    private DefaultTrackSelector mTrackSelector;
    private SimpleExoPlayer mExoPlayer;
    private DefaultDataSourceFactory mDataSourceFactory;
    private ExtractorMediaSource mVideoSource;
    private long mCurrentPlayerPosition;
    private Step mStep;


    public StepDetailFragment() {
    }

    private void setUI() {
        if (mStep != null) {
            mTextViewInstructionDescription.setText(mStep.getDescription());
            if ((mStep.getVideoURL() != null) && (mStep.getVideoURL() != "")) {
                mImageViewNoVideo.setVisibility(View.GONE);
                initVideoPlayer(Uri.parse(mStep.getVideoURL()));
            } else {
                mImageViewNoVideo.setVisibility(View.VISIBLE);
                freeAndNilExoPlayer();
            }

        }
    }

    public void setStep(Step step) {
        mStep = step;
        setUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        mTextViewInstructionDescription = view.findViewById(R.id.textViewStepDescription);
        mImageViewNoVideo = view.findViewById(R.id.imageViewNoVideo);
        mPlayerView = view.findViewById(R.id.playerViewStepVideo);
        return view;
    }


    public void initVideoPlayer(Uri videoUri) {

        freeAndNilExoPlayer();

        mBandwidthMeter = new DefaultBandwidthMeter();
        mVideoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(mBandwidthMeter);
        mTrackSelector = new DefaultTrackSelector(mVideoTrackSelectionFactory);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), mTrackSelector);
        mPlayerView.setPlayer(mExoPlayer);
        mDataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), getString(R.string.app_name)), mBandwidthMeter);
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

}
