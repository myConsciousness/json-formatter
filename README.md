# JSON Formatter

## What is it?

**_This is a formatter for formatting JSON strings._**

`JSON Formatter` is specialized in formatting JSON strings that do not use regular expressions and has no functions other than formatting. **_Therefore it is extremely lightweight besides can be expanded to meet user needs._**

By default, the formatting process is performed according to the following rules.

- Indent is two half-width spaces
- No space is left before the colon after the key string
- Insert a single space after the colon
- After the start brace, increase the number of indents and line breaks
- After the start bracket, increase the number of indents and line breaks
- After the end brace, decrease the number of indents and line breaks
- After the end bracket, decrease the number of indents and line breaks

...Okay, it's a little confusing. I'll put up some examples of how to use it and how it's done as following, so take a look.

## How To Use

### 1. Add the dependencies on Json Fomratter

Add following dependencies. This process will soon be resolved by registering the jar and dependencies in the maven central repository.

- `dev-utils.jar`
- `formatter-commons.jar`

### 2. Add an import for Json Fomratter

```java
import org.thinkit.formatter.JsonFormatter;
```

### 3. Create a instance

```java
Formatter formatter = JsonFormatter.of();
```

You can also create an instance with a specific number of indents as following.

> _Note:_<br>
> If a negative indentation is passed at instantiation time, the initial value defined in the [content file](https://github.com/myConsciousness/json-formatter/blob/master/src/main/resources/content/formatter/json/JsonDefaultIndentItem.json) takes precedence.

```java
Formatter formatter = JsonFormatter.withIndent(indent);
```

### 4. Format!

```java
Formatter formatter = JsonFormatter.withIndent(indent);
String formattedJson = formatter.format(json);
```

## I/O

I have prepared the following unformatted json string for input/output reference. This is the template for my `content-framework`, but it's hard to see without any spaces or line breaks.

```
    {"metadata":{"author":"KatoShinya","since":"1.0","version":"1.0","creationDate":"2020/06/24","encoding":"UTF-8","description":"test"},"selectionNodes":[{"node":{"conditionId":"0","test1":"test","test2":"test"}},{"node":{"conditionId":"1","test1":"test","test2":"test"}},{"node":{"conditionId":"2","test1":"test","test2":"test"}}],"conditionNodes":[{"node":{"conditionId":"0","exclude":false,"conditions":[{"keyName":"test","operand":"=","value":"0"}]}},{"node":{"conditionId":"1","exclude":false,"conditions":[{"keyName":"test","operand":"=","value":"1"}]}},{"node":{"conditionId":"2","exclude":false,"conditions":[{"keyName":"test","operand":"=","value":"2"}]}}]}
```

Let's run `JSON Formatter` with the JSON string of above template as an argument.

```java
JsonFormatter.of().format(json);
```

The output is as follows.

> _Note:_<br>
> Whitespace in the JSON string before formatting is trimmed during the formatting process, so no pre-processing is required.

```json
{
  "metadata": {
    "author": "Kato Shinya",
    "since": "1.0",
    "version": "1.0",
    "creationDate": "2020/06/24",
    "encoding": "UTF-8",
    "description": "test"
  },

  "selectionNodes": [
    {
      "node": {
        "conditionId": "0",
        "test1": "test",
        "test2": "test"
      }
    },

    {
      "node": {
        "conditionId": "1",
        "test1": "test",
        "test2": "test"
      }
    },

    {
      "node": {
        "conditionId": "2",
        "test1": "test",
        "test2": "test"
      }
    }
  ],

  "conditionNodes": [
    {
      "node": {
        "conditionId": "0",
        "exclude": false,
        "conditions": [
          {
            "keyName": "test",
            "operand": "=",
            "value": "0"
          }
        ]
      }
    },

    {
      "node": {
        "conditionId": "1",
        "exclude": false,
        "conditions": [
          {
            "keyName": "test",
            "operand": "=",
            "value": "1"
          }
        ]
      }
    },

    {
      "node": {
        "conditionId": "2",
        "exclude": false,
        "conditions": [
          {
            "keyName": "test",
            "operand": "=",
            "value": "2"
          }
        ]
      }
    }
  ]
}
```

## License

```
Copyright 2020 Kato Shinya.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
```
