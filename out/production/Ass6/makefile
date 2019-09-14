# 307758334
# bariudd

compile: bin
	find src -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar -d bin @sources.txt

run:
	java -jar ass5game.jar 1 2 3 4

jar:
	jar cfm ass5game.jar src/META-INF/MANIFEST.MF -C bin .

bin:
	mkdir bin

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java src/arkanoidgame/animation/*.java src/arkanoidgame/collidablesdata/*.java src/arkanoidgame/gamelogic/*.java src/arkanoidgame/gameobjects/*.java src/arkanoidgame/indicators/*.java src/arkanoidgame/levelsdata/*.java src/arkanoidgame/listeners/*.java