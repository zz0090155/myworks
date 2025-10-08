#include <stdio.h>

int main(){
    int num;
    scanf("%d",&num);
    while(num!=0){
        int count;
        scanf("%d",&count);
        int bread[count];
        int wet[count];
        for(int i=0;i<count;i++){
            scanf("%d",&bread[i]);
            wet[i]=0;
        }
        for(int i=count-1;i>=0;i--){
            for(int j=0;j<bread[i];j++){
                if(i-j<0){
                    break;
                }
                wet[i-j]=1;
            }
        }
        for(int i=0;i<count;i++){
            printf("%d\t",wet[i]);
        }
        num--;
    }
    return 0;
}