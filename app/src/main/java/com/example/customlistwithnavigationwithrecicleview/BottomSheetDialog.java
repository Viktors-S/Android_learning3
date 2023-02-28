package com.example.customlistwithnavigationwithrecicleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Random;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    OnCalculationListener myListener;

    String[] math_symbols = {"*","+","-","%"};

    public BottomSheetDialog(OnCalculationListener listener){
        myListener=listener;
    }

    Random rn = new Random();
    int math_symbol=rn.nextInt(4);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_dialog,container,false);



        TextView tv = v.findViewById(R.id.tv_sheet);
        Button btn = v.findViewById(R.id.btn_validate);

        TextView tv_number_one = v.findViewById(R.id.tv_random_number_one);
        TextView tv_math = v.findViewById(R.id.tv_random_math);
        TextView tv_number_two = v.findViewById(R.id.tv_random_number_two);

        EditText math_solved = v.findViewById(R.id.et_math_solve);

        ImageButton ib_close_bottom_sheet = v.findViewById(R.id.ib_close_button);

        ib_close_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        tv_number_one.setText(Integer.toString(rn.nextInt(10)+1));
        tv_number_two.setText(Integer.toString(rn.nextInt(10)+1));

        math_symbol=rn.nextInt(4);

        tv_math.setText(math_symbols[math_symbol]);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result = 0;

                switch (math_symbol){
                    case 0:
                        result= Integer.parseInt(tv_number_one.getText().toString()) * Integer.parseInt(tv_number_two.getText().toString());
                        break;
                    case 1:
                        result= Integer.parseInt(tv_number_one.getText().toString()) + Integer.parseInt(tv_number_two.getText().toString());
                        break;
                    case 2:
                        result= Integer.parseInt(tv_number_one.getText().toString()) - Integer.parseInt(tv_number_two.getText().toString());
                        break;
                    case 3:
                        result= Integer.parseInt(tv_number_one.getText().toString()) % Integer.parseInt(tv_number_two.getText().toString());
                        break;

                }

                if(math_solved.getText().toString().equals(Integer.toString(result))){
                    myListener.onCalculationClicked("CORRECT");
                    dismiss();
                }else{
                    tv_number_one.setText(Integer.toString(rn.nextInt(10)+1));
                    tv_number_two.setText(Integer.toString(rn.nextInt(10)+1));
                    math_symbol=rn.nextInt(4);
                    tv_math.setText(math_symbols[math_symbol]);
                    math_solved.setText("");
                    Toast.makeText(getContext(), "Incorrect Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }



    public interface OnCalculationListener{
        void onCalculationClicked(String clicked);
    }

}
