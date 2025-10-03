@echo off
echo Compilando EcoChallenge com suporte a MySQL...

REM Verifica se o MySQL Connector existe
if not exist "lib\mysql-connector-java-*.jar" (
    echo ERRO: MySQL Connector nao encontrado!
    echo Baixe o MySQL Connector/J em: https://dev.mysql.com/downloads/connector/j/
    echo Coloque o arquivo .jar na pasta 'lib\'
    pause
    exit /b 1
)

REM Cria diretório de saída
if not exist "build" mkdir build

REM Compila com o MySQL Connector no classpath
echo Compilando arquivos Java...
javac -cp "lib\*;src" -d build src\com\ecochallenge\*.java src\com\ecochallenge\models\*.java src\com\ecochallenge\managers\*.java src\com\ecochallenge\database\*.java src\com\ecochallenge\dao\*.java

if %ERRORLEVEL% equ 0 (
    echo Compilacao concluida com sucesso!
) else (
    echo Erro na compilacao!
    pause
    exit /b 1
)

pause
