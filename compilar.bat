@echo off
echo Compilando EcoChallenge Terminal...
javac -d . src/com/ecochallenge/*.java src/com/ecochallenge/models/*.java src/com/ecochallenge/managers/*.java
if %errorlevel% == 0 (
    echo Compilacao concluida com sucesso!
) else (
    echo Erro na compilacao!
    pause
)

