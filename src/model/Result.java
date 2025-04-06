package model;

public record Result(boolean flg, String message) {
    public Result(boolean flg, String message){
        this.flg = flg;
        this.message = message;
    }
}
