# Continuous Integration and Deployment

As a part of deployment, there are three major 

## Continuous Integration using Travis CI

We need to educate Travis-ci about our project. This is done by adding a ```.travis.yml``` file in the root of the project.

[Travis CI documents](https://docs.travis-ci.com/)

A typical .travis.yml file

```yaml
language: scala
jdk:
  - openjdk11
if: tag IS blank
services:
  - mysql
addons:
  apt:
    sources:
      - mysql-5.7-xenial
    packages:
      - mysql-server
dist: bionic
sudo: required
before_install:
  - echo -e "machine github.com\n  login $GITHUB_AUTH_TOKEN" > ~/.netrc
  - mysql -e 'CREATE DATABASE IF NOT EXISTS $ZZ_API_TEST_DB_NAME;'
  - sudo mysql -e "use mysql; update user set authentication_string=PASSWORD('') where user='$ZZ_API_DB_USERNAME'; update user set plugin='mysql_native_password';FLUSH PRIVILEGES;"
  - sudo mysql_upgrade -u $ZZ_API_DB_USERNAME
  - sudo service mysql restart
git:
  depth: false
env:
  global:
  - ZZ_API_DB_HOST="localhost:3306"
  - ZZ_API_TEST_DB_NAME=issue_management_test
  - ZZ_API_DB_USERNAME=root
  - ZZ_API_DB_PASSWORD=""
  - SCALA_2_12="2.12.8"
  - SCALA_2_13="2.13.3"
before_cache:
  - find $HOME/.ivy2 -name "ivydata-*.properties" -delete
  - find $HOME/.sbt -name "*.lock" -delete
cache:
  directories:
  - $HOME/.sbt/boot/scala*
  - $HOME/.sbt/cache
  - $HOME/.sbt/launchers
  - $HOME/.ivy2/cache
  - $HOME/.coursier
stages
  - version_2.12
  - version_2.13
jobs:
  include:
    - stage: version_2.12
      name: "2.12.8"
      script:
        - if [ "$TRAVIS_EVENT_TYPE" == "cron" ]; then sbt coverage $SCALA_2_12 test ; else sbt $SCALA_2_12 test; fi
      after_success:
       - sbt coverageReport coverageAggregate
      deploy:
        - provider: script
          skip_cleanup: true
          script: sbt publish
          on:
            all_branches: true
            condition: $TRAVIS_BRANCH != master || $TRAVIS_BRANCH != develop
        - provider: script
          skip_cleanup: true
          before_deploy:
            - travis/before_deploy.sh
          script: sbt publish
          on:
            branch: develop
        - provider: script
          skip_cleanup: true
          script: travis/release.sh
          on:
            branch: master
    - stage: version_2.13
      name: "2.13.3"
      script:
          - if [ "$TRAVIS_EVENT_TYPE" == "cron" ]; then sbt coverage  $SCALA_2_13 test ; else sbt $SCALA_2_13 test; fi
      after_success:
          - sbt coverageReport coverageAggregate
      deploy:
          - provider: script
            skip_cleanup: true
            script: sbt publish
            on:
              all_branches: true
              condition: $TRAVIS_BRANCH != master || $TRAVIS_BRANCH != develop
          - provider: script
            skip_cleanup: true
            before_deploy:
              - travis/before_deploy.sh
            script: sbt publish
            on:
              branch: develop
          - provider: script
            skip_cleanup: true
            script: travis/release.sh
            on:
              branch: master

```

## Artefact Management (Publishing) using bintray

* BinTray does not allow snapshot builds
* Most of the artefacts would require License information
* Task key of skip to true for the root project. This key is specific to publish task, and since we do not want to publish our group project, we set the publish/skip to true.

To publish all of our modules with same version, we can use ThisBuild feature here. If we don't use the ThisBuild here, the submodule would default to 0.0.1-SNAPSHOT while the root project has moved to 1.0.

In order to push our package to Bintray, we have to login to bintray. On sbt shell, type ```bintrayChangeCredentials``` and hit enter.

Now clean the build and publish it to bintray using ```;clean ;publish```. To test whether it is actually published, we can add
```shell
resolvers += Resolver.JCenterRepository
lazy val tes = project.settings(
  libraryDependencies += ("calculators" % "calculators_2.13" % "1.0")
)
  
ls ~/.ivy2/cache/calculators/calculators_2.13/jars
```

## Continuous Deployment

Deploying API to Heroku server

heroku login

Heroku pulls only from master branch of git
```shell
git checkout master && git pull
heroku git:remote -a h2-sbt-api
git push heroku master

# Heroku now pull, compiles and deploys to Heroku servers
```
