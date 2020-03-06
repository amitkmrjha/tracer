#Technical Assignment - Backend

##Step to build the project
Unzip the source code in any folder
Navigate to the root folder and run "sbt"

there upon execute 
source code compile using "compile"
test code compile using "test:compile"

## 1.Running through SBT Console
#in sbt console execute following command "run"
once the bootstrap has happened we can paste
following input 


2016-10-20T12:43:34.000Z 2016-10-20T12:43:35.000Z trace1 back-end-3 ac->ad
2016-10-20T12:43:33.000Z 2016-10-20T12:43:36.000Z trace1 back-end-1 aa->ac
2016-10-20T12:43:38.000Z 2016-10-20T12:43:40.000Z trace1 back-end-2 aa->ab
2016-10-20T12:43:32.000Z 2016-10-20T12:43:42.000Z trace1 front-end null->aa
2016-10-20T12:43:32.000Z 2016-10-20T12:43:42.000Z trace1 front-end ad->ae
2016-10-20T12:43:32.000Z 2016-10-20T12:43:42.000Z trace1 front-end ae->af
2016-10-20T12:43:32.000Z 2016-10-20T12:43:42.000Z trace1 front-end af->ag
2016-10-20T12:43:34.000Z 2016-10-20T12:43:35.000Z trace2 back-end-3 ac->ad
2016-10-20T12:43:33.000Z 2016-10-20T12:43:36.000Z trace2 back-end-1 aa->ac
2016-10-20T12:43:38.000Z 2016-10-20T12:43:40.000Z trace2 back-end-2 aa->ab
2016-10-20T12:43:32.000Z 2016-10-20T12:43:42.000Z trace2 front-end null->aa
2016-10-20T12:43:34.000Z 2016-10-20T12:43:35.000Z trace3 back-end-3 ac->ad
2016-10-20T12:43:33.000Z 2016-10-20T12:43:36.000Z trace3 back-end-1 aa->ac
2016-10-20T12:43:38.000Z 2016-10-20T12:43:40.000Z trace3 back-end-2 aa->ab
2016-10-20T12:43:32.000Z 2016-10-20T12:43:42.000Z trace3 front-end null->aa 

after 15 second of pool delay will get json out put.

## 2.Running through batch file or shell file
in sbt console execute following command "compile"
there upon to execute "universal:packageBin" to generate the zip containing program jar and lib folder containing 
dependencies.

We can take this zip to suitable working directory and unzip it.
then we can navigate to bin folder and run batch or shell command.





## This scala program uses Akka type actor to for parallel statefull processing as well as akka stream



# tracer
