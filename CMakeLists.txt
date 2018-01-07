cmake_minimum_required(VERSION 3.4.1)

set(
    PDCORE_PATH
    CACHE STRING ""
)

message(${PDCORE_PATH})
message(${ANDROID_ABI})

include_directories(${PDCORE_PATH}/jni/libpd/pure-data/src)

add_library(
    tanh_tilde
    SHARED
    src/main/cpp/cyclone/tanh.c
    src/main/cpp/cyclone/shared.c
    src/main/cpp/cyclone/loud.c
    src/main/cpp/cyclone/sic.c
)

add_library(
    limiter_tilde
    SHARED
    src/main/cpp/zexy/limiter~.c
)

add_library(
    z_tilde
    SHARED
    src/main/cpp/zexy/z~.c
)

target_link_libraries(
    tanh_tilde
    ${PDCORE_PATH}/libs/${ANDROID_ABI}/libpd.so
)

target_link_libraries(
    limiter_tilde
    ${PDCORE_PATH}/libs/${ANDROID_ABI}/libpd.so
)

target_link_libraries(
    z_tilde
    ${PDCORE_PATH}/libs/${ANDROID_ABI}/libpd.so
)
