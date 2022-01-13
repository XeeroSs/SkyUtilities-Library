package com.xeross.skyutilities.helpers.utils;

import com.sun.istack.internal.Nullable;
import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.utils.api.UtilsAPI;
import lombok.NonNull;
import org.apache.commons.lang.ArrayUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author XeroSs
 */
public class UtilsHandler implements UtilsAPI {

    private final Random random;
    private final SkyUtilities main;
    private final SimpleDateFormat formatDateAndTime;
    private final SimpleDateFormat formatTime;

    public UtilsHandler(SkyUtilities main) {
        this.main = main;
        this.random = new Random();
        this.formatDateAndTime = new SimpleDateFormat("dd/MM/yy HH:mm");
        this.formatTime = new SimpleDateFormat("mm:ss");
    }

    @Override
    public <T> T getRandom(T[] array) {
        int indexRandom = random.nextInt(array.length);
        return array[indexRandom];
    }

    @Override
    public <T> T getRandom(ArrayList<T> array) {
        int indexRandom = random.nextInt(array.size());
        return array.get(indexRandom);
    }

    @Override
    public int getDigitRandomBySizeList(long max) {
        return random.nextInt((int) max);
    }

    @Override
    public <T extends Enum<T>> T getEnumOrNull(Class<T> clazz, String value) {
        T object = null;
        try {
            object = Enum.valueOf(clazz, value.toUpperCase(Locale.ROOT));
        } catch (Exception ignored) {
        }
        return object;
    }

    @Override
    public <T> T getElementFromArrayOrNull(@Nullable T[] array, int index) {
        if (array == null || index > array.length || index < 0) return null;
        return array[index];
    }

    @Override
    public <T extends Enum<T>> T getNextElementAtEnum(Class<T> clazz, final T element) {
        final T[] elements = clazz.getEnumConstants();
        int index = ArrayUtils.indexOf(elements, element);
        index = index >= (elements.length - 1) ? 0 : (index + 1);
        return elements[index];
    }

    @Override
    public <E extends Enum<E>, T> HashMap<E, ArrayList<T>> getMapWithListEnumInValues(Class<E> clazz) {
        final E[] elements = clazz.getEnumConstants();
        final HashMap<E, ArrayList<T>> map = new HashMap<>();
        for (E element : elements) {
            map.put(element, new ArrayList<>());
        }
        return map;
    }

    @NonNull
    @Override
    public String getDateAndHourToString() {
        return formatDateAndTime.format(new Date());
    }

    @NonNull
    @Override
    public String getFormatTimeFromSecond(int timeQueue) {
        return formatTime.format(timeQueue * 1000);
    }
}
