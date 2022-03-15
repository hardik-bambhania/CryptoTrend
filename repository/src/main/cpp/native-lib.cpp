#include <jni.h>
#include <string>
#include <iostream>


/**
 * This method format your crypto currency data into logs
 */
extern "C" JNIEXPORT jstring JNICALL
Java_com_cryptotrend_repository_Repository_prepareLog(
        JNIEnv *env,
        jobject /* this */,
        jstring symbol,
        jstring usd) {

    char result[200];
    const auto now = std::chrono::system_clock::now();
    char dest[70];
    time_t temp;
    struct tm *timeptr;

    //Get system time
    temp = time(nullptr);
    timeptr = localtime(&temp);
    strftime(dest, sizeof(dest) - 1, "%A, %b %d %I:%M:%S %p | ", timeptr);
    const char *name = env->GetStringUTFChars(symbol, nullptr);
    const char *price = env->GetStringUTFChars(usd, nullptr);

    //Concat all details
    strcpy(result, dest);
    strcat(result, name);
    strcat(result, " | ");
    strcat(result, price);
    strcat(result, " USD");

    //release memory
    env->ReleaseStringUTFChars(symbol, name);
    env->ReleaseStringUTFChars(usd, price);

    return env->NewStringUTF(result);
}