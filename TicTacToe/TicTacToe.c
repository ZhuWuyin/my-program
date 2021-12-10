#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int table[][3]={{0,0,0},
                {0,0,0},
                {0,0,0}};

int tie(){
    for (int i=0; i<3; i++){
        for (int j=0; j<3; j++){
            if (table[i][j]==0){
                return 0;
            }
        }
    }
    return 1;
}

int winner(){

    int sum;

    for (int i=0; i<3; i++){
        sum=0;
        for (int j=0; j<3; j++){
            sum+=table[i][j];
        }

        if (sum==3){
            return 1;
        }
        else if (sum==12){
            return 2;
        }
    }

    for (int i=0; i<3; i++){
        sum=0;
        for (int j=0; j<3; j++){
            sum+=table[j][i];
        }

        if (sum==3){
            return 1;
        }
        else if (sum==12){
            return 2;
        }
    }

    sum=0;
    for (int i=0; i<3; i++){
        sum+=table[i][i];
    }
    if (sum==3){
        return 1;
    }
    else if (sum==12){
        return 2;
    }

    sum=0;
    for (int i=0; i<3; i++){
        sum+=table[2-i][i];
    }
    if (sum==3){
        return 1;
    }
    else if (sum==12){
        return 2;
    }

    return 0;
}

void bot(){

    int sum=0;

    for (int i=0; i<3; i++){
        sum=0;
        for (int j=0; j<3; j++){
            sum+=table[i][j];
        }
        if (sum==2){
            for (int j=0; j<3; j++){
                if (!table[i][j]){
                    table[i][j]=4;
                    return;
                }
            }
        }
    }

    for (int i=0; i<3; i++){
        sum=0;
        for (int j=0; j<3; j++){
            sum+=table[j][i];
        }
        if (sum==2){
            for (int j=0; j<3; j++){
                if (!table[j][i]){
                    table[j][i]=4;
                    return;
                }
            }
        }
    }

    sum=0;
    for (int i=0; i<3; i++){
        sum+=table[i][i];
    }
    if (sum==2){
        for (int i=0; i<3; i++){
            if (table[i][i]==0){
                table[i][i]=4;
                return;
            }
        }
    }

    sum=0;
    for (int i=0; i<3; i++){
        sum+=table[2-i][i];
    }
    if (sum==2){
        for (int i=0; i<3; i++){
            if (table[2-i][i]==0){
                table[2-i][i]=4;
                return;
            }
        }
    }

    if (table[1][1]==0){
        table[1][1]=4;
        return;
    }

    for (int i=0; i<3; i++){
        sum=0;
        for (int j=0; j<3; j++){
            sum+=table[i][j];
        }
        if (sum==8){
            for (int j=0; j<3; j++){
                if (!table[i][j]){
                    table[i][j]=4;
                    return;
                }
            }
        }
    }

    for (int i=0; i<3; i++){
        sum=0;
        for (int j=0; j<3; j++){
            sum+=table[j][i];
        }
        if (sum==8){
            for (int j=0; j<3; j++){
                if (!table[j][i]){
                    table[j][i]=4;
                    return;
                }
            }
        }
    }

    sum=0;
    for (int i=0; i<3; i++){
        sum+=table[i][i];
    }
    if (sum==8){
        for (int i=0; i<3; i++){
            if (table[i][i]==0){
                table[i][i]=4;
                return;
            }
        }
    }

    sum=0;
    for (int i=0; i<3; i++){
        sum+=table[2-i][i];
    }
    if (sum==8){
        for (int i=0; i<3; i++){
            if (table[2-i][i]==0){
                table[2-i][i]=4;
                return;
            }
        }
    }

    sum=0;
    for (int i=0; i<3; i++){
        for (int j=0; j<3; j++){
            if (!table[i][j]){
                sum++;
            }
        }
    }
    int *positionRow=malloc((size_t)sum*sizeof(int));
    int *positionCol=malloc((size_t)sum*sizeof(int));
    int counter=0;
    for (int i=0; i<3; i++){
        for (int j=0; j<3; j++){
            if (!table[i][j]){
                *(positionRow+counter)=i;
                *(positionCol+counter)=j;
                counter++;
            }
        }
    }
    srand((unsigned) time(NULL)%10);
    int index=rand()%sum;
    table[*(positionRow+index)][*(positionCol+index)]=4;
    free(positionRow);
    free(positionCol);
}

void tablePrinter(){
    printf("-------------\n");
    for (int i=0; i<3; i++){
        printf("|");
        for (int j=0; j<3; j++){
            if (table[i][j]==0){
                printf("   |");
            }
            else {
                table[i][j]==1 ? printf(" 1 |"):printf(" 2 |");
            }
        }
        printf("\n-------------\n");
    }
}

int stringCompare(char *str1, char *str2){
    for (int i=0;; i++){
        str1+=1;
        str2+=1;
        if (*str1!=*str2){
            return 0;
        }
        if (*str1=='\0' && *str2=='\0'){
            return 1;
        }
    }
    return 0;
}

void main()
{

    start:
    printf("Wellcome to Tic-Tac-Toe\n\n");
    printf("Please type the coordination and press \"Enter\" to put your chess piece.\n");
    printf("Please type x and y and both are start at 1. Input example: 1,1\n");
    printf("If the program appears some errors, press \"Ctrl+C\" to exit, or click the cross in the upper right of this window\n\n");
    tablePrinter();
    while (1){

        int x=0, y=0;
        do {
            printf("Coordination:");
            scanf("%d%*c%d", &x, &y);
            y--; x--;
        } while (x<0 || x>2 || y<0 || y>2 || table[y][x]!=0);
        table[y][x]=1;
        printf("Player:\n");
        tablePrinter();

        int isWin=winner(), isTie=tie();
        if (isWin!=0){
            printf("Winner is player %d\n", isWin);
            break;
        }
        else if (isTie==1){
            printf("Tie\n");
            break;
        }

        bot();
        printf("Bot:\n");
        tablePrinter();

        isWin=winner();
        isTie=tie();
        if (isWin!=0){
            printf("Winner is player %d\n", isWin);
            break;
        }
        else if (isTie==1){
            printf("Tie\n");
            break;
        }
    }

    printf("If you want play again, please input reset, else the program will exit\n");
    char str1[1];
    scanf("%s", str1);
    if (stringCompare(str1, "reset")){
        for (int i=0; i<9; i++){
            for (int j=0; j<3; j++){
                table[i][j]=0;
            }
        }
        printf("\n\n");
        goto start;
    }
}
