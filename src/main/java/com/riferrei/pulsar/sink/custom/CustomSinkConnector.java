/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
 
package com.riferrei.pulsar.sink.custom;

import java.util.Map;

import org.apache.pulsar.io.core.Sink;
import org.apache.pulsar.functions.api.Record;
import org.apache.pulsar.io.core.SinkContext;
import org.apache.pulsar.io.core.annotations.Connector;
import org.apache.pulsar.io.core.annotations.IOType;
//import org.apache.pulsar.client.api.schema.GenericObject;

@Connector(
        name = "custom_sink_connector",
        type = IOType.SINK,
        help = "Example of a sink connector for Pulsar I/O",
        configClass = CustomSinkConfig.class
)
public class CustomSinkConnector implements Sink<String> {

    private CustomSinkConfig customSinkConfig;

    @Override
    public void open(Map<String, Object> config, SinkContext sinkContext) throws Exception {

        customSinkConfig = CustomSinkConfig.load(config);
        customSinkConfig.validate();

        StringBuilder sb = new StringBuilder();
        sb.append("=====> ");
        sb.append(customSinkConfig.getRequiredParam()).append(" ");
        sb.append(customSinkConfig.getNonRequiredParam()).append(" is ");
        if (customSinkConfig.getParamWithDefaultValue() != 100) {
            sb.append(customSinkConfig.getParamWithDefaultValue());
        } else {
            sb.append("💯");
        }
        System.out.println(sb.toString());

    }

    @Override
    public void write(Record<String> record) throws Exception {
        System.out.println("=====> " + record);
        record.ack();
    }

    @Override
    public void close() {
    }

}
