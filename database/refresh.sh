#!/bin/sh
# Drop old database, recreate and input saved data
mysql -uroot < delete.sql
mysql -uroot < setup.sql
mysql -uroot < input.sql

