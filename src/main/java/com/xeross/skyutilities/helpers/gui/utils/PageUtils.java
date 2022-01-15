package com.xeross.skyutilities.helpers.gui.utils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PageUtils {
    
    public static <T> List<T> getPageItems(List<T> itemStackList, int page, int spaces) {
        int upperBound = (page * spaces);
        int lowerBound = (upperBound - spaces);
        
        ArrayList<T> newItemStackList = new ArrayList<>();
        
        for (int i = lowerBound; i < upperBound; i++) {
            try {
                newItemStackList.add(itemStackList.get(i));
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        
        return newItemStackList;
    }
    
    public static <T> Boolean isPageValid(int sizeList, int page, int spaces) {
        if (page <= 0) return false;
        int upperBound = (page * spaces);
        int lowerBound = (upperBound - spaces);
        return sizeList > lowerBound;
    }
    
    public static <T> Boolean isPageValid(long sizeList, int page, int spaces) {
        if (page <= 0) return false;
        int upperBound = (page * spaces);
        int lowerBound = (upperBound - spaces);
        return sizeList > lowerBound;
    }
    
    public static <T> int getPage(int sizeList, int max) {
        double page = ((double) sizeList / max);
        int pageToInt = ((int) page);
        return page > pageToInt ? (pageToInt + 1) : pageToInt;
    }
}
