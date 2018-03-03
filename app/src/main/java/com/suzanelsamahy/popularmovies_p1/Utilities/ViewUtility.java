package com.suzanelsamahy.popularmovies_p1.Utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;

import com.suzanelsamahy.popularmovies_p1.R;

/**
 * Created by SuZan ElsaMahy on 02-Mar-18.
 */

public class ViewUtility {

    private Activity mActivity;
    public ProgressDialog mProgressDialog;

    public ViewUtility(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void showProgressDialog() {
        ProgressBar progressBar = new android.widget.ProgressBar(mActivity, null,android.R.attr.progressBarStyle);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mActivity, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mActivity);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(progressBar);
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
