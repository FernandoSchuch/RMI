@echo off
cd build\classes
echo Gerando Stubs...
echo[
echo[


rmic trabalhormiserver.Servidor
echo[
echo[

timeout 3
cls

echo ============= INICIANDO ALGORITMO SERVIDOR =============
cd ../
cd ../
java -jar TrabalhoRMIClient.jar