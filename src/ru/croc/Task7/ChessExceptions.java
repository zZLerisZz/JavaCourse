package ru.croc.Task7;

public final class ChessExceptions {
    public static class IllegalPositionException extends Exception {
        public IllegalPositionException(String message)
        {
            super(message);
        }
    }
    public static class IllegalMoveException extends Exception {
        public IllegalMoveException(Chess prevPos,Chess curPos)
        {
            super("Конь так не ходит: " + prevPos + " -> "+ curPos);
        }
    }
}
