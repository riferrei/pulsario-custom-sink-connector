#!/bin/bash

export PULSAR_HOME=/Users/riferrei/Work/Pulsar/apache-pulsar-2.8.1

$PULSAR_HOME/bin/pulsar-admin --admin-url http://localhost:8080 \
  sinks localrun --archive ${PWD}/../target/custom-sink-connector-0.0.1.nar \
  --tenant public --namespace default --name customSinkConnectorTest \
  --sink-config-file ${PWD}/../util/custom-sink-config.yml --inputs pulsar-summit \
  --parallelism 1
