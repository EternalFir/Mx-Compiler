#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef bool i1;
typedef char i8;
typedef __int32_t i32;

i8* __builtIn__malloc(i32 __size) { return malloc(__size); }

i8* __builtIn__stringAdd(i8* str1, i8* str2) {
  i8* ret = malloc(strlen(str1) + strlen(str2) + 1);
  strcpy(ret, str1);
  strcat(ret, str2);
  return ret;
}

i1 __builtIn__stringEq(i8* s1, i8* s2) { return strcmp(s1, s2) == 0; }
i1 __builtIn__stringNe(i8* s1, i8* s2) { return strcmp(s1, s2) != 0; }
i1 __builtIn__stringLt(i8* s1, i8* s2) { return strcmp(s1, s2) < 0; }
i1 __builtIn__stringLe(i8* s1, i8* s2) { return strcmp(s1, s2) <= 0; }
i1 __builtIn__stringGt(i8* s1, i8* s2) { return strcmp(s1, s2) > 0; }
i1 __builtIn__stringGe(i8* s1, i8* s2) { return strcmp(s1, s2) >= 0; }

i32 __builtIn__stringLength(i8* s) { return strlen(s); }
i8* __builtIn__substring(i8* str, i32 left, i32 right) {
  i32 len = right - left;
  i8* s = malloc(len + 1);
  memcpy(s, str + left, len);
  s[len] = '\0';
  return s;
}
i32 __builtIn__parseInt(i8* s) {
  i32 ret;
  sscanf(s, "%d", &ret);
  return ret;
}
i32 __builtIn__stringOrd(i8* s, int pos) { return s[pos]; }

void __builtIn__print(i8* s) { printf("%s", s); }
void __builtIn__println(i8* s) { printf("%s\n", s); }
void __builtIn__printInt(i32 num) { printf("%d", num); }
void __builtIn__printlnInt(i32 num) { printf("%d\n", num); }

i8* __builtIn__getString() {
  i8* s = malloc(256);
  scanf("%s", s);
  return s;
}
i32 __builtIn__getInt() {
  i32 ret;
  scanf("%d", &ret);
  return ret;
}
i8* __builtIn__toString(i32 num) {
  i8* s = malloc(16);
  sprintf(s, "%d", num);
  return s;
}
