/*
 * Copyright 2020 Kato Shinya.
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

package org.thinkit.formatter;

import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Bracket;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.formatter.common.Formatter;
import org.thinkit.formatter.common.Tokenizable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * JSON文字列を整形する処理を定義したフォーマッタクラスです。
 * <p>
 * {@link JsonFormatter} クラスのインスタンス生成時に渡されたJSON文字列が {@code ""} の場合、
 * {@link #format()} メソッドは必ず {@code ""} を返却します。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(staticName = "newInstance")
@AllArgsConstructor(staticName = "from")
public final class JsonFormatter implements Formatter {

    /**
     * 整形処理時のインデント数
     */
    private int indent = -1;

    @Override
    public String format(@NonNull final String json) {

        if (json.isEmpty()) {
            return "";
        }

        final Tokenizable tokenizer = JsonTokenizer.of(json);
        final JsonAppender appender = JsonAppender.builder().register(tokenizer).withIndent(this.indent).build();

        while (tokenizer.next()) {
            if (tokenizer.isWhitespace(tokenizer.getToken())) {
                continue;
            }

            final String lowercaseToken = tokenizer.getLowercaseToken();

            if (Brace.start().equals(lowercaseToken) || Bracket.start().equals(lowercaseToken)) {
                appender.appendToken().incrementIndent().appendNewline();
            } else if (Brace.end().equals(lowercaseToken) || Bracket.end().equals(lowercaseToken)) {
                appender.decrementIndent().appendNewline().appendToken();
            } else if (Delimiter.comma().equals(lowercaseToken)) {
                appender.appendToken().appendNewline();

                final String lastToken = tokenizer.getLastToken();

                if (Brace.end().equals(lastToken) || Bracket.end().equals(lastToken)) {
                    appender.appendNewline();
                }
            } else if (Delimiter.colon().equals(lowercaseToken)) {
                appender.appendToken().appendSpace();
            } else {
                appender.appendToken();
            }
        }

        return appender.toString();
    }
}
