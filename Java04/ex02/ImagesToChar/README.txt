
# Delete target folder
rm -rf target
rm -rf lib

# Download external libs
mkdir lib
curl https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar --output lib/JColor-5.5.1.jar
curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar --output lib/jcommander-1.82.jar

# Compile
javac --release 8 -cp "lib/JColor-5.5.1.jar:lib/jcommander-1.82.jar" -d ./target src/java/edu.school42.printer/*/*.java

# Extract external libs
jar -xf lib/JColor-5.5.1.jar com; mv com target; jar -xf lib/jcommander-1.82.jar com; mv com/beust target/com; rm -rf com

# Copy resources into target
cp -r src/resources target/.

# Run before making the jar
# java -cp "target/" edu.school42.printer.app.Program --white=<WHITE> --black=<BLACK>

# Create jar
jar -cfmv target/images-to-chars-printer.jar ./src/manifest.txt -C . target/resources -C target com -C target edu

# Execute jar
# java -jar target/images-to-chars-printer.jar --white=<color> --black=<color>


