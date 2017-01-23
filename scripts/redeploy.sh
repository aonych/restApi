#!/bin/sh

echo "************ UNDEPLOYING *******************"
asadmin undeploy rest_istoty-1.0-SNAPSHOT
echo "************ BUILDING **********************"
cd ..
mvn clean
mvn package
echo "************ DEPLOYING *********************"
asadmin deploy target/rest_istoty-1.0-SNAPSHOT.war
