#!/bin/bash



for ((i=1;i<=10;i++)); do
	echo "Ejecución número $i:"
	java -jar testgenerator.jar
	echo ""
done