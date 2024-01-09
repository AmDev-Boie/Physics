package com.real.Classes.Handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoadLevel {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String[][] LoadLevelJSON(int StageID, int LevelID) {
        String[][] Result = gson.fromJson("td_engine/src/LevelData/Stage_" + StageID + "/Level_" + LevelID + ".json", String[][].class);
        return Result;
    }
}
