#!/bin/bash

# Start the Selenium Grid with Chrome browsers
docker compose -f grid.yaml up --scale chrome=2 -d

# Run test suites with Chrome
export BROWSER=chrome
docker compose -f grid.yaml up tests --abort-on-container-exit --exit-code-from tests

# Stop Chrome containers and start Firefox containers
docker compose -f grid.yaml up --scale firefox=2 -d

# Run test suites with Firefox
export BROWSER=firefox
docker compose -f grid.yaml up tests --abort-on-container-exit --exit-code-from tests

# Bring down all containers
docker compose -f grid.yaml down
docker compose down
