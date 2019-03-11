package com.farhanshahoriar.TeacherTote;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EditMarks extends AppCompatActivity {
    private TextInputLayout rollTextIL;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout banglaTextInputLayout;
    private TextInputLayout englishTextInputLayout;
    private TextInputLayout mathTextInputLayout;
    private TextView textView;
    String cls = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_marks);
        rollTextIL = (TextInputLayout) findViewById(R.id.til_student_roll);
        nameTextInputLayout = (TextInputLayout) findViewById(R.id.til_student_nickname);
        banglaTextInputLayout = (TextInputLayout) findViewById(R.id.til_marks_bangla);
        englishTextInputLayout = (TextInputLayout) findViewById(R.id.til_marks_english);
        mathTextInputLayout = (TextInputLayout) findViewById(R.id.til_marks_math);
        textView = (TextView) findViewById(R.id.tv_demo);

        Intent intent = getIntent();
        if(intent.hasExtra("Class")){
            cls = intent.getStringExtra("Class");
        }
    }

    //a function for adding marks
    public void addmarks(View view){
        int roll;
        String inputstr,csvline="";
        String bmrk,emrk,mmrk;

        inputstr = rollTextIL.getEditText().getText().toString();
        roll =Integer.parseInt(inputstr);
        csvline+=inputstr+",";
        csvline+="0,";
        inputstr = nameTextInputLayout.getEditText().getText().toString();

        csvline+=inputstr+",";

        //bangla marks
        bmrk=inputstr = banglaTextInputLayout.getEditText().getText().toString();
        if(inputstr.equals(""))inputstr="0";
        csvline+=inputstr+",";

        //english maks
        emrk=inputstr = englishTextInputLayout.getEditText().getText().toString();
        if(inputstr.equals(""))inputstr="0";
        csvline+=inputstr+",";

        //math marks
        mmrk=inputstr = mathTextInputLayout.getEditText().getText().toString();
        if(inputstr.equals(""))inputstr="0";
        csvline+=inputstr+",";

        String fileName = "resultdata"+cls+".csv";
        File file1 = new File(getApplicationContext().getFilesDir(),fileName);

        ArrayList<IndividualResult> resultArrayList = new ArrayList<>() ;
        IndividualResult individualResult = new IndividualResult();
        individualResult.setDatadata(csvline);
        boolean exits =false;
        if(file1.exists()) {
            resultArrayList = ResultUtilites.loadDataFile(file1);
            int aLsize = resultArrayList.size();

            for (int i = 0; i < aLsize; i++) {
                if (resultArrayList.get(i).oldRoll == roll) {
                    if (!individualResult.nickName.equals("")) {
                        resultArrayList.get(i).nickName = individualResult.nickName;
                    }
                    if (!bmrk.equals("")) {
                        resultArrayList.get(i).marks[0] = individualResult.marks[0];
                    }
                    if (!emrk.equals("")) {
                        resultArrayList.get(i).marks[1] = individualResult.marks[1];
                    }
                    if (!mmrk.equals("")) {
                        resultArrayList.get(i).marks[2] = individualResult.marks[2];
                    }
                    resultArrayList.get(i).updateSum();
                    exits = true;
                    break;
                }
            }
        }
        if(!exits){
            resultArrayList.add(individualResult);
        }


        try {
            PrintWriter printWriter = new PrintWriter(file1);

            for(IndividualResult result: resultArrayList){
                printWriter.println(result.getCsvLine());
            }
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        textView.setText("Result Added");
        Toast.makeText(this,"Result Added",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,DisplayResult.class);
        intent.putExtra("Class",cls);
        startActivity(intent);
        //DisplayResult
    }
}
