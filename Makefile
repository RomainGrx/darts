DIR = IntelliJ/src/

.PHONY : default clean

default :
	make -C $(DIR)

clean :
	rm *.class
