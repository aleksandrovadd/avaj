package com.daleksan;

import java.io.BufferedWriter;
import java.io.IOException;

public class LogListener {
    private static BufferedWriter buff;

    public LogListener(BufferedWriter buff){
        LogListener.buff = buff;
    }

    public static void log(String string) {
        writingInFile(string + "\n");
    }

    private static void writingInFile(String string) {
        try {
            buff.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
