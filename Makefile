build:
	javac Walsh.java
	javac Prinel.java
	javac Statistics.java
	javac Crypto.java

run-p1:
	java Walsh

run-p2:
	java Statistics

run-p3:
	java Prinel

run-p4:
	java Crypto

clean:
	rm -f *.class

.PHONY: build clean