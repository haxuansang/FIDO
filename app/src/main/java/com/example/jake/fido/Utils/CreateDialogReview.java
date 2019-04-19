package com.example.jake.fido.Utils;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.jake.fido.Objects.ReviewObject;
import com.example.jake.fido.R;

public class CreateDialogReview {
    private static CreateDialogReview createDialog;

    Dialog dialog;
    ReviewOnClickListener reviewOnClickListener;

    public static CreateDialogReview getInstance() {
        if (createDialog == null) {
            createDialog = new CreateDialogReview();
        }
        return createDialog;
    }
    public void makeDialogReview(Context context, final ReviewOnClickListener reviewOnClickListener){
        final ReviewObject reviewObject = new ReviewObject();
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.review_dialog);
         Button btnContinue = null;
        RatingBar ratingBar=null;
        EditText contentReview =null;
        contentReview = (EditText)dialog.findViewById(R.id.ti_review);
        btnContinue = (Button)dialog.findViewById(R.id.btn_up_review);
        ratingBar = (RatingBar)dialog.findViewById(R.id.rating_review);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        //btnChoose = (Button) dialog.findViewById(R.id.btn_continue);
        final EditText finalContentReview = contentReview;
        final RatingBar finalRatingBar = ratingBar;
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reviewOnClickListener!=null){
                    reviewObject.setContentReview(finalContentReview.getText().toString());
                    reviewObject.setNumRating(finalRatingBar.getRating());
                    reviewOnClickListener.onReviewClickListener(reviewObject);
                    dialog.dismiss();
                }
            }
        });
        ratingBar.setStepSize(1);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar,  float v, boolean b) {

            }
        });
        dialog.show();
    }
}
