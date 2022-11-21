struct a {
int x;
};


struct b {
int y;
char z;
a dd;
};


int x, y, zzz;
int test(int a, int b, int* x, bool z) {
return a + b;
}

int * foo(b* eg) {
return &(*eg).dd.x;
}

int main() {

int u = 1;
int v = u >> 23;
if ( u > 1) {
v = u + 1;
} else if (u > 0) {
 v = u - 1;
} else {
v =0 ;
}
v = v + 1;
a inst_of_a;

int sum = 0;
int a[100];
int x = 1, y = 2;
int z = x & y;

for (int a = 1; a < 2; ++a) {
for(int b = 1; b < 3; ++b)
sum += b;
sum += 2;
}

  return 0;
}



