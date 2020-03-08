package by.matusevich.controller.util;

import by.matusevich.view.Printer;

public class UtilClasses {

    public static void viewOperations(String[] splitLine){

        int count = Integer.parseInt(splitLine[1]);
        if(count>0){
            Printer.print("-----------------------------------------------------------");
            Printer.print("--№----TYPE--------SUM------------------------------------");
        }
        for (int i =2,j=1;i<splitLine.length;i++,j++){
            Printer.print("  "+j+" |   "+splitLine[i+1] + ":   " + splitLine[i]);
            i++;
        }
        if(count>0) Printer.print("-----------------------------------------------------------");
    }

    public static void viewUsers(String responce,String[] splitLine){
        if(splitLine[0].equals("ok")){
            int count = Integer.parseInt(splitLine[1]);
            if(count>0){
                Printer.print("--------------------------------------------------------");
                Printer.print("--------TYPE--------LOGIN-------------------------------");
            }
            for (int i =2,j=1;i<splitLine.length;i++,j++){
                Printer.print("  "+j+" |   "+splitLine[i] + "     " + splitLine[i+1]);
                i++;
            }
            if(count>0)  Printer.print("--------------------------------------------------------");
        }else Printer.print(responce);
    }
}
