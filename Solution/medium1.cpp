#include <bits/stdc++.h>

using namespace std;

int arrangingCoins(int n);

int main(){
	int arr[] = {5,8,10,15,21};

	// 2,3,4,5,6	
        for(int i=0;i<5;i++){
                int ans = arrangingCoins(arr[i]);
                cout<<ans;
                cout<<",";
        }
}


int arrangingCoins(int n){
    int st = 1,total=0;

    while(1){
	total += st;
	if (total > n)
		break;
	st++;
}

	return st-1;
}
