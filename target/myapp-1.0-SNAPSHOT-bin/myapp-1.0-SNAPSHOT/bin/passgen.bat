
@REM ==== START VALIDATION ====



@echo off

IF  "%MyApp%"=="" GOTO A


IF NOT "%MyApp%"=="" GOTO B


:A

java -jar ../lib/myapp-1.0-SNAPSHOT.jar %1 %2
GOTO END


:B
java -jar %MyApp%/lib/myapp-1.0-SNAPSHOT.jar %1 %2
GOTO END

:END





