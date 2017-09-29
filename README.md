# tmdb-rxjava2-kotlin

[![Release](https://jitpack.io/v/sevar83/tmdb-rxjava2-kotlin.svg)](https://jitpack.io/#sevar83/tmdb-rxjava2-kotlin)

A Kotlin wrapper around the [TMDb v3 API](https://developers.themoviedb.org/3) using [Retrofit 2][1] and [RxJava 2][2]. Based on Uwe Trottmann's [tmdb-java](https://github.com/UweTrottmann/tmdb-java).
 
**Pull requests (e.g. support for more API endpoints, bugfixes) are welcome!**

## Usage

Add the following to your project build.gradle:

```groovy
maven { url "https://jitpack.io" }
```

Add the following dependency to your module build.gradle:

```groovy
compile 'com.github.sevar83:tmdb-rxjava2-kotlin:1.6.0'
```

Use like any other retrofit2 based service. For example:

```kotlin
// Create an instance of the service you wish to use
// you can keep this around
val tmdb = new Tmdb("yourapikey")
val movieService = tmdb.movieService()
//
// Call any of the available endpoints
val movies: Single<Movie> = movieService.details(550)
// You may use Kotlin's named parameters:
val videos: Single<Videos> = movieService.videos(550, language = "en")
```

See test cases in `src/test/` for more examples and the [retrofit website][1] for configuration options.

## Use Proguard!
You likely will not use every method in this library, so it is probably useful to strip unused ones with Proguard.
Just apply the [Proguard rules for retrofit](https://square.github.io/retrofit/#download).

## License

Created by [Uwe Trottmann](https://uwetrottmann.com).
Converted to Kotlin & RxJava 2 by Svetlozar Kostadinov.
Except where noted otherwise, released into the [public domain](UNLICENSE).
Do not just copy, make it better.


 [1]: https://square.github.io/retrofit/
 [2]: https://github.com/ReactiveX/RxJava/tree/2.x