#!/bin/bash
rm -f src/*.class
rm -f *.class
rm -r output
javac -cp /usr/local/Cellar/hadoop/1.1.1/libexec/hadoop-core-1.1.1.jar src/*.java
mv src/*.class ./
hadoop MapReduceTester 