#include <stdio.h>

int friends[100000];
int originfriend[100000];

void begin(int n){
    for(int i=1;i<=n;i++){
        originfriend[i]=i;
        friends[i]=1;
    }
}

int findOriginalFriend(int a){
    if(originfriend[a]==a){
        return a;
    }else{
        findOriginalFriend(originfriend[a]);
    }
}

void MakeFriends(int a,int b){
   if(findOriginalFriend(a)==findOriginalFriend(b)){
    return;
   }else{
    friends[findOriginalFriend(a)]+=friends[findOriginalFriend(b)];
    friends[findOriginalFriend(b)]+=friends[findOriginalFriend(a)];
    originfriend[findOriginalFriend(b)]=findOriginalFriend(a);
   }
}

int main(){
    int n,m;
    scanf("%d %d",&n,&m);
    begin(n);
    for(int i=0;i<m;i++){
        int a,b;
        scanf("%d %d",&a,&b);
        MakeFriends(a,b);
        if(friends[findOriginalFriend(a)]==n||friends[findOriginalFriend(b)]==n){
            printf("%d",i+1);
            return 0;
        }
    }
    printf("-1");
    return 0;
}