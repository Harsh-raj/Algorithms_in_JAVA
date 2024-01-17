package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        new MainWindow();
    }
}

/*
 * Here's how to build a jar with IntelliJ 10 http://blogs.jetbrains.com/idea/2010/08/quickly-create-jar-artifact/
 *
 * File -> Project Structure -> Project Settings -> Artifacts -> Click green plus sign -> Jar -> From modules with dependencies...
 *
 * Select a Main Class (the one with main() method) if you need to make the jar runnable.
 *
 * The above sets the "skeleton" to where the jar will be saved to. To actually build and save it do the following:
 *
 * Extract to the target Jar
 *
 * OK
 *
 * Build | Build Artifact | Build
 *
 * Try Extracting the .jar file from
 *
 * ProjectName | out | artifacts | ProjectName_jar | ProjectName.jar
 * */