#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>

char* _toy_malloc(int mallocSize) {
  return (char*) malloc(mallocSize);
}

void print(char *str) {
  printf("%s", str);
}

void println(char *str) {
  printf("%s\n", str);
}

void printInt(int n) {
  printf("%d", n);
}

void printlnInt(int n) {
  printf("%d\n", n);
}

char* getString() {
  char *newStr = (char *) malloc(sizeof(char) * 1024);
  scanf("%s", newStr);
  return newStr;
}

int getInt() {
  int x;
  scanf("%d", &x);
  return x;
}

char* toString(int i) {
  char *str = malloc(sizeof(char) * 13);
  sprintf(str, "%d", i);
  return str;
}

char* _str_add(char *s1, char *s2) {
  char *ret = malloc(sizeof(char) * (strlen(s1) + strlen(s2) + 1));
  strcpy(ret, s1);
  strcat(ret, s2);
  return ret;
}

bool _str_eq(char *s1, char *s2) {
  return !strcmp(s1, s2);
}

bool _str_neq(char *s1, char *s2) {
  return strcmp(s1, s2) != 0;
}

bool _str_slt(char *s1, char *s2) {
  return strcmp(s1, s2) < 0;
}

bool _str_sle(char *s1, char *s2) {
  return strcmp(s1, s2) <= 0;
}

bool _str_sgt(char *s1, char *s2) {
  return strcmp(s1, s2) > 0;
}

bool _str_sge(char *s1, char *s2) {
  return strcmp(s1, s2) >= 0;
}

int _str_length(char *str) {
  return strlen(str);
}

char* _str_substring(char *str, int left, int right) {
  char *ret = malloc(sizeof(char) * (right - left + 1));
  memcpy(ret, str + left, right - left);
  return ret;
}

int _str_parseInt(char *str) {
  int ret;
  sscanf(str, "%d", &ret);
  return ret;
}

int _str_ord(char *str, int pos) {
  return str[pos];
}
