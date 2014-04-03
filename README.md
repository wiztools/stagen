# WizTools.org StaGen

StaGen is the static site generator behind [WizTools.org](http://www.wiztools.org/).

StaGen is written in Java 8, and supports:

* [Markdown](https://daringfireball.net/projects/markdown/) for content.
* [StringTemplate 4](http://www.stringtemplate.org/) for templating.
* [JSON](http://www.json.org/) for storing configuration.

Read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial).

### Extensible

At the core of StaGen engine, Guice is used for wiring the implementations with the interfaces. StaGen can be easily extended to support any new format.

## Build

To build:

    $ mvn package

In the target directory you will find the executable Jar `stagen-VER-full.jar`.

## Running

Encourage first-time visitors to read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial). This will help in understanding the conventions used by StaGen.

In the command-prompt, to create a new template project structure (current direcory must be empty to create new project!):

    $ java -jar ~/stagen-VER-full.jar init

To build the project:

    $ java -jar ~/stagen-VER-full.jar gen

This command will generate the site under `target` directory.

To delete the `target` directory:

    $ java -jar ~/stagen-VER-full.jar clean

To view cli options:

    $ java -jar ~/stagen-VER-full.jar -h

