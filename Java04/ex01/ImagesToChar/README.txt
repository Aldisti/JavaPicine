Compile:
javac --release 8 -d target/ src/java/edu.school42.printer/*/*java

Copy resources:
cp -r src/resources target/.

To run before jar:
java -cp target/ edu.school42.printer.app.Program

Create jar:
jar cfvm target/images-to-chars-printer.jar src/manifest.txt -C target/ edu/ -C . target/resources

Execute jar:
java -jar target/images-to-chars-printer.jar

