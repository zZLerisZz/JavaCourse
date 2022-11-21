package ru.croc.Task7;
public class Chess extends Exception{
    private int x,y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Chess createChess(String pos) throws ChessExceptions.IllegalPositionException {
        if (pos.length() < 2) {
            throw new ChessExceptions.IllegalPositionException("Создана невозможная позиция: " + pos);
        }
        int x = pos.charAt(0) - 'a';
        int y = pos.charAt(1) - '0' - 1;
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            throw new ChessExceptions.IllegalPositionException("Создана невозможная позиция: " + pos);
        }
        return new Chess(x,y);
    }

    private Chess(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString()
    {
        char x = (char)('a' + this.x);
        String pos = "" + x + (1 + this.y);
        return pos;
    }
}
