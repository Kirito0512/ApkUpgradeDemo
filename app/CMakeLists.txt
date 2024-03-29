# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.

add_library( # Specifies the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.cpp
        src/main/cpp/bspatch.cpp
        src/main/jni/bzip2/blocksort.c
        src/main/jni/bzip2/bzip2.c
        src/main/jni/bzip2/bzip2recover.c
        src/main/jni/bzip2/bzlib.c
        src/main/jni/bzip2/compress.c
        src/main/jni/bzip2/crctable.c
        src/main/jni/bzip2/decompress.c
        src/main/jni/bzip2/dlltest.c
        src/main/jni/bzip2/huffman.c
        src/main/jni/bzip2/mk251.c
        src/main/jni/bzip2/randtable.c
        src/main/jni/bzip2/spewG.c
        src/main/jni/bzip2/unzcrash.c)

add_library(bspatch

        SHARED

        src/main/cpp/bspatch.cpp
        src/main/jni/bzip2/blocksort.c
        src/main/jni/bzip2/bzip2.c
        src/main/jni/bzip2/bzip2recover.c
        src/main/jni/bzip2/bzlib.c
        src/main/jni/bzip2/compress.c
        src/main/jni/bzip2/crctable.c
        src/main/jni/bzip2/decompress.c
        src/main/jni/bzip2/dlltest.c
        src/main/jni/bzip2/huffman.c
        src/main/jni/bzip2/mk251.c
        src/main/jni/bzip2/randtable.c
        src/main/jni/bzip2/spewG.c
        src/main/jni/bzip2/unzcrash.c)
# Specifies a path to native header files.
include_directories(src/main/cpp/include/)

find_library( # Defines the name of the path variable that stores the
              # location of the NDK library.
              log-lib

              # Specifies the name of the NDK library that
              # CMake needs to locate.
              log )

# Links your native library against one or more other native libraries.
target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the log library to the target library.
                       ${log-lib} )