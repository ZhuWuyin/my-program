#include <stdio.h>

// Definition for a binary tree node.
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

int countNodes(struct TreeNode *root){
    int interim=1;
    Count(root, &interim);
    return interim;
}

void Count(struct TreeNode *root, int *counter){
    if (root==NULL){
        (*counter)=0;
        return;
    }
    if ((*root).left!=NULL){
        (*counter)+=1;
        Count((*root).left, counter);
    }
    if ((*root).right!=NULL){
        (*counter)+=1;
        Count((*root).right, counter);
    }
}