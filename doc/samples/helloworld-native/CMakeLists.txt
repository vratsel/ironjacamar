PROJECT(helloworld-native)

CMAKE_MINIMUM_REQUIRED (VERSION 2.8)
SET(CMAKE_VERBOSE_MAKEFILE 1)

SET(LIBRARY_OUTPUT_PATH build)
SET(BUILD_SHARED_LIBS ON)
SET(CMAKE_INCLUDE_CURRENT_DIR ON)

FIND_PACKAGE(Java COMPONENTS Development)
FIND_PACKAGE(JNI)

ADD_LIBRARY(HelloWorld helloworld.c)
