Blockbender
===========

Set-up for latest version:

1. Download Minecraft Forge v1180 src.
2. Create new forge1180 folder, extract zip to it.
3. Run "gradlew.bat setupDecompWorkspace" on command line, wait for "Build Successful".
4. Run "gradlew.bat eclipse" on command line, wait for "Build Successful".
5. Open eclipse using forge1180/eclipse as workspace.
6. Window > Show view > Other > Git > Git Repositories.
7. Clone a Git repository.
8. Paste SSH address and change protocol to SSH, press next.
9. Master should be ticked, press next.
10. Change directory, tick 'import all existing projects after clone finishes', press finish.
11. Add Blockbender project to classpath of both server and client run configurations.

Command Line help:

dir -> list folders  
cd folder -> change to folder  
cd fol* -> change to folder (use for folders with long names)  
cd .. -> go up one level  
press tab to autocomplete names  
