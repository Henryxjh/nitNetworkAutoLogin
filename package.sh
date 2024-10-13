#!/bin/bash
mvn package -f pom.xml
# shellcheck disable=SC2164
cd target/lib

for file in *; do
    if [[ -f "$file" ]]; then
        if [[ "$file" == *.jar ]]; then
            jar -x -f "$file"
            rm -f META-INF/MANIFEST.MF
            rm -f "$file"
        else
            rm -f "$file"
        fi
    fi
done

rm -f module-info.class

for file1 in ../auto_login*.jar; do
    if [[ -f "$file1" ]]; then
        jar -x -f "$file1"
        rm -f "$file1"
    fi
done

jar -c -m ./META-INF/MANIFEST.MF -f "$file1" ./*