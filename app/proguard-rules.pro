# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# Keep Google HTTP Client classes
-keep class com.google.api.client.http.** { *; }

# Keep Joda-Time classes (used for time calculations)
-keep class org.joda.time.** { *; }

# Ignore all ErrorProne annotations (they are not required at runtime)
-dontwarn com.google.errorprone.annotations.**

# Keep Google Tink classes (ensures no critical code is stripped)
-keep class com.google.crypto.tink.** { *; }

# Suppress warnings related to missing Google HTTP Client
-dontwarn com.google.api.client.**

# Suppress warnings related to missing Joda-Time
-dontwarn org.joda.time.**

# Keep AuthResponseModel and its nested User class
-keep class com.aagamshah.slipstreampicks.domain.model.** { *; }


