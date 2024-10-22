/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_project_cs4421_project_pciInfo */

#ifndef _Included_com_project_cs4421_project_pciInfo
#define _Included_com_project_cs4421_project_pciInfo
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_project_cs4421_project_pciInfo
 * Method:    read
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_project_cs4421_project_pciInfo_read
  (JNIEnv *, jobject);

/*
 * Class:     com_project_cs4421_project_pciInfo
 * Method:    busCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_project_cs4421_project_pciInfo_busCount
  (JNIEnv *, jobject);

/*
 * Class:     com_project_cs4421_project_pciInfo
 * Method:    deviceCount
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_project_cs4421_project_pciInfo_deviceCount
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_project_cs4421_project_pciInfo
 * Method:    functionCount
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_project_cs4421_project_pciInfo_functionCount
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_project_cs4421_project_pciInfo
 * Method:    functionPresent
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_project_cs4421_project_pciInfo_functionPresent
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     com_project_cs4421_project_pciInfo
 * Method:    vendorID
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_project_cs4421_project_pciInfo_vendorID
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     com_project_cs4421_project_pciInfo
 * Method:    productID
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_project_cs4421_project_pciInfo_productID
  (JNIEnv *, jobject, jint, jint, jint);

#ifdef __cplusplus
}
#endif
#endif