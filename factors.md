# 12 Factor Application - Fulfilled factors
## 1. Codebase
>A twelve-factor app is always tracked in a version control system

Has been achieved: https://github.com/Mackesmilian/12factor

## 2. Dependencies
>Explicitly declare and isolate dependencies

Completed as to my understanding because all necessary dependencies are defined in build.gradle

## 3. Config
>Store config in the environment

There wasn't much to configure here, but I have extracted some properties into the `application.properties` file.

## 4. Backing Services
>Treat backing services as attached resources

I only have one resource in this application, and it is third party. 
However, in theory if I needed to add a local service I would access it the same way.

## 5. Build, release, run
>Strictly separate build and run stages

I have configured gradle to allow this. 
There are separate build and run stages.

## 6. Processes
>Twelve-factor processes are stateless and share-nothing. Any data that needs to persist must be stored in a stateful backing service, typically a database.

I am not 100% sure I have completed this factor,
but I do think I'm not far off because I do not persist any data in the first place.
>In the simplest case, the code is a stand-alone script, the execution environment is a developer’s local laptop with an installed language runtime, and the process is launched via the command line (for example, python my_script.py).

According to this quote it seems as though I have completed the factor as the app can easily be started with a gradle 
task?

## 7. Port binding
>Export services via port binding

As this is a spring boot app this is provided out of the box and therefore completed.

## 8. Dev/prod parity
>Keep development, staging, and production as similar as possible

There is no prod environment so to speak in this case, but if there were 
it would be the exact same as the dev environment, because nothing 
would need to change between those two.