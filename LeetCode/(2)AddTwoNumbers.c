#include <stdio.h>
#include <stdlib.h>

//Definition for singly-linked list.
struct ListNode {
    int val;
    struct ListNode *next;
};

// Iteration Method
struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
    struct ListNode *interim=(struct ListNode*)malloc(sizeof(struct ListNode));
    interim->val=0;
    interim->next=NULL;
    struct ListNode *result=interim;
    while (l1 || l2){
        int sum=(l1 ? l1->val:0)+(l2 ? l2->val:0);
        l1=(l1 ? l1->next:NULL);
        l2=(l2 ? l2->next:NULL);
        interim->val+=sum;
        if (l1 || l2 || interim->val>9){
            if (!interim->next){
                interim->next=(struct ListNode*)malloc(sizeof(struct ListNode));
                interim->next->val=0;
                interim->next->next=NULL;
            }
            if (interim->val>9){
                interim->next->val+=interim->val/10;
                interim->val%=10;
            }
            interim=interim->next;
        }
    }
    return result;
}

// Recursion Method
void calculate(int sum, struct ListNode *interim){
    if (sum>9){
        if (interim->next==NULL){
            interim->next=(struct ListNode*)malloc(sizeof(struct ListNode));
            interim->next->val=sum/=10;
            interim->next->next=NULL;
        }
        else {
            interim->next->val+=sum/=10;
        }
        interim->val%=10;
        calculate(sum, interim->next);
    }
}

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
    struct ListNode *interim=(struct ListNode*)malloc(sizeof(struct ListNode));
    interim->val=0;
    interim->next=NULL;
    struct ListNode *result=interim;
    while (l1 || l2){
        int sum=(l1 ? l1->val:0)+(l2 ? l2->val:0);
        l1=(l1 ? l1->next:NULL);
        l2=(l2 ? l2->next:NULL);
        interim->val+=sum;
        calculate(interim->val, interim);
        if (!interim->next){
            if (l1 || l2){
                interim->next=(struct ListNode*)malloc(sizeof(struct ListNode));
                interim->next->val=0;
                interim->next->next=NULL;
                interim=interim->next;
            }
            else {
                interim->next=NULL;
            }
        }
        else {
            interim=interim->next;
        }
    }
    return result;
}