# 307758334
# bariudd

compile: bin
	find src -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar -d bin @sources.txt

bin:
	mkdir bin

run:
	java -jar ass6game.jar

jar:
	jar cfm ass6game.jar MANIFEST.MF -C bin . -C resources .

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java src/arkanoidgame/animation/*.java src/arkanoidgame/collidablesdata/*.java src/arkanoidgame/gamelogic/*.java src/arkanoidgame/gameobjects/*.java src/arkanoidgame/indicators/*.java src/arkanoidgame/levelsdata/*.java src/arkanoidgame/listeners/*.java src/arkanoidgame/menu/*.java src/arkanoidgame/scoredata/*.java