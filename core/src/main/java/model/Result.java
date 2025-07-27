package model;

import java.util.Map;

public class Result {
    private final Map<String, Object> data;

    public Result(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
