# Yildiz-Engine module-graphic.

This is the official repository of the Graphic Module, part of the Yildiz-Engine project.
The graphic module is an abstract component meant to display 3D scenes with cameras, lights, particles, volumetric effects and a 2D or 3D GUI.
It requires an implementation module to materialize it.

## Features

* 3D rendering.
* Multi camera.
* Several light type(spot, omni, directional).
* Particle and particle effects.
* Skybox.
* VFS resource loading.
* Mesh animation.
* GUI API with many widgets.
* ...

## Requirements

To build this module, you will need a java 9 JDK, and Maven 3.

## Coding Style and other information

Project website:
http://www.yildiz-games.be

Issue tracker:
https://yildiz.atlassian.net

Wiki:
https://yildiz.atlassian.net/wiki

Quality report:
https://sonarqube.com/overview?id=be.yildiz-games:module-graphic

## License

All source code files are licensed under the permissive MIT license
(http://opensource.org/licenses/MIT) unless marked differently in a particular folder/file.
## Build instructions for module-graphic using maven.

Go to your root directory, where you POM file is located.

Then invoke maven

	mvn clean install

This will compile the source code, then run the unit tests, and finally build a jar file.

## Usage

In your maven project, add the dependency

```xml
<dependency>
    <groupId>be.yildiz-games</groupId>
    <artifactId>module-graphic</artifactId>
    <version>LATEST</version>
</dependency>
```
Replace LATEST with the correct version.
## Contact
Owner of this repository: Grégory Van den Borre