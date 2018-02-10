# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/Android Studio.app/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class com.uwetrottmann.tmdb2.entities.** {*;}
-keep class com.uwetrottmann.tmdb2.enumerations.** {*;}

## -- ThreeTenBP --
# Preserve the name of the FROM fields so that ThreeTenTypeAdapters.create() would still work
-keepclassmembers public class * implements org.threeten.bp.temporal.TemporalAccessor {
     public static final org.threeten.bp.temporal.TemporalQuery FROM;
}