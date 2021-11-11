#include <stdio.h>

bool find(int **table, int row, int col, int maxRow, int maxCol){
    for (int i=0; i<maxCol; i++){
        if (table[row][i]==1 && i!=col){
            return true;
        }
    }
    for (int i=0; i<maxRow; i++){
        if (table[i][col]==1 && i!=row){
            return true;
        }
    }
    return false;
}

int countServers(int **grid, int gridSize, int* gridColSize){
    int counter=0;
    for (int i=0; i<gridSize; i++){
        for (int j=0; j<*gridColSize; j++){
            if (grid[i][j]==1 && find(grid, i, j, gridSize, *gridColSize)){
                counter++;
            }
        }
    }
    return counter;
}
