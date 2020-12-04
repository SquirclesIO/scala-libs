# PI PRELUDE

Une collection d'utilitaires et de types riche aidant à la programmation des projets perfimmo.

## TO deploy on nexus

made by CI

## Install with sbt

`"org.perfimmo" %% "pi-prelude" % "0.1.2-SNAPSHOT"`

## Exemples

### SafeUUID

### NoCaseString

### \/ a.k.a Disjonction a.k.a Bicovariant Either

```scala
import pi.prelude.either.\/._
import zio.ZIO            
import zio.prelude._

val either = "toto".right[String]

val result: ZIO[Any, String, String] =
    either
        .map { s => s"hello $s" }
        .flatMap { x => x.right[String] }
        .mapError { e => s"error: $e" }
        .io
        .map { s => s.right[String] }
        .absolve
```