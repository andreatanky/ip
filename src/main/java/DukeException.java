package main.java;

public class DukeException extends Exception {
    String message;

    public DukeException(String message) {
        super(message);
    }

//    public String toString() {
//        return this.message;
//    }
}