

public class Mines {

    public static void main(String[] args) {

        char command;
        char[][] grid = new char[10][11];
        boolean[][] cellStatus = new boolean[10][11];

        grid = startGame(grid); // GOOD

        do {
            command = getInput();
            switch (command) {
                case 's':
                    printGrid(grid); // GOOD
                    break;
                case 'c':
                    printGrid(checkGrid(grid)); //ALMOST
                    break;
                case 'r':
                    grid = createBoard(grid, Pep8.deci(), Pep8.deci()); //GOOD
                    break;
                case 'a':
                    printGrid(getStateGrid(checkGrid(grid), cellStatus)); //ALMOST
                    break;
                case 'd':
                    discoverGrid(grid, Pep8.deci(), Pep8.deci(), cellStatus); //ALMOST
                    break;
                case 'j':

                    break;
            }
        } while (command != 'q');
    }

    private static char[][] createBoard(char[][] grid, int seed, int prob) {
        Rnd.srnd = seed;
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                grid[j][i] = (Rnd.rnd() < prob ? '*' : '.');
            }
            grid[j][10] = '\n';
        }
        return grid;
    }

    private static void discoverGrid(char[][] grid, int x, int y, boolean[][] status) {
        if (x > 0 && x < 11 && y > 0 && y < 11) {
            status[x][y] = true;
            if (grid[x - 1][y - 1] == '*') {
                System.out.println("Boum!");
            } else {
                System.out.println(checkCell(grid, x, y));
            }
        } else {
            System.out.println("Coup invalide.");
        }
    }

    private static char[][] getInputBoard(char[][] grid, char tmp) {
        boolean test = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 10; j++) {
                if (!test) {
                    grid[i][j] = tmp;
                    test = true;
                } else {
                    grid[i][j] = Pep8.chari();
                }
            }
        }
        return grid;
    }

    private static char getInput() {
        char input;
        do {
            input = Pep8.chari();
        } while (input != 's' && input != 'q' && input != 'c' && input != 'r' &&
                input != 'a' && input != 'd' && input != 'j');
        return input;
    }

    private static void printGrid(char[][] grid) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.print(grid[i][j]);
            }
        }
    }

    private static char[][] startGame(char[][] grid) {
        char tmp = Pep8.chari();
        if (tmp == 'r') {
            grid = createBoard(grid, Pep8.deci(), Pep8.deci());
        } else {
            grid = getInputBoard(grid, tmp);
        }
        return grid;
    }

    private static int checkCell(char grid[][], int x, int y) {
        int nbProximityMine = 0;
        if (x < 9 && y < 10) {
            if (x > 0 && y > 0) {
                if (grid[x - 1][y - 1] == '*') {
                    nbProximityMine++;
                }
            }
            if (x > 0) {
                if (grid[x - 1][y] == '*') {
                    nbProximityMine++;
                }
            }
            if (x > 0) {
                if (grid[x - 1][y + 1] == '*') {
                    nbProximityMine++;
                }
            }
            if (y > 0) {
                if (grid[x][y - 1] == '*') {
                    nbProximityMine++;
                }
            }
            if (grid[x][y + 1] == '*') {
                nbProximityMine++;
            }
            if (y > 0) {
                if (grid[x + 1][y - 1] == '*') {
                    nbProximityMine++;
                }
            }
            if (grid[x + 1][y] == '*') {
                nbProximityMine++;
            }
            if (grid[x + 1][y + 1] == '*') {
                nbProximityMine++;
            }
        }
        return nbProximityMine;
    }

    private static char[][] checkGrid(char[][] grid) {
        char[][] gridAnalyse = new char[10][11];
        int val;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 10; j++) {
                val = checkCell(grid, i, j);
                if (val == 0) {
                    gridAnalyse[i][j] = '.';
                } else {
                    switch (val) {
                        case 1:
                            gridAnalyse[i][j] = '1';
                            break;
                        case 2:
                            gridAnalyse[i][j] = '2';
                            break;
                        case 3:
                            gridAnalyse[i][j] = '3';
                            break;
                        case 4:
                            gridAnalyse[i][j] = '4';
                            break;
                        case 5:
                            gridAnalyse[i][j] = '5';
                            break;
                        case 6:
                            gridAnalyse[i][j] = '6';
                            break;
                        case 7:
                            gridAnalyse[i][j] = '7';
                            break;
                        case 8:
                            gridAnalyse[i][j] = '8';
                            break;
                        default:
                            break;
                    }
                }
            }
            gridAnalyse[i][10] = '\n';
        }
        return gridAnalyse;
    }

    private static char[][] getStateGrid(char[][] stateGrid, boolean[][] status) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 10; j++) {
                if (status[i][j]) {
                    if (stateGrid[i][j] == 0) {
                        stateGrid[i][j] = '.';
                    }
                } else {
                    stateGrid[i][j] = '#';
                }
            }
            stateGrid[i][10] = '\n';
        }
        return stateGrid;
    }

}
