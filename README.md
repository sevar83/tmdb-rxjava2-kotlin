**Pull requests (e.g. support for more API endpoints, bugfixes) are welcome!**

# tmdb-java

A Kotlin wrapper around the [TMDb v3 API](https://developers.themoviedb.org/3) using [Retrofit 2][1] and [RxJava 2][2].

## Usage
<a href="https://search.maven.org/#search%7Cga%7C1%7Ctmdb-java"><img src="https://img.shields.io/maven-central/v/com.uwetrottmann.tmdb2/tmdb-java.svg?style=flat-square"></a>

Add the following to your project build.gradle:

```groovy
maven { url "https://jitpack.io" }
```

Add the following dependency to your module build.gradle:

```groovy
compile 'com.github.sevar83:tmdb-rxjava2:1.6.0'
```

Use like any other retrofit2 based service. For example:

```kotlin
// Create an instance of the service you wish to use
// you can keep this around
val tmdb = new Tmdb("yourapikey")
val movieService = tmdb.movieService()
//
// Call any of the available endpoints
val movies: Observable<Movie> = movieService.summary(550)
val trailers: Observable<Trailers> = movieService.trailers(550)
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