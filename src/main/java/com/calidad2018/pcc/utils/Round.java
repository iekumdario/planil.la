package com.calidad2018.pcc.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Round {
    public static Double Round(Double value){
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        df.setRoundingMode(RoundingMode.DOWN);
        return  Double.parseDouble(df.format(value));
    }

    public static Double Round4Value(Double value){
        DecimalFormat df = new DecimalFormat("#.#####");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        df.setRoundingMode(RoundingMode.UP);
        return  Double.parseDouble(df.format(value));
    }
    public static Double Round3Value(Double value){
        DecimalFormat df = new DecimalFormat("#.###");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        df.setRoundingMode(RoundingMode.UP);
        return  Double.parseDouble(df.format(value));
    }

    public static Double Round1(Double value){
        DecimalFormat df = new DecimalFormat("#.");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        df.setRoundingMode(RoundingMode.DOWN);
        return  Double.parseDouble(df.format(value));
    }
}
