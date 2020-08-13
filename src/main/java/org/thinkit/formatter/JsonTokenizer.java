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

import java.util.StringTokenizer;

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
final class JsonTokenizer {

    /**
     * 空白
     */
    private static final String WHITESPACES = " \n\r\f\t";

    /**
     * 区切り文字
     */
    private static final String TOKEN_DELIMITER = "{[]}:\"," + WHITESPACES;

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
     * JSONのトークナイザー
     */
    private StringTokenizer jsonTokenizer;

    /**
     * デフォルトコンストラクタ
     */
    private JsonTokenizer() {
    }

    /**
     * コンストラクタ
     *
     * @param json 処理対象のJSON文字列
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private JsonTokenizer(@NonNull String json) {
        this.jsonTokenizer = new StringTokenizer(json.trim(), TOKEN_DELIMITER, true);
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
    public static JsonTokenizer of(@NonNull String json) {
        return new JsonTokenizer(json);
    }

}