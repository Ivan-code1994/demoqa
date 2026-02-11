@echo off
@echo off
echo Ejecutando pruebas automatizadas...
call mvnw.cmd clean test -Dtest=Runner
pause