package com.example.FrontEnd_PG4.util;

import com.example.FrontEnd_PG4.beans.Category;

/**
 * Created by Gurrala on 4/26/2015.
 */
public class ConfigUtil {
    private static Category category;

    public static Category getCategory() {
        return category;
    }

    public static void setCategory(Category category) {
        ConfigUtil.category = category;
    }
}
