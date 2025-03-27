#include <bits/stdc++.h>
#include<vector>

using namespace std;
string reverseOnlyLetters(string s);

int main(){
        string tc[] = {"ab-cd","a-bC-dEf-ghIj","Test1ng-Leet=code-Q!","7_28]"};

        for(int i=0;i<4;i++){
        string ans = reverseOnlyLetters(tc[i]);
        cout<<ans<<",";
}
}

string reverseOnlyLetters(string s){
    int l=0,r=s.size()-1;

    while(l < r){
        while(l < r && !isalpha(s[l]))
                l++;
        while( l < r && !isalpha(s[r]))
                r++;
        if (l < r){
                char t = s[l];
                s[l] = s[r];
                s[r] = t;
                l++;
                r--;
        }
    }
    return s;
}
