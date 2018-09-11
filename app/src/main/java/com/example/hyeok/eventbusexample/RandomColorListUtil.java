package com.example.hyeok.eventbusexample;

import android.util.Log;

import java.util.ArrayList;

public class RandomColorListUtil {

    public static ArrayList<Integer> randomizeColorList(ArrayList<Integer> colorList){
        int listSize = colorList.size();
        int randomIndexArray[] = getRandomIndexArray(listSize);
        ArrayList<Integer> randomizedList = new ArrayList<>(listSize);

        for(int i = 0; i < listSize; i++){
            randomizedList.add(i, colorList.get(randomIndexArray[i]));
        }
        return randomizedList;
    }

    private static int[] getRandomIndexArray(int size){
        int randomIndex[] = new int[size];

        for(int i = 0; i < size; i++){
            randomIndex[i] = (int)(Math.random() * size);

            for(int j = 0; j < i; j++){
                if(randomIndex[i] == randomIndex[j]){
                    i--;
                    break;
                }
            }
        }

        return randomIndex;
    }
}
