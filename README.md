# WizTools.org StaGen

A static site generation tool that uses:

* [Markdown](https://daringfireball.net/projects/markdown/) for content.
* [StringTemplate 4](http://www.stringtemplate.org/) for templating.
* [JSON](http://www.json.org/) for storing configuration.

Read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial).

Written in Java8.

## Running

There is no official release made yet. You will have to build (requires Java 8 and Maven 3):

    $ mvn package

In the target directory you will find the executable Jar `stagen-1.0-SNAPSHOT-jar-with-dependencies.jar`. Create your project directory structure as explained in the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial).

In the command-prompt, cd into the project directory and execute:

    $ java -jar ~/stagen-1.0-SNAPSHOT-jar-with-dependencies.jar -v -f

This command will generate the site under `target` directory.

