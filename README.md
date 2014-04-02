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

In the target directory you will find the executable Jar `stagen-VER-full.jar`. Create your project directory structure as explained in the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial).

In the command-prompt, to create a new template project structure (current direcory must be empty to create new project!):

    $ java -jar ~/stagen-VER-full.jar init

To build the project:

    $ java -jar ~/stagen-VER-full.jar gen

This command will generate the site under `target` directory.

To delete the `target` directory:

    $ java -jar ~/stagen-VER-full.jar clean

To view cli options:

    $ java -jar ~/stagen-VER-full.jar -h

