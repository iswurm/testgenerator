#!/bin/bash



for ((i=1;i<=3;i++)); do
	echo "Ejecución número $i:" >> resultados.txt
	java -jar testgenerator.jar
	echo ""
done