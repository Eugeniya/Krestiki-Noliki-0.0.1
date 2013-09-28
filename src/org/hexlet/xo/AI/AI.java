package org.hexlet.xo.AI;
import org.hexlet.xo.exception.*;
import org.hexlet.xo.field.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Date: 09.09.13
 * Time: 14:25
 * Класс, реализуюший логику работы ИИ
 */
public class AI {
    private Difficulty difficulty;
    Random random;
    private final CellState figure;     //AI's figure
    private final int WIN = 5;
    private final int LOSE = -5;
    private final int DRAW = 2;
    private final int NONE = 0;
    private final int DEPTH = 3;        //глубина обхода


    public AI(CellState figure)
    {
        this(Difficulty.EASY, figure);
    }

    /**
     * Initializing constructor
     *
     * @param difficulty         - level of game difficulty
     */
    public AI(Difficulty difficulty, CellState figure) {
        this.difficulty = difficulty;
        this.figure = figure;
        random = new Random();
    }

    public CellState getFigure() {
        return figure;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Calculating next step from AI
     *
     * @param gameField           - current game field  statement
     * ..@param calculatingMoveSign - char, indicates the empty place of game field
     * @return - move from AI
     * @throws AIException - exception of wrong difficulty level
     */
    public CellInfo calculatingMove(Field gameField) throws AIException {
        switch (difficulty) {
            case EASY:
                return getEasyMove(gameField);
            case MEDIUM:
                return getMediumMove();
            case HARD:
                return getHardMove();
            default:
                throw new AIException("Incorrect difficulty level");
        }
    }

    /**
     * Calculating move for easy game difficulty
     *
     * @return -  move from AI
     */
    private CellInfo getEasyMove(Field gameField) {
        //если первый ход компьютера, то ходим в центр или левый верхний угол
        if(gameField.getEmptyCellsCount() == gameField.SIZE * gameField.SIZE
                || gameField.getEmptyCellsCount() == gameField.SIZE * gameField.SIZE - 1)
        {
            try {
                if (gameField.getCell(1, 1).figure == CellState.EMPTY)
                    return new CellInfo(1, 1, figure);
                else
                    return new CellInfo(0, 0, figure);
            } catch (InvalidCellCoordinatesException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        int tempScore;
        int countOfEmtyCells = gameField.getEmptyCellsCount();
        Field tempField;

        //получаем всех потомков для текущего сотояния, записываем вес каждого в массив result
        ArrayList<Integer> result = new ArrayList<Integer>(countOfEmtyCells);
        for(int i = 1; i <= countOfEmtyCells; i++)
        {
            try {
                tempField = gameField.clone();
                tempField = children(tempField, figure, i);
                tempScore = minimax(tempField, switchFigure(figure), 1);
                result.add(tempScore);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        //в полученном массиве весов ищем наилучший вариант (WIN DRAW NONE),если все ходы приволят
        //только к поражению ходим в случайную клетку
        int number = result.indexOf(WIN);   // порядковый номер пустой ячейки для победы
        if(number == -1)
        {
            number = result.indexOf(DRAW);  //ничья
            if(number == -1)
            {
                number = result.indexOf(NONE);
                if(number == -1)
                {
                    try {
                        int x = random.nextInt(gameField.SIZE);
                        int y = random.nextInt(gameField.SIZE);
                        while(gameField.getCell(x, y).figure != CellState.EMPTY) {
                            x = random.nextInt(gameField.SIZE);
                            y = random.nextInt(gameField.SIZE);
                        }
                        return new CellInfo(x, y, figure);
                    } catch (InvalidCellCoordinatesException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }

        //по номеру ячейки ищем ее координаты
        int numberCell = -1;
        for(int i = 0; i < gameField.SIZE; i++)
            for(int j = 0 ; j < gameField.SIZE; j++)
            {
                try {
                    if(gameField.getCell(j, i).figure == CellState.EMPTY)
                        numberCell++;
                    if(numberCell == number)
                        return new CellInfo(j, i, figure);
                } catch (InvalidCellCoordinatesException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }


        return null;
    }

    private int minimax(Field gameField, CellState player, int deph){
        //terminated -  can not walk more or WIN or LOSE
        if(heuristic(gameField, this.figure) == WIN)
            return WIN;

        if(heuristic(gameField, switchFigure(player)) == WIN)
            return LOSE;

        if(gameField.getEmptyCellsCount() == 0)
            return DRAW;

        if(deph == DEPTH)
            return NONE;

        int score = WIN;
        int tempScore;
        int numberOfEmtyCells = gameField.getEmptyCellsCount();
        Field tempField;
        for(int i = 1; i <= numberOfEmtyCells; i++)
        {
            try {
                tempField = gameField.clone();
                tempField = children(tempField, player, i);
                tempScore = minimax(tempField,switchFigure(player),deph + 1);
                if(tempScore < score)
                    score  = tempScore;
                if(tempScore == LOSE)
                    return LOSE;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return score;
    }

    /**
     * Calculating move for medium game difficulty
     *
     * @return -  move from AI
     */
    private CellInfo getMediumMove() {
        return null;
    }

    /**
     * Calculating move for hard game difficulty
     *
     * @return -  move from AI
     */
    private CellInfo getHardMove() {
        return null;
    }

    /**
     * вычисление победы
     * @param gameField  - игровое поле
     * @param figure -  игрок
     * @return
     */

    private int heuristic(Field gameField, CellState figure){
        int horizontalSequenceLength = 0;
        int verticalSequenceLength = 0;

        try {
            // Horizontal check & Vertical check
            int NEED_CHARACTERS_TO_WIN = gameField.SIZE;
            for (int i = 0; i < gameField.SIZE; i++) {
                for (int j = 0; j < gameField.SIZE; j++) {
                    if (gameField.getCell(i,j).figure == figure) {
                        horizontalSequenceLength++;
                    }
                    if (gameField.getCell(j, i).figure == figure) {
                        verticalSequenceLength++;
                    }
                }

                if (horizontalSequenceLength == NEED_CHARACTERS_TO_WIN ||
                        verticalSequenceLength == NEED_CHARACTERS_TO_WIN) {
                    return WIN;
                }

                horizontalSequenceLength = 0;
                verticalSequenceLength = 0;
            }

            int diagonalSequenceLength = 0;
            int diagonalSequenceLength2 = 0;

            // Diagonal check
            for (int i = 0, j = 2; i < gameField.SIZE; i++, j--) {
                if (gameField.getCell(i,j).figure == figure) {
                    diagonalSequenceLength++;
                }
                if (gameField.getCell(i,j).figure == figure) {
                    diagonalSequenceLength2++;
                }
            }

            if (diagonalSequenceLength == NEED_CHARACTERS_TO_WIN ||
                    diagonalSequenceLength2 == NEED_CHARACTERS_TO_WIN) {
                return WIN;
            }

            if(gameField.getEmptyCellsCount() == 0)
                return DRAW;

            return NONE;

        } catch (InvalidCellCoordinatesException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    /**
     * returns the next child
     * @param gameField
     * @param player
     * @param number - number of empty cell
     * @return
     */
    private Field children(Field gameField, CellState player, int number){
        int countEmptyCell = 0;     //количество найденных пустых ячеек
        for(int i = 0; i < gameField.SIZE; i++)
            for(int j = 0 ; j < gameField.SIZE; j++)
            {
                try {
                    if(gameField.getCell(j, i).figure == CellState.EMPTY)
                        countEmptyCell++;
                    if(countEmptyCell == number)
                    {
                        gameField.setCell(new CellInfo(j, i, player));
                        return gameField;
                    }

                } catch (InvalidCellCoordinatesException e) {
                    e.printStackTrace();
                }
            }
        return null;
    }

    /**
     * возвращает противоположную фигуру
     * @param currentFigure
     * @return
     */
    private CellState switchFigure(CellState currentFigure) {
        if (currentFigure == CellState.O) {
            return CellState.X;
        }
        return CellState.O;
    }

}
