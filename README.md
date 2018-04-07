# WizTools.org StaGen

StaGen is the static site generator behind [WizTools.org](http://www.wiztools.org/).

StaGen is written in Java, and supports:

* [Markdown](https://daringfireball.net/projects/markdown/) for content.
* [StringTemplate 4](http://www.stringtemplate.org/) for templating.
* [JSON](http://www.json.org/) for storing configuration.

Read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial).

### Deliberately Minimalist (meaning: simple to learn!)

This static site generator was developed for maintaining a site like [WizTools.org](http://www.wiztools.org/). This is best suited for sites that have few pages generated out of few templates. We do not support themes or blog-like-static-pages. Of course, by nature of flexibility of StaGen, support for themes and blogs can be easily hacked into a StaGen site.

Read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial) to get started.

## Install

Prerequisite: [Java 10](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

Download the `.zip` or `.tgz` package from our [releases section](https://github.com/wiztools/stagen/releases). Extract the content, and add to your `PATH` variable the `bin` folder. Once this is done, you will be able to execute stagen using the command `stagen`.

## From the source

### Build

To build (requires Java 10, or above versions):

    $ ./gradlew build

In the `build` directory you will find the build artifacts.

### Running

Encourage first-time visitors to read the [tutorial](https://github.com/wiztools/stagen/wiki/Tutorial). This will help in understanding the conventions used by StaGen.

In the command-prompt, to create a new template project structure (current direcory must be empty to create new project!):

    $ stagen init

To build the project:

    $ stagen gen

This command will generate the site under `target` directory.

To run the project using the embedded Jetty server:

	$ stagen run

The `run` command also monitors project directory for changes, and builds automatically when a change is detected. Press `Ctrl+C` to quit.

To delete the `target` directory:

    $ stagen clean

To view cli options:

    $ stagen -h
