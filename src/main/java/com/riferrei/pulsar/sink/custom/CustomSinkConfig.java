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

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

import lombok.experimental.Accessors;

import org.apache.commons.lang3.StringUtils;
import org.apache.pulsar.io.core.annotations.FieldDoc;

@Data
@Accessors(chain = true)
public class CustomSinkConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @FieldDoc(
        required = true,
        defaultValue = "",
        help = "Example of a required parameter for the connector"
    )
    private String requiredParam;

    @FieldDoc(
        required = false,
        defaultValue = "",
        help = "Example of a non-required parameter for the connector"
    )
    private String nonRequiredParam;

    @FieldDoc(
            required = false,
            defaultValue = "5",
            help = "Example of a parameter that, if omitted — will use its default value"
    )
    private long paramWithDefaultValue = 100;

    public static CustomSinkConfig load(Map<String, Object> config) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new ObjectMapper().writeValueAsString(config), CustomSinkConfig.class);
    }

    public void validate() {
        if (StringUtils.isEmpty(requiredParam)) {
            throw new IllegalArgumentException("requiredParam is not set");
        }
        if (paramWithDefaultValue > 100) {
            throw new IllegalArgumentException("paramWithDefaultValue cannot be higher than 100");
        }
    }

}
