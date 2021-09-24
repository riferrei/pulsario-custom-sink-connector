#!/bin/bash

export PULSAR_HOME=/Users/riferrei/Work/Pulsar/apache-pulsar-2.8.1

$PULSAR_HOME/bin/pulsar-admin --admin-url http://localhost:8080 \
  sinks create --tenant public --namespace default \
  --sink-type custom_sink_connector --name customSinkConnector \
  --sink-config-file ${PWD}/../util/custom-sink-config.yml \
  --inputs pulsar-summit
  