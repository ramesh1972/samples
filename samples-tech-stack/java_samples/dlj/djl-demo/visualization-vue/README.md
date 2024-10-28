# Visualizing Training with DJL

This module provides training visualization UI and backend service.

Currently, implemented features:

- Training/Validating progress
- Training Metrics Visualization chart
- Inference: Online image/Local image

## Prerequisites

* You need to have Java Development Kit version 11 or later installed on your system.
* You should be familiar with the API documentation in the DJL [Javadoc](https://javadoc.djl.ai/api/0.4.0/index.html).

### Install the Java Development Kit

For ubuntu:

```bash
sudo apt-get install openjdk-11-jdk-headless
```
For centos:

```bash
sudo yum install java-11-openjdk-devel
```
For macOS:

```bash
brew tap homebrew/cask-versions
brew update
brew cask install adoptopenjdk11
```

You can also download and install [Oracle JDK](https://www.oracle.com/technetwork/java/javase/overview/index.html)
manually if you have trouble with the previous commands.

If you have multiple versions of Java installed, you can use the $JAVA_HOME environment
variable to control which version of Java to use.

# Getting started: 30 seconds to run an example

## Build and install DJL UI

This component supports building with npm. To build, use the following command:  
(Refer to reference info, if you encounter any issue.)

```bash
# clone the project
git clone https://github.com/deepjavalibrary/djl-demo.git

# Install npm if you didn't have npm installed
brew install npm

# enter the project directory
cd djl-demo/visualization-vue/djl-training-ui

# install dependency
npm install

# develop
npm run dev
```

This will automatically open http://localhost:8090

## Build backend example project

To build, use the following command:

```bash
# enter the project directory
cd djl-demo/visualization-vue/djl-training-demo/

# Maven build
./mvnw package -DskipTests

# Run example code
java -jar target/djl-training-demo-0.0.1-SNAPSHOT.jar
# or
mvnw spring-boot:run
```

## Open browser

Open http://localhost:8090 to get:
#### 1. Training:
![Screenshot](training.png)

#### 2. Inference:
![Screenshot](inference.png)

  
  
## Reference information
#### 1. Check node.js and npm version:

```bash
node -v
v14.16.0
npm -v
7.8.0
```

#### 2. Install latest node.js and npm:

```bash
#Clean cache
sudo npm cache clean -f
#Install n
sudo npm install -g n
#Install latest node.js
sudo n stable
#Install latest npm
sudo npm install npm@latest -g
```
#### 3. If you're getting this error on Mac OSX:
"Package pangocairo was not found in the pkg-config search path."  
Ensure to run:

```bash
brew install pkg-config
brew intall cairo
brew install pango
```
