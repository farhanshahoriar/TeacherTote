package com.farhanshahoriar.teachertote;

public class IndividualResult implements Comparable{
    public String nickName,fullName;
    public int oldRoll,newRoll,totalSubjects;
    public double totalMarks,averageMarks;
    public double []marks = new double[10];

    public String getCsvLine(){
        String csvLine= oldRoll+","+newRoll+","+nickName+",";
        for(int i=0;i<totalSubjects;i++){
            csvLine+=marks[i]+",";
        }
        return csvLine;
    }
    public void updateSum(){
        totalMarks=0;
        for(int i=0;i<totalSubjects;i++){
            totalMarks+=marks[i];
        }
    }
    public void setDatadata(String str) {
        int s=0,ln=str.length(),dcnt=0;
        String part;

        totalSubjects = 0;
        totalMarks = 0;
        for(int i=0;i<ln;i++) {
            if(str.charAt(i)==',') {
                part= str.substring(s, i);
                s=i+1;

                switch (dcnt){
                    case 0:
                        oldRoll = Integer.parseInt(part);
                        break;

                    case 1:
                        newRoll = Integer.parseInt(part);
                        break;
                    case 2:
                        nickName = part;
                        break;
                    default:
                        marks[dcnt-3] = Double.parseDouble(part);
                        totalSubjects++;
                        totalMarks+=marks[dcnt-3];
                }

                dcnt++;
            }
        }

    }


    @Override
    public int compareTo(Object o) {
        return (int) (((IndividualResult)o).totalMarks-totalMarks);//fix this tiny bug******
    }
}