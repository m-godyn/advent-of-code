package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class TreetopTreeHouse {

    private static final int MATRIX_SIZE = 99;

    public static void main(String[] args) {
        findVisibleTrees();
        findHighestScenicScore();
    }

    static void findHighestScenicScore() {
        int[][] trees = readInput();
        int highestScore = 0;
        for (int y = 1; y < trees.length - 1; y++) {
            for (int x = 1; x < trees[y].length - 1; x++) {
                int tree = trees[y][x];
                int top = scoreFromTop(trees[y][x], y, x, trees);
                int bot = scoreFromBottom(trees[y][x], y, x, trees);
                int left = scoreFromLeft(trees[y][x], y, x, trees);
                int right = scoreFromRight(trees[y][x], y, x, trees);
                int score4Tree = top * bot * left * right;
                if (score4Tree > highestScore) {
                    highestScore = score4Tree;
                }
            }
        }
        System.out.println(highestScore);
    }

    static void findVisibleTrees() {
        int[][] trees = readInput();
        int visibleTreesQuantity = (trees.length - 1) * 4;
        for (int i = 1; i < trees.length - 1; i++) {
            for (int j = 1; j < trees[i].length - 1; j++) {
                if (
                        isVisiblefromTop(trees[i][j], i, j, trees) ||
                                isVisiblefromBottom(trees[i][j], i, j, trees) ||
                                isVisiblefromLeft(trees[i][j], i, j, trees) ||
                                isVisiblefromRight(trees[i][j], i, j, trees)) {
                    visibleTreesQuantity++;
                }
            }
        }
        System.out.println(visibleTreesQuantity);
    }

    static int scoreFromRight(int tree, int y, int x, int[][] trees) {
        int score = 0;
        for (int i = x + 1; i < trees.length; i++) {
            score++;
            if (trees[y][i] >= tree) {
                return score;
            }
        }
        return score;
    }

    static int scoreFromLeft(int tree, int y, int x, int[][] trees) {
        int score = 0;
        for (int i = x - 1; i >= 0; i--) {
            score++;
            if (trees[y][i] >= tree) {
                return score;
            }
        }
        return score;
    }

    static int scoreFromBottom(int tree, int y, int x, int[][] trees) {
        int score = 0;
        for (int i = y + 1; i < trees.length; i++) {
            score++;
            if (trees[i][x] >= tree) {
                return score;
            }
        }
        return score;
    }

    static int scoreFromTop(int tree, int y, int x, int[][] trees) {
        int score = 0;
        for (int i = y - 1; i >= 0; i--) {
            score++;
            if (trees[i][x] >= tree) {
                return score;
            }
        }
        return score;
    }

    static boolean isVisiblefromRight(int tree, int x, int y, int[][] trees) {
        for (int i = x + 1; i < trees.length; i++) {
            if (trees[i][y] >= tree) {
                return false;
            }
        }
        return true;
    }

    static boolean isVisiblefromLeft(int tree, int x, int y, int[][] trees) {
        for (int i = x - 1; i >= 0; i--) {
            if (trees[i][y] >= tree) {
                return false;
            }
        }
        return true;
    }

    static boolean isVisiblefromBottom(int tree, int x, int y, int[][] trees) {
        for (int i = y + 1; i < trees.length; i++) {
            if (trees[x][i] >= tree) {
                return false;
            }
        }
        return true;
    }

    static boolean isVisiblefromTop(int tree, int x, int y, int[][] trees) {
        for (int i = y - 1; i >= 0; i--) {
            if (trees[x][i] >= tree) {
                return false;
            }
        }
        return true;
    }

    static int[][] readInput() {
        int[][] result = new int[MATRIX_SIZE][MATRIX_SIZE];
        int numOfLines = 0;
        try (Scanner scanner = new Scanner(new File("src/Day8/input.txt"))) {
            while (scanner.hasNextLine()) {
                char[] line = scanner.nextLine().toCharArray();
                for (int i = 0; i < line.length; i++) {
                    result[numOfLines][i] = Integer.parseInt(String.valueOf(line[i]));
                }
                numOfLines++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return new int[][]{};
        }
        System.out.println(Arrays.deepToString(result));
        return result;
    }
}
