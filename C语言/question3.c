#include <stdio.h>
#include <math.h>
#define MOD 1000000007

int main() {
    int n;
    scanf("%d", &n);
    if (n < 3) {
        printf("0");
        return 0;
    }
    long long total = ((int)pow(2, n))%MOD;
    long long beautiful[n + 1];
    beautiful[0] = 1;
    beautiful[1] = 2;
    beautiful[2] = 4;
    for (int i = 3; i <= n; i++) {
        beautiful[i] = (beautiful[i-1] + beautiful[i-2]) % MOD;
    }
    long long uglyTotal=(total-beautiful[n]+MOD)%MOD;
    printf("%lld",uglyTotal);
    return 0;
}
