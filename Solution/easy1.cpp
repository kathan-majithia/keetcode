#include<bits/stdc++.h>

using namespace std;

int addDigits(int num);

int main(){
        int nums[] = {38,0,123,999,45,88};

        for(int i=0;i<6;i++){
                int ans = addDigits(nums[i]);
                cout<<ans;
                cout<<",";
        }

}
int addDigits(int num) {
    int sum = 0;

    while (num > 0) {
        sum += num % 10;
        num /= 10;
        if (sum > 10){
        num = sum;
        sum = 0;
		}
    }
    return sum;
}
