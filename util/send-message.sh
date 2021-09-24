#!/bin/bash

export PULSAR_HOME=/Users/riferrei/Work/Pulsar/apache-pulsar-2.8.1

$PULSAR_HOME/bin/pulsar-client --url pulsar://localhost:6650 \
   produce --files ${PWD}/message.json pulsar-summit
