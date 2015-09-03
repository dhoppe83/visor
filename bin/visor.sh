#!/bin/sh
java=java
if test -n "$JAVA_HOME"; then
    java="$JAVA_HOME/bin/java"
fi
exec "$java" -Djava.library.path="../lib" -jar ../lib/visor.jar "$@"
