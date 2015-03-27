#
# Makefile - utility commands
# 
# Chris Dean

all: run

run:
	lein trampoline run

# Create the tables
init:
	createuser -s postgres -h localhost || exit 0
	createdb -Upostgres -h localhost sampleapi
	createdb -Upostgres -h localhost sampleapi_test

# Drop the tables
drop:
	dropdb -Upostgres -h localhost sampleapi
	dropdb -Upostgres -h localhost sampleapi_test

migrate:
	lein ragtime migrate
	lein with-profile test ragtime migrate

# Nuke the existing databases and recreate
rebuild: drop init

rebuild_all: drop init migrate

test:
	lein test

.PHONY: test

