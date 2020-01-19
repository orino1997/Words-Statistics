package com.javarush;

public class FileEmptyException extends Throwable {
    public FileEmptyException(String message) {
        super(message);
    }
}
