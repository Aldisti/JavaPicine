
Compile:
javac --release 8 -cp "lib/JColor-5.5.1.jar:lib/jcommander-1.82.jar" -d ./target src/java/edu.school42.printer/*/*.java

Extract other libs:
jar -xf lib/JColor-5.5.1.jar com; mv com target; jar -xf lib/jcommander-1.82.jar com; mv com/beust target/com; rm -rf com

Copy resources into target:
cp -r src/resources target/.

Run before making the jar:
java -cp "target/" edu.school42.printer.app.Program --white=<WHITE> --black=<BLACK>

jar -cfmv target/images-to-chars-printer.jar ./src/manifest.txt -C . target/resources -C target com -C target edu



Create .jar:
jar cfvm target/images-to-chars-printer.jar src/manifest.txt -C target/edu.school42.printer . -C target/resources .

Run .jar:
java -jar target/images-to-chars-printer.jar

Compile:
jc -d target/edu.school42.printer src/java/edu.school42.printer/*/*java

Execute:
j -cp target/edu.school42.printer Program

