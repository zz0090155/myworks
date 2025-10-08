#include <stdio.h>
#include <math.h>

void ResLength(char* chars){
    int len=0,count=1,flag=0,k=0;
    for(int i=0;chars[i]!='\0';i++){
        if(chars[i]==chars[i+1]){
            count++;
        }else{
            flag=i-count+1;
            if(count!=1){
            int n=0,j=1;
            while((count/pow(10,n))>=1){
                n++;
            }
            n--;
            while(n>=0){
                if((count/pow(10,n+1))>=1){
                    chars[flag+j]=(count-(int)pow(10,n+1))/((int)pow(10,n))+'0';
                }else{
                    chars[flag+j]=count/((int)pow(10,n))+'0';
                }
                n--;
                j++;
            }
            for(int m=flag+count;chars[m-1]!='\0';m++){
                if(chars[m]=='\0'){
                    chars[flag+j+k]=chars[m];
                    break;
                }
                chars[flag+j+k]=chars[m];
                k++;
            }
            i=flag+j-1;
        }
        count=1;
        flag=0;
        k=0;
        }
    }
}

int main(){
    char chars[1000];
    printf("请输入一个字符串:");
    scanf("%s",chars);
    ResLength(chars);
    for(int i=0;chars[i]!='\0';i++){
        if(chars[i+1]=='\0'){
            printf("%d",i+1);
        }
    }
    return 0;
}