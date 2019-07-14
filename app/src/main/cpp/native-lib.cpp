//
// Created by 徐奇 on 2019-07-14.
//

#include <jni.h>
#include <string>

extern "C"

JNIEXPORT jstring JNICALL Java_com_example_addapkupgradedemo_MainActivity_getStringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++ test2";
    return env->NewStringUTF(hello.c_str());
}