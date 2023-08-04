plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
}

android {
    namespace = "net.simno.kortholt"
    compileSdk = libs.versions.compileSdk.get().toInt()
    ndkVersion = libs.versions.ndk.get()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        externalNativeBuild {
            cmake {
                cppFlags("-std=c++14")
                arguments("-DANDROID_STL=c++_shared")
                abiFilters("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            }
        }
    }
    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    packaging {
        jniLibs.excludes += listOf(
            "lib/armeabi-v7a/libpd.so",
            "lib/arm64-v8a/libpd.so",
            "lib/x86/libpd.so",
            "lib/x86_64/libpd.so"
        )
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
        multipleVariants {
            withSourcesJar()
            withJavadocJar()
            allVariants()
        }
    }
}

val siteUrl = "https://github.com/simonnorberg/dmach-externals"
val gitUrl = "https://github.com/simonnorberg/dmach-externals.git"

group = "net.simno.dmach"
version = "2.0.2"

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "net.simno.dmach"
                artifactId = "dmach-externals"
                version = "2.0.2"

                pom {
                    name.set("dmach-externals")
                    url.set(siteUrl)
                    licenses {
                        license {
                            name.set("BSD New")
                            url.set("https://raw.githubusercontent.com/simonnorberg/pd-miXedSon/master/LICENSE.txt")
                        }
                        license {
                            name.set("GPL-2.0")
                            url.set("https://git.iem.at/pd/zexy/raw/master/LICENSE.txt")
                        }
                    }
                    scm {
                        connection.set(gitUrl)
                        developerConnection.set(gitUrl)
                        url.set(siteUrl)
                    }
                }
            }
        }
    }
}

tasks.register<Jar>("sourcesJar") {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.set("sources")
}

tasks.register<Javadoc>("javadoc") {
    source = android.sourceSets["main"].java.getSourceFiles()
    classpath += files(android.bootClasspath.joinToString(File.pathSeparator))
}

tasks.register<Jar>("javadocJar") {
    dependsOn("javadoc")
    archiveClassifier.set("javadoc")
    from(tasks["javadoc"].outputs.files)
}

artifacts {
    archives(tasks["javadocJar"])
    archives(tasks["sourcesJar"])
}
