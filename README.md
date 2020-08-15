# JSON Formatter

This is a formatter for formatting JSON strings.

By default, the formatting process is performed according to the following rules.

- Indent is two half-width spaces
- No space is left before the colon after the key string
- Insert a single space after the colon
- After the start brace, increase the number of indents and line breaks
- After the start bracket, increase the number of indents and line breaks
- After the end brace, decrease the number of indents and line breaks
- After the end bracket, decrease the number of indents and line breaks

# License

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
