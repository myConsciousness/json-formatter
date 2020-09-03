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

import java.util.Locale;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.thinkit.api.catalog.BiCatalog;
import org.thinkit.common.catalog.Quotation;
import org.thinkit.formatter.common.Tokenizable;
import org.thinkit.formatter.common.catalog.Whitespace;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * JSON文字列のトークン解析を行う処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
final class JsonTokenizer implements Tokenizable {

    /**
     * 区切り文字
     */
    private static final String TOKEN_DELIMITER = "{[]}:,\\\"" + getWhitespaces();

    /**
     * トークン
     */
    @Getter
    private String token;

    /**
     * 小文字のトークン
     */
    @Getter
    private String lowercaseToken;

    /**
     * 1つ前のトークン
     */
    @Getter
    private String lastToken;

    /**
     * JSONのトークナイザー
     */
    private StringTokenizer jsonTokenizer;

    /**
     * デフォルトコンストラクタ
     */
    private JsonTokenizer() {
    }

    /**
     * 空白の文字列集合を返却します。
     *
     * @return 空白の文字列集合
     */
    private static String getWhitespaces() {
        return BiCatalog.stream(Whitespace.class).collect(Collectors.toList()).toString();
    }

    /**
     * コンストラクタ
     *
     * @param json 処理対象のJSON文字列
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private JsonTokenizer(@NonNull String json) {
        this.jsonTokenizer = new StringTokenizer(json, TOKEN_DELIMITER, true);
        this.token = "";
        this.lowercaseToken = "";
    }

    /**
     * 引数として渡された {@code json} 文字列に基づいて {@link JsonTokenizer} クラスの新しいインスタンスを生成し返却します。
     *
     * @param json 処理対象のJSON文字列
     * @return {@link JsonTokenizer} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Tokenizable of(@NonNull String json) {
        return new JsonTokenizer(json);
    }

    @Override
    public boolean next() {

        if (!jsonTokenizer.hasMoreTokens()) {
            return false;
        }

        this.token = this.jsonTokenizer.nextToken();

        if (Quotation.doubleQuote().equals(this.token)) {
            this.afterDoubleQuotation();
        } else {
            if (!this.isWhitespace(this.lowercaseToken)) {
                this.lastToken = this.lowercaseToken;
            }

            this.lowercaseToken = this.token.toLowerCase(Locale.ROOT);
        }

        return true;
    }

    /**
     * ダブルクォーテーション以降のトークンを参照し文字列を生成します。生成された文字列は変数 {@code token} に格納されるため、
     * {@link #getToken()} メソッドを呼び出すことで取得することができます。
     */
    private void afterDoubleQuotation() {

        final StringBuilder sb = new StringBuilder(this.token);

        while (jsonTokenizer.hasMoreTokens()) {
            final String tokenAfterQuote = jsonTokenizer.nextToken();
            sb.append(tokenAfterQuote);

            if (!this.isWhitespace(this.lowercaseToken)) {
                this.lastToken = this.lowercaseToken;
            }

            this.lowercaseToken = tokenAfterQuote.toLowerCase(Locale.ROOT);

            if (Quotation.doubleQuote().equals(tokenAfterQuote) && !"\\".equals(this.lastToken)) {
                break;
            }
        }

        this.token = sb.toString();
    }
}