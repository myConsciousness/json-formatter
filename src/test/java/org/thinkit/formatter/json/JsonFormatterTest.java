/*
 * Copyright 2021 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.formatter.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * {@link JsonFormatter} クラスのテストケースを管理するクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.2
 */
public final class JsonFormatterTest {

    @Test
    void testFormat() {

        final String formattedJson = JsonFormatter.newInstance().format(
                "    {\"metadata\":{\"author\":\"Kato Shinya\",\"since\":\"1.0\",\"version\":\"1.0\",\"creationDate\":\"2020/06/24\",\"encoding\":\"UTF-8\",\"description\":\"test\"},\"selectionNodes\":[{\"node\":{\"conditionId\":\"0\",\"test1\":\"test\",\"test2\":\"test\"}},{\"node\":{\"conditionId\":\"1\",\"test1\":\"test\",\"test2\":\"test\"}},{\"node\":{\"conditionId\":\"2\",\"test1\":\"test\",\"test2\":\"test\"}}],\"conditionNodes\":[{\"node\":{\"conditionId\":\"0\",\"exclude\":false,\"conditions\":[{\"keyName\":\"test\",\"operand\":\"=\",\"value\":\"0\"}]}},{\"node\":{\"conditionId\":\"1\",\"exclude\":false,\"conditions\":[{\"keyName\":\"test\",\"operand\":\"=\",\"value\":\"1\"}]}},{\"node\":{\"conditionId\":\"2\",\"exclude\":false,\"conditions\":[{\"keyName\":\"test\",\"operand\":\"=\",\"value\":\"2\"}]}}]}");

        assertNotNull(formattedJson);
        assertEquals(EXPECTED_FORMATTED_JSON_STRING, formattedJson);
    }

    /**
     * 整形されたJSONの期待値
     */
    private final String EXPECTED_FORMATTED_JSON_STRING = """
            {
              "metadata":{
                "author":"Kato Shinya",
                "since":"1.0",
                "version":"1.0",
                "creationDate":"2020/06/24",
                "encoding":"UTF-8",
                "description":"test"
              },
              "selectionNodes":[
                {
                  "node":{
                    "conditionId":"0",
                    "test1":"test",
                    "test2":"test"
                  }
                },
                {
                  "node":{
                    "conditionId":"1",
                    "test1":"test",
                    "test2":"test"
                  }
                },
                {
                  "node":{
                    "conditionId":"2",
                    "test1":"test",
                    "test2":"test"
                  }
                }
              ],
              "conditionNodes":[
                {
                  "node":{
                    "conditionId":"0",
                    "exclude":false,
                    "conditions":[
                      {
                        "keyName":"test",
                        "operand":"=",
                        "value":"0"
                      }
                    ]
                  }
                },
                {
                  "node":{
                    "conditionId":"1",
                    "exclude":false,
                    "conditions":[
                      {
                        "keyName":"test",
                        "operand":"=",
                        "value":"1"
                      }
                    ]
                  }
                },
                {
                  "node":{
                    "conditionId":"2",
                    "exclude":false,
                    "conditions":[
                      {
                        "keyName":"test",
                        "operand":"=",
                        "value":"2"
                      }
                    ]
                  }
                }
              ]
            }
            """;
}
