DonniBot  [![Build Status](https://travis-ci.org/innodonni/DonniBot.png?branch=master)](https://travis-ci.org/innodonni/DonniBot) [![Floobits Status](https://floobits.com/innodonni/DonniBot.png)](https://floobits.com/innodonni/DonniBot/redirect)
========

A unit-tested Twitch IRC chat bot.

## Dependencies

- Java 8 Runtime Environment
- Tested on Microsoft(R) Windows(R) 7 Professional x64 only

## Usage

- If you haven't got one already, you'll need to get an [OAuth token](http://www.twitchapps.com/tmi/) for Twitch IRC.
- Configure authentication details in the config/donnibot.properties file.
- Run: `java -jar DonniBot-1.0-SNAPSHOT.jar`

## Compiling

This software uses Gradle to manage build dependencies. You can build the project
simply by running `gradlew` or `gradlew deployZip` from the root of the project.

## Getting involved

Collaborate with [Floobits](https://floobits.com) or submit a pull request.

## Creating a release

- Follow [SemVer](http://semver.org) numbering.
- Create the appropriate [release-X.Y branch](http://nvie.com/posts/a-successful-git-branching-model/).
- When ready, merge back into master and run the following:
 - `git tag vX.Y.Z && git push --tags`
- The build script on Travis CI will automatically upload the release to GitHub.