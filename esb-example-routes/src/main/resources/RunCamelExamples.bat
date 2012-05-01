set _=%~dp0..\..\..

start /D%_% mvn exec:java -Dexec.mainClass="com.github.rabid_fish.ExampleMain" -Dexec.args="Subscriber"
start /D%_% mvn exec:java -Dexec.mainClass="com.github.rabid_fish.ExampleMain" -Dexec.args="Processor"
start /D%_% mvn exec:java -Dexec.mainClass="com.github.rabid_fish.ExampleMain" -Dexec.args="Initiator"

REM pause
