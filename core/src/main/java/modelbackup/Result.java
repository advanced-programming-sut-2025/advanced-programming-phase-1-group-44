package modelbackup;

import java.util.Map;

public class Result {
    private Map<String, Object> data;

    public Result() {
        // Kryo needs this
    }

    public Result(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
