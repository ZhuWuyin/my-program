#include <stdio.h>

int oddCells(int m, int n, int** indices, int indicesSize, int* indicesColSize){
    int marix[m][n];
    for (int i=0; i<m; i++){
        for (int j=0; j<n; j++){
            marix[i][j]=0;
        }
    }
    for (int i=0; i<indicesSize; i++){
        for (int a=0; a<n; a++){
            marix[indices[i][0]][a]++;
        }
        for (int a=0; a<m; a++){
            marix[a][indices[i][1]]++;
        }
    }
    int counter=0;
    for (int i=0; i<m; i++){
        for (int j=0; j<n; j++){
            if (marix[i][j]%2==1){
                counter++;
            }
        }
    }
    return counter;
}