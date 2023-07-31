#!/bin/bash



for ((i=1;i<=100;i++)); do
	echo "Ejecución número $i:" >> resultados.txt
	cd /home/ignacio.sanchez/ntjtools/Workspaces/SISnetGitLab/testgenerator ; /usr/bin/env /usr/lib/jvm/java-11-openjdk-amd64/bin/java @/tmp/cp_3ibzppegh4z7fk1as8s7evoy7.argfile com.testgenerator.Main 
	echo ""
done