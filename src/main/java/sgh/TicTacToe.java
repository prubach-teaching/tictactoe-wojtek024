package sgh;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    public enum Result {NOT_FINISHED, NO_WINNER, X_WON, O_WON}

    public static int checkRows(int[][] board) {
        for (int[] row : board) {
            if (Arrays.equals(row, new int[]{1, 1, 1})) {
                return 1;
            } else if (Arrays.equals(row, new int[]{-1, -1, -1})) {
                return -1;
            }
        }
        return 0;
    }

    public static int checkColumns(int[][] board) {
        for (int mark : new int[]{-1, 1}) {
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
                    return mark;
                }
            }
        }
        return 0;
    }

    public static int checkDiagonals(int[][] board) {
        for (int mark : new int[]{-1, 1}) {

            if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
                return mark;
            } else if (board[2][0] == mark && board[1][1] == mark && board[0][2] == mark) {
                return mark;
            }

        }
        return 0;
    }

    public static Result checkBoard(String boardFileName) {
        int[][] board = new int[3][3];
        try {
            File boardFile = new File(boardFileName);
            System.out.println(boardFile.getAbsolutePath());
            Scanner fileReader = new Scanner(boardFile);
            int rowCount = 0;
            while (fileReader.hasNextLine()) {
                String[] data = fileReader.nextLine().split(";");
                int columnCount = 0;
                for (String mark : data) {
                    if (mark.equals("x")) {
                        board[rowCount][columnCount] = 1;
                        columnCount++;
                    } else if (mark.equals("o")) {
                        board[rowCount][columnCount] = -1;
                        columnCount++;
                    } else {
                        board[rowCount][columnCount] = 0;
                        columnCount++;
                    }
                }
                rowCount++;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Exception occured");
            exception.printStackTrace();
        }


        if (checkRows(board) == 1 || checkColumns(board) == 1 || checkDiagonals(board) == 1) {
            return Result.X_WON;
        } else if (checkRows(board) == -1 || checkColumns(board) == -1 || checkDiagonals(board) == -1) {
            return Result.O_WON;
        }

        for (int[] row : board){
            for (int mark:row){
                if(mark == 0){
                    return Result.NOT_FINISHED;
                }
            }
        }

        return Result.NO_WINNER;
    }


    public static void main(String[] args) {
        Result res = checkBoard("boards/tick3.csv");
        System.out.println(res);
    }
}
