package com.beijar.patrik.myapplication;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.ListView;
import android.widget.TextView;


public class MyDialogFragment extends DialogFragment  {

    private Question question;
    private MapsActivity mMapsActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        question = (Question) args.getSerializable("questionClass");

        mMapsActivity = (MapsActivity) getActivity();

        CharSequence[] Choices = {question.getAnswerOne(), question.getAnswerTwo(), question.getAnswerThree()};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(question.getQuestion())
                .setSingleChoiceItems(Choices, -1, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //int selectedPos = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        ListView lw = ((AlertDialog)dialog).getListView();
                        Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                        if(checkedItem.toString().equals(question.getCorrect())){
                            mMapsActivity.score = mMapsActivity.score + 10;
                            String score = Integer.toString(mMapsActivity.score);

                            TextView scoreBoard = (TextView) getActivity().findViewById(R.id.score);
                            scoreBoard.setText("Poäng: " + score);
                            //TODO: SHOW A CORRECT ANSWER MESSAGE
                        } else {
                            //TODO: SHOW A WRONG ANSWER MESSAGE
                        }
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }
}
