package com.andre.tasks.constants;

import com.andre.tasks.entities.PriorityEntity;

import java.util.HashMap;
import java.util.List;

public class PriorityCacheConstants {

    public static HashMap<Integer,String> mPriorityCache = new HashMap<>();

    private PriorityCacheConstants() {
    }


    public static void setValues(List<PriorityEntity> values) {
        for (PriorityEntity entity : values){
            mPriorityCache.put(entity.Id,entity.Description);
        }
    }

    public static String get(int id) {
        return mPriorityCache.get(id);
    }
}
