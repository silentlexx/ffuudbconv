#!/bin/bash

CSV="in.csv"
SQL="out.sql"
TEMPLATE="trafaret.sql"
DEBUG="debug"

java -jar ffuudbconv.jar $CSV $TEMPLATE $SQL $DEBUG