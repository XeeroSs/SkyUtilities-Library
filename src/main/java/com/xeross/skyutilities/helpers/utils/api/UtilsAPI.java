package com.xeross.skyutilities.helpers.utils.api;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author XeroSs
 */
@SuppressWarnings("unused")
public interface UtilsAPI {
    
    /**
     * @return random array element in terms according to the size of array.
     */
    <T> T getRandom(final T[] array);
    <T> T getRandom(final ArrayList<T> array);
    int getDigitRandomBySizeList(final long max);
    /**
     * @return enum element or null.
     */
    <T extends Enum<T>> T getEnumOrNull(final Class<T> clazz, final String value);
    /**
     * @return element from array. Return null if array is null or index is not correct.
     */
    <T> T getElementFromArrayOrNull(final T[] array, final int index);
    /**
     * @return next element by {}.
     */
    <T extends Enum<T>> T getNextElementAtEnum(Class<T> clazz, final T element);
    
    <E extends Enum<E>, T> HashMap<E, ArrayList<T>> getMapWithListEnumInValues(Class<E> clazz);
    
    /**
     * @return 'dd/MM/yy HH:mm'.
     */
    
    String getDateAndHourToString();
    /**
     * @return 'mm:ss'.
     */
    
    String getFormatTimeFromSecond(final int timeQueue);
}
