package com.xeross.skyutilities.helpers.utils.files.api;

import java.util.Map;

/**
 * @author XeroSs
 */
public interface YamlFileAPI<E extends Enum<E> & YamlFileDataAPI> {

    Map<E, Object> get();

}
