![Build](https://img.shields.io/badge/Build-Automated-2980b9.svg?style=for-the-badge)
![Latest Version](https://img.shields.io/badge/Latest_Version-v1.0.2-27ae60.svg?style=for-the-badge)
![License](https://img.shields.io/badge/License-Apache_2.0-e74c3c.svg?style=for-the-badge)</br>
![Java CI with Gradle](https://github.com/myConsciousness/json-formatter/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)

# JSON Formatter

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [What is it?](#what-is-it)
- [How To Use](#how-to-use)
  - [1. Add the dependencies](#1-add-the-dependencies)
  - [2. Add an import for **_JSON Fomratter_**](#2-add-an-import-for-_json-fomratter_)
  - [3. Create a instance](#3-create-a-instance)
  - [4. Format](#4-format)
- [Demonstrate I/O](#demonstrate-io)
- [License](#license)
- [More Information](#more-information)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## What is it?

**_This is a formatter for JSON strings._**

`JSON Formatter` is specialized in formatting JSON strings that do not use regular expressions and has no functions other than formatting. **_Therefore it is extremely lightweight besides can be expanded to meet user needs._**

## How To Use

### 1. Add the dependencies

> **_Note:_**</br>
> Replace version you want to use. Check the latest [Packages](https://github.com/myConsciousness/json-formatter/packages).</br>
> Please contact me for a token to download the package.

**_Maven_**

```xml
<dependency>
  <groupId>org.thinkit.formatter</groupId>
  <artifactId>json-formatter</artifactId>
  <version>v1.0.2</version>
</dependency>

<servers>
  <server>
    <id>github</id>
    <username>myConsciousness</username>
    <password>xxxxxxxxxxxxxxxxxx</password>
  </server>
</servers>
```

**_Gradle_**

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/myConsciousness/json-formatter")
        credentials {
          username = "myConsciousness"
          password = "xxxxxxxxxxxxxxxxxx"
        }
    }
}

dependencies {
    implementation 'org.thinkit.formatter:json-formatter:v1.0.2'
}
```

### 2. Add an import for [**_JSON Fomratter_**](https://github.com/myConsciousness/json-formatter/blob/master/src/main/java/org/thinkit/formatter/json/JsonFormatter.java)

```java
import org.thinkit.formatter.json.JsonFormatter;
```

### 3. Create a instance

```java
Formatter formatter = JsonFormatter.newInstance();
```

You can also create an instance with a specific number of indents as following.

> **_Note:_**</br>
> If a negative indentation is passed at instantiation time, the initial value defined in the [content file](https://github.com/myConsciousness/json-formatter/blob/master/src/main/resources/content/org/thinkit/formatter/json/JsonDefaultIndent.json) takes precedence.

```java
Formatter formatter = JsonFormatter.from(indent);
```

### 4. Format

```java
Formatter formatter = JsonFormatter.from(indent);
String formattedJson = formatter.format(json);
```

## Demonstrate I/O

I have prepared the following unformatted json string for input/output reference. This is the json template for `content-framework`, but it's hard to see without any spaces or line breaks.

```txt
    {"metadata":{"author":"Kato Shinya","since":"1.0","version":"1.0","creationDate":"2020/06/24","encoding":"UTF-8","description":"test"},"selectionNodes":[{"node":{"conditionId":"0","test1":"test","test2":"test"}},{"node":{"conditionId":"1","test1":"test","test2":"test"}},{"node":{"conditionId":"2","test1":"test","test2":"test"}}],"conditionNodes":[{"node":{"conditionId":"0","exclude":false,"conditions":[{"keyName":"test","operand":"=","value":"0"}]}},{"node":{"conditionId":"1","exclude":false,"conditions":[{"keyName":"test","operand":"=","value":"1"}]}},{"node":{"conditionId":"2","exclude":false,"conditions":[{"keyName":"test","operand":"=","value":"2"}]}}]}
```

Let's run `JSON Formatter` with the JSON string of above template as an argument.

```java
JsonFormatter.newInstance().format(json);
```

The output is as follows.

> **_Note:_**</br>
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

```license
Copyright 2020 Kato Shinya.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
```

## More Information

`JSON Formatter` was designed and implemented by Kato Shinya, who works as a freelance developer.

Regardless of the means or content of communication, I would love to hear from you if you have any questions or concerns. I do not check my email box very often so a response may be delayed, anyway thank you for your interest!

- [Creator Profile](https://github.com/myConsciousness)
- [Creator Website](https://myconsciousness.github.io/)
- [License](https://github.com/myConsciousness/json-formatter/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/json-formatter/releases)
- [Package](https://github.com/myConsciousness/json-formatter/packages)
- [File a Bug](https://github.com/myConsciousness/json-formatter/issues)
- [Reference](https://myconsciousness.github.io/json-formatter/)
