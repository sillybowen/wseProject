#!/bin/bash
rm -f *.class
rm -r output
export HADOOP_CLASSPATH=/Users/silly/Documents/workspace/wseProject/
javac -cp /usr/local/Cellar/hadoop/1.1.1/libexec/hadoop-core-1.1.1.jar src/*.java
cp ./src/*.class ./
hadoop MapReduceTester
rm *.class