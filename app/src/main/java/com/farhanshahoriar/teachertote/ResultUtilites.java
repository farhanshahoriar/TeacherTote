package com.farhanshahoriar.resultcalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResultUtilites {
    public static ArrayList<IndividualResult> loadDataFile( File file1){

        Scanner fsc = null;
        try {
            fsc = new Scanner(file1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<IndividualResult> resultList = new ArrayList<>();
        String inputstr;
        int in=0;

        while (fsc.hasNext()){

            inputstr = fsc.nextLine();
            IndividualResult individualResult = new IndividualResult();
            individualResult.setDatadata(inputstr);
            resultList.add(individualResult);
        }
        fsc.close();
        return resultList;
    }

}
