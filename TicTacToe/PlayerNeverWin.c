#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAXROW 3
#define MAXCOL 3
#define BOT 'X'
#define PLAYER 'O'
#define INITIAL '-'
#define WIN 1

char board[MAXROW][MAXCOL];

void initializeBoard(){
    for (char row=0; row<MAXROW; row++){
        for (char col=0; col<MAXCOL; col++){
            board[row][col]=INITIAL;
        }
    }
}

void printBoard(){
    for (char row=0; row<MAXROW; row++){
        for (char col=0; col<MAXCOL; col++){
            printf("%c ", board[row][col]);
        }
        printf("\n");
    }
}

char checkFull(){
    char flag=INITIAL;
    for (char row=0; row<MAXROW; row++){
        for (char col=0; col<MAXCOL; col++){
            flag=board[row][col]-INITIAL;
            if (flag==0){
                return 0;   // Not full
            }
        }
    }
    
    // Full
    return 1;
}

char checkWin(char symbol, char location[2]){
    char row=location[0];
    char col=location[1];

    register char temp=0;
    for (char i=0; i<MAXCOL; i++){
        if (board[row][i]==symbol){
            temp++;
        }
    }
    if (temp==3){
        return WIN;
    }

    temp=0;
    for (char i=0; i<MAXROW; i++){
        if (board[i][col]==symbol){
            temp++;
        }
    }
    if (temp==3){
        return WIN;
    }

    if (board[1][1]==symbol){
        if (board[0][0]==symbol && board[2][2]==symbol){
            return WIN;
        }
        if (board[0][2]==symbol && board[2][0]==symbol){
            return WIN;
        }
    }

    // Not win
    return 0;
}

char* botMove(char* location){
    char row=location[0];
    char col=location[1];
    board[row][col]=BOT;
    printf("BotMove: %d, %d\n\n", row+1, col+1);
    return location;
}

char* randBotMove(char** emptyList){
    #define RAND (rand()+1)%4
    srand(time(NULL)%10);
    register char index=RAND;
    register char row=emptyList[index][0];
    register char col=emptyList[index][1];
    while (board[row][col]!=INITIAL) {
        index=RAND;
        row=emptyList[index][0];
        col=emptyList[index][1];
    }

    char* emptyLoc=(char*)malloc(2);
    emptyLoc[0]=row;
    emptyLoc[1]=col;
    return botMove(emptyLoc);
}

void botRow(char** selfLoc, char** playerLoc, char* selfRow, char* playerRow){
    char* emptyLoc=(char*)malloc(2);
    for (char row=0; row<MAXROW; row++){
        char player=0;
        char self=0;
        emptyLoc[0]=emptyLoc[1]=-1;

        for (char col=0; col<MAXCOL; col++){
            player+=(board[row][col]==PLAYER) ? 1:0;
            self+=(board[row][col]==BOT) ? 1:0;
            if (board[row][col]==INITIAL){
                emptyLoc[0]=row;
                emptyLoc[1]=col;
            }
        }

        if (emptyLoc[0]+1){
            if (self==2){
                *selfLoc=emptyLoc;
                *selfRow=self;
                return ;
            }
            else if(player==2){
                char* pLoc=(char*)malloc(2);
                pLoc[0]=emptyLoc[0];
                pLoc[1]=emptyLoc[1];
                *playerLoc=pLoc;
                *playerRow=player;
            }
        }
    }
}

void botCol(char** selfLoc, char** playerLoc, char* selfCol, char* playerCol){
    char* emptyLoc=(char*)malloc(2);
    for (char col=0; col<MAXROW; col++){
        char player=0;
        char self=0;
        emptyLoc[0]=emptyLoc[1]=-1;

        for (char row=0; row<MAXCOL; row++){
            player+=(board[row][col]==PLAYER) ? 1:0;
            self+=(board[row][col]==BOT) ? 1:0;
            if (board[row][col]==INITIAL){
                emptyLoc[0]=row;
                emptyLoc[1]=col;
            }
        }

        if (emptyLoc[0]+1){
            if (self==2){
                *selfLoc=emptyLoc;
                *selfCol=self;
                return ;
            }
            else if (player==2){
                char* pLoc=(char*)malloc(2);
                pLoc[0]=emptyLoc[0];
                pLoc[1]=emptyLoc[1];
                *playerLoc=pLoc;
                *playerCol=player;
            }
        }
    }
}

char* bot(){
    char* emptyLoc=(char*)malloc(2);
    // Horizontal & Vertical
    char* selfRowLoc=NULL; char* playerRowLoc=NULL; char selfRow=0; char playerRow=0;
    char* selfColLoc=NULL; char* playerColLoc=NULL; char selfCol=0; char playerCol=0;
    botRow(&selfRowLoc, &playerRowLoc, &selfRow, &playerRow);
    botCol(&selfColLoc, &playerColLoc, &selfCol, &playerCol);
    if (selfRow==2){
        return botMove(selfRowLoc);
    }
    else if (selfCol==2){
        return botMove(selfColLoc);
    }
    else if (playerRow==2){
        return botMove(playerRowLoc);
    }
    else if (playerCol==2){
        return botMove(playerColLoc);
    }

    // Diagonal
    char player=0;
    char self=0;
    emptyLoc[0]=emptyLoc[1]=-1;
    for (char i=0; i<3; i++){
        player+=(board[i][i]==PLAYER) ? 1:0;
        self+=(board[i][i]==BOT) ? 1:0;
        if (board[i][i]==INITIAL){
            emptyLoc[0]=i;
            emptyLoc[1]=i;
        }
    }
    if ((player==2 || self==2) && emptyLoc[0]+1){
        return botMove(emptyLoc);
    }

    player=0;
    self=0;
    emptyLoc[0]=emptyLoc[1]=-1;
    for (char i=0; i<3; i++){
        register char col=2/(i+1);
        player+=(board[i][col]==PLAYER) ? 1:0;
        self+=(board[i][col]==BOT) ? 1:0;
        if (board[i][col]==INITIAL){
            emptyLoc[0]=i;
            emptyLoc[1]=col;
        }
    }
    if ((player==2 || self==2) && emptyLoc[0]+1){
        return botMove(emptyLoc);
    }

    // Check Center and Corner
    emptyLoc[0]=emptyLoc[1]=-1;
    if (board[1][1]==INITIAL){  // Check center
        emptyLoc[0]=emptyLoc[1]=1;
        return botMove(emptyLoc);
    } else {
        if (board[0][0]==INITIAL || board[0][2]==INITIAL ||
            board[2][0]==INITIAL || board[2][2]==INITIAL){  // Check corner
            char** emptyList=(char**)malloc(sizeof(char*)*4);
            
            char e1[2]={0,0}; char e2[2]={0,2};
            char e3[2]={2,0}; char e4[2]={2,2};
            emptyList[0]=e1; emptyList[1]=e2;
            emptyList[2]=e3; emptyList[3]=e4;
            return randBotMove(emptyList);
        }
        else {  // Exception
            char** emptyList=(char**)malloc(sizeof(char*)*4);

            char e1[2]={0,1}; char e2[2]={1,0};
            char e3[2]={1,2}; char e4[2]={2,1};
            emptyList[0]=e1; emptyList[1]=e2;
            emptyList[2]=e3; emptyList[3]=e4;
            return randBotMove(emptyList);
        }
    }
}

char strCompare(char *str1, char *str2){
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

void main(){
    set :
    initializeBoard();
    printf("Wellcome to TicTacToe!\n");

    while (1){
        int row=0;
        int col=0;
        printf("Player (\"O\"): ");

        input:
        scanf("%d%*c%d", &row, &col);
        row--; col--;
        if (row>2 || row<0 || col>2 || col<0 || board[row][col]!=INITIAL){
            printf("Error Input, try again: ");
            goto input;
        }
        printf("\n");
        board[row][col]=PLAYER;
        printBoard();

        char location[2]={row, col};
        if (checkWin(PLAYER, location)==1){
            printf("\nPlayer Win!\n\n");
            goto check_reset;
        } else {
            if (checkFull()){
                printf("\nTie!\n\n");
                goto check_reset;
            }
            printf("\nBot (\"X\")\n");
            char* move=bot();
            printBoard();
            if (checkWin(BOT, move)){
                free(move);
                printf("\nBot Win!\n\n");
                goto check_reset;
            }
        }
    }

    check_reset:
    printf("Type \"reset\" restart the game: ");
    char* str;
    scanf("%s", str);
    if (strCompare(str, "reset")){
        goto set;
    }
}
