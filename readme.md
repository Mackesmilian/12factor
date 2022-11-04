# 12 Factor Application
## Description
Super simple application that calls a weather 
API and checks whether the weather will be warmer or colder,
on average, over the coming eight days. 
The app compares Vienna and Graz as those are the cities I 
live in.

## Run

To run execute the `dockerRun` gradle task or call 
`docker build -t mackesmilianmaddi/12factor .`
and then 
`docker run -p 4076:4076 mackesmilianmaddi/12factor`
