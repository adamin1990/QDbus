package com.adamin.android.qdbus.thirdparty.avloading;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.adamin.android.qdbus.R;

/**
 * Created by Adam on 2016/5/30.
 */
public class AvloadingDialog extends AlertDialog{

    private GraduallyTextView mMessageView;
   public AvloadingDialog(Context context) {
        super(context,R.style.CustomDialog);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_avloading,null);
        mMessageView= (GraduallyTextView) view.findViewById(R.id.message);
        setView(view);

    }
    public void diaogshow(){
        this.show();
        mMessageView.startLoading();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(mMessageView!=null){
            mMessageView.stopLoading();
        }
    }

    @Override
    public void setMessage(CharSequence message) {
        mMessageView.setText(message);
    }

}
