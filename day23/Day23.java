package day23;

import java.io.File;
import java.util.*;

import librerias.PrintingFormats;
import librerias.Tuple;

public class Day23 {
    private static final int ROUNDS = 10;
    private static int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
    private static Set<Tuple<Integer, Integer>> board = new HashSet<>();
    private static Set<Tuple<Integer, Integer>> auxNonMovedElf = new HashSet<>();
    private static Map<Tuple<Integer, Integer>, Tuple<Integer, Integer>> nextMoves = new HashMap<>();

    public static void main(String[] args) {

        // File fd = new File("day23/input_test2.txt");
        // File fd = new File("day23/input_test.txt");
        File fd = new File("day23/input.txt");

        Scanner file = null;
        try {
            file = new Scanner(fd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int line = 0;
        while (file.hasNextLine()) {
            int column = 0;
            for (char actualChar : file.nextLine().toCharArray()) {
                if (actualChar == '#') {
                    board.add(new Tuple<Integer, Integer>(line, column));
                }
                column++;
            }
            line++;
        }

        // map.add(new Tuple<Integer,Integer>(432, 665));

        auxNonMovedElf = new HashSet<>(board);


        doRounds();
        setBorders();
        // printBoard();
        // moveNorth();
        // moveSouth();
        // System.out.println(nextMoves);
        // realiceMoves();
        System.out.println(countEmpty());

        
        System.out.println(minX + " " + maxX + "\n" + minY + " " + maxY);
        
        // System.out.println(board);

        // System.out.println(auxNonMovedElf);
        
    }

    @SuppressWarnings("unused")
    private static void printBoard(){
        int minusX = 0;
        int minusY = 0;
        if(minX < 0)
        minusX = Math.abs(minX);
        if(minY < 0)
        minusY = Math.abs(minY);

        Character[][] printBoard = new Character[maxX-minX+minusX][maxY-minY+minusY];


        for(int i = 0; i < printBoard.length; i++){
            for(int j = 0; j < printBoard[i].length; j++){
                printBoard[i][j] = '.';
            }
        }

        for (Tuple<Integer, Integer> tuple : board) {
            printBoard[tuple.getFirst()+minusX][tuple.getSecond()+minusY] = '#';
        }

        PrintingFormats.printBoardHorizontal(printBoard);
    }

    private static void setBorders(){
        for (Tuple<Integer,Integer> tuple : board) {
            maxX = Math.max(maxX, tuple.getFirst());
            minX = Math.min(minX, tuple.getFirst());
            maxY = Math.max(maxY, tuple.getSecond());
            minY = Math.min(minY, tuple.getSecond());
            
        }
    }

    private static int countEmpty(){
        int count = 0;
        for(int x = minX; x <= maxX; x++){
            for(int y = minY; y <= maxY; y++){
                if(!board.contains(new Tuple<Integer, Integer>(x, y))){
                    count++;
                }
            }
        }



        return count;
    }


    private static void doRounds(){
        int round;
        for(int i = 0; i < ROUNDS; i++){
            round = i % 4;
            switch(round){
                case 0:
                    moveNorth();
                    moveSouth();
                    moveWest();
                    moveEast();
                    realiceMoves();
                    break;

                case 1:
                    moveSouth();
                    moveWest();
                    moveEast();
                    moveNorth();
                    realiceMoves();
                    break;

                case 2:
                    moveWest();
                    moveEast();
                    moveNorth();
                    moveSouth();
                    realiceMoves();
                    break;

                case 3:
                    moveEast();
                    moveNorth();
                    moveSouth();
                    moveWest();
                    realiceMoves();
                    break;
            }
        }
    }

    private static void realiceMoves(){
        for(Tuple<Integer, Integer> tuple : auxNonMovedElf){
            nextMoves.put(tuple, tuple);
        }
        auxNonMovedElf.clear();
        board.clear();
        board = new HashSet<>(nextMoves.keySet());
        nextMoves.clear();
        auxNonMovedElf = new HashSet<>(board);

    }

    private static boolean hasNearElf(Tuple<Integer, Integer> tuple){
        if(board.contains(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond()))
        || board.contains(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond()-1))
        || board.contains(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond()+1))
        || board.contains(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond()))
        || board.contains(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond()-1))
        || board.contains(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond()+1))
        || board.contains(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond()-1))
        || board.contains(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond()+1)))
        return true;

        return false;
    }

    private static void moveNorth() {
        for (Tuple<Integer, Integer> tuple : board) {
            if(!auxNonMovedElf.contains(tuple)){
                continue;
            }
            if(!hasNearElf(tuple)){
                nextMoves.put(tuple, tuple);
                auxNonMovedElf.remove(tuple);
                continue;
            }
            if (!board.contains(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond()))
                    && !board.contains(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond() - 1))
                    && !board.contains(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond() + 1))) {
                        if(!nextMoves.containsKey(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond()))){
                            nextMoves.put(new Tuple<Integer,Integer>(tuple.getFirst() - 1, tuple.getSecond()), tuple);
                            auxNonMovedElf.remove(tuple);
                        } else {
                            Tuple<Integer, Integer> aux = nextMoves.get(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond()));
                            nextMoves.remove(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond()));
                            nextMoves.put(tuple, tuple);
                            nextMoves.put(aux, aux);
                            auxNonMovedElf.remove(tuple);
                        }
                
            }
        }
    }

    private static void moveSouth() {
        for (Tuple<Integer, Integer> tuple : board) {
            if(!auxNonMovedElf.contains(tuple)){
                continue;
            }
            if(!hasNearElf(tuple)){
                nextMoves.put(tuple, tuple);
                auxNonMovedElf.remove(tuple);
                continue;
            }
            if (!board.contains(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond()))
                    && !board.contains(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond() - 1))
                    && !board.contains(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond() + 1))) {
                        if(!nextMoves.containsKey(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond()))){
                            nextMoves.put(new Tuple<Integer,Integer>(tuple.getFirst() + 1, tuple.getSecond()), tuple);
                            auxNonMovedElf.remove(tuple);
                        } else {
                            Tuple<Integer, Integer> aux = nextMoves.get(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond()));
                            nextMoves.remove(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond()));
                            nextMoves.put(tuple, tuple);
                            nextMoves.put(aux, aux);
                            auxNonMovedElf.remove(tuple);
                        }
                
            }
        }
    }

    private static void moveEast() {
        for (Tuple<Integer, Integer> tuple : board) {
            if(!auxNonMovedElf.contains(tuple)){
                continue;
            }
            if(!hasNearElf(tuple)){
                nextMoves.put(tuple, tuple);
                auxNonMovedElf.remove(tuple);
                continue;
            }
            if (!board.contains(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond() + 1))
                    && !board.contains(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond() + 1))
                    && !board.contains(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond() + 1))) {
                        if(!nextMoves.containsKey(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond()+1))){
                            nextMoves.put(new Tuple<Integer,Integer>(tuple.getFirst(), tuple.getSecond()+1), tuple);
                            auxNonMovedElf.remove(tuple);
                        } else {
                            Tuple<Integer, Integer> aux = nextMoves.get(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond()+1));
                            nextMoves.remove(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond()+1));
                            nextMoves.put(tuple, tuple);
                            nextMoves.put(aux, aux);
                            auxNonMovedElf.remove(tuple);
                        }
                
            }
        }
    }


    private static void moveWest() {
        for (Tuple<Integer, Integer> tuple : board) {
            if(!auxNonMovedElf.contains(tuple)){
                continue;
            }
            if(!hasNearElf(tuple)){
                nextMoves.put(tuple, tuple);
                auxNonMovedElf.remove(tuple);
                continue;
            }
            if (!board.contains(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond() - 1))
                    && !board.contains(new Tuple<Integer, Integer>(tuple.getFirst() + 1, tuple.getSecond() - 1))
                    && !board.contains(new Tuple<Integer, Integer>(tuple.getFirst() - 1, tuple.getSecond() - 1))) {
                        if(!nextMoves.containsKey(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond()-1))){
                            nextMoves.put(new Tuple<Integer,Integer>(tuple.getFirst(), tuple.getSecond()-1), tuple);
                            auxNonMovedElf.remove(tuple);
                        } else {
                            Tuple<Integer, Integer> aux = nextMoves.get(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond()-1));
                            nextMoves.remove(new Tuple<Integer, Integer>(tuple.getFirst(), tuple.getSecond()-1));
                            nextMoves.put(tuple, tuple);
                            nextMoves.put(aux, aux);
                            auxNonMovedElf.remove(tuple);
                        }
                
            }
        }
    }

}
