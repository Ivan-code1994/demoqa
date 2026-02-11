@echo off
echo ===============================
echo Ejecutando pruebas automatizadas
echo ===============================

call mvnw.cmd clean test -Dtest=Runner

echo ===============================
echo Copiando historial anterior...
echo ===============================

REM Copiar historial del reporte anterior a los nuevos resultados
if exist "allure-report\history" (
    echo Encontrado historial en allure-report\history
    if not exist "allure-results\history" mkdir "allure-results\history"
    xcopy "allure-report\history" "allure-results\history" /E /I /Y
)

echo ===============================
echo Generando reporte Allure...
echo ===============================

call tools\allure\bin\allure.bat generate allure-results --clean -o allure-report

echo ===============================
echo Mostrando reporte...
echo ===============================

REM Usar START para ejecutar Allure en un proceso separado
start "Allure Report" /B cmd /c "tools\allure\bin\allure.bat serve allure-results"

echo Esperando 3 segundos para que Allure inicie...
timeout /t 3 >nul

echo ===============================
echo Eliminando carpeta temporal...
echo ===============================

REM Esperar un momento y luego intentar eliminar
echo Intentando eliminar allure-results...
timeout /t 2 >nul

REM Intentar eliminar varias veces
set attempts=0
:delete_attempt
if exist "allure-results" (
    rmdir /S /Q "allure-results" 2>nul
    set /a attempts+=1
    if %attempts% LSS 5 (
        timeout /t 1 >nul
        goto delete_attempt
    )
)

if exist "allure-results" (
    echo ⚠ No se pudo eliminar allure-results (puede estar en uso)
    echo La carpeta se eliminará cuando cierres el servidor Allure
) else (
    echo ✓ Carpeta allure-results eliminada
)

echo ===============================
echo Proceso terminado
echo ===============================
echo Reporte abierto en el navegador.
echo Historial preservado en: allure-report\history
echo ===============================
pause