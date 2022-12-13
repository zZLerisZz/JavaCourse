package ru.croc.Task18;
public class DBExceptions {
    public static class ProductAlreadyExistsException extends Exception {
        public ProductAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class NoSuchProductException extends Exception
    {
        public NoSuchProductException(String message)
        {
            super(message);
        }
    }
}
