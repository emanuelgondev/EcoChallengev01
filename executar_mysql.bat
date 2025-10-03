@echo off
echo Executando EcoChallenge com MySQL...

REM Verifica se foi compilado
if not exist "build\com\ecochallenge\EcoChallengeTerminal.class" (
    echo ERRO: Projeto nao compilado!
    echo Execute primeiro: compilar_mysql.bat
    pause
    exit /b 1
)

REM Verifica se o MySQL Connector existe
if not exist "lib\mysql-connector-java-*.jar" (
    echo ERRO: MySQL Connector nao encontrado!
    echo Baixe o MySQL Connector/J e coloque na pasta 'lib\'
    pause
    exit /b 1
)

REM Executa o programa
echo Iniciando aplicacao...
java -cp "build;lib\*" com.ecochallenge.EcoChallengeTerminal

pause
