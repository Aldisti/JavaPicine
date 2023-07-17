
Create .jar:
jar cfvm target/images-to-chars-printer.jar src/manifest.txt -C target/edu.school42.printer . -C target/resources .

Run .jar:
java -jar target/images-to-chars-printer.jar

Compile:
jc -d target/edu.school42.printer src/java/edu.school42.printer/*/*java

Execute:
j -cp target/edu.school42.printer Program

