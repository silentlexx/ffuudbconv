@echo off

set CSV=in.csv
set SQL=out.sql
set TEMPLATE=trafaret.sql
set DEBUG=debug

start javaw.exe -jar ffuudbconv.jar %CSV% %TEMPLATE% %SQL% %DEBUG%