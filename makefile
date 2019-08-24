#makefile

Pet: Pet.java
	javac Pet.java

clean: 
	rm -f *.class
	rm -f *.ser
	
run: Pet
	java Pet
