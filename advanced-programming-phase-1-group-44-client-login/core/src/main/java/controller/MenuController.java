package controller;

import java.util.Map;

import model.Result;

public abstract class MenuController {
    abstract public Result exit();
    abstract public Result enterMenu(String name);

    public static Result makeResult(String message) {
        return new Result(Map.of("message", message));
    }
}
