# WizTools.org StaGen

StaGen is the static site generator behind [WizTools.org](http://www.wiztools.org/).

StaGen is written in Java 8, and supports:

* [Markdown](https://daringfireball.net/projects/markdown/) for content.
* [StringTemplate 4](http://www.stringtemplate.org/) for templating.
* [JSON](http://www.json.org/) for storing configuration.

Read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial).

### Extensible

At the core of StaGen engine, Guice is used for wiring the implementations with the interfaces. StaGen can be easily extended to support any new format.

### Deliberately Minimalist (meaning: simple to learn!)

This static site generator was developed for maintaining a site like [WizTools.org](http://www.wiztools.org/). This is best suited for sites that have few pages generated out of few templates. We do not support themes or blog-like-static-pages. Of course, by nature of flexibility of StaGen, support for themes and blogs can be easily hacked into a StaGen site.

Read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial) to get started.

## Build

To build (requires Java 8 and Maven 3, or above versions):

    $ mvn package

In the `target` directory you will find the executable jar `stagen-VER-full.jar`.

## Running

Encourage first-time visitors to read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial). This will help in understanding the conventions used by StaGen.

In the command-prompt, to create a new template project structure (current direcory must be empty to create new project!):

    $ java -jar ~/stagen-VER-full.jar init

To build the project:

    $ java -jar ~/stagen-VER-full.jar gen

This command will generate the site under `target` directory.

To run the project using the embedded Jetty server:

	$ java -jar ~/stagen-VER-full.jar run

The `run` command also monitors project directory for changes, and builds automatically when a change is detected. Press `Ctrl+C` to quit.

To delete the `target` directory:

    $ java -jar ~/stagen-VER-full.jar clean

To view cli options:

    $ java -jar ~/stagen-VER-full.jar -h

## Install

Download the `.zip` or `.tgz` package from our [releases section](https://github.com/wiztools/stagen/releases). Extract the content, and add to your `PATH` variable the `bin` folder. Once this is done, you will be able to execute stagen using the command `stagen`.
