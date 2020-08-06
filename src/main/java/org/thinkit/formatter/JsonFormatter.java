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

import org.json.JSONException;
import org.json.JSONObject;
import org.thinkit.formatter.common.Formatter;
import org.thinkit.formatter.common.exception.FormatHandlingException;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * JSON文字列を整形する処理を定義したフォーマッタクラスです。
 * <p>
 * {JsonFormatter} クラスのインスタンスを取得する場合は静的メソッドの {@link #builder()}
 * メソッドを呼び出して各パラメータの設定を行い {@link Builder#build()} メソッドを呼び出してください。
 * {@link Builder#build()} メソッドを呼び出して取得した {@link JsonFormatter} クラスのインスタンスから
 * {@link #format()} メソッドを呼び出すことで整形後のJSON文字列を取得することができます。 {@link JsonFormatter}
 * クラスのインスタンス生成時に渡されたJSON文字列が {@code ""} の場合、 {@link #format()} メソッドは必ず
 * {@code ""} を返却します。
 * <p>
 * {@link #format()} メソッドの実行時に指定されたJSON文字列がJSONオブジェクトへ変換できなかった場合は
 * {@link FormatHandlingException} が必ず発生するため、例外が発生する場合は呼び出し元の実装を確認してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class JsonFormatter implements Formatter {

    /**
     * 整形処理時のインデント数
     */
    private int indentFactor;

    /**
     * デフォルトコンストラクタ
     */
    private JsonFormatter() {
    }

    /**
     * コンストラクタ
     *
     * @param indentFactor 整形時のインデント数
     */
    private JsonFormatter(int indentFactor) {
        this.indentFactor = indentFactor;
    }

    /**
     * {@link JsonFormatter} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link JsonFormatter} クラスの新しいインスタンス
     */
    public static Formatter of() {
        return new JsonFormatter();
    }

    /**
     * 整形時のインデント数 {@code indentFactor} の値を基に {@link JsonFormatter}
     * クラスの新しいインスタンスを生成し返却します。
     * <p>
     * 引数の {@code indentFactor} には {@code 0} 以上の整数を指定してください。
     *
     * @param indentFactor 整形時のインデント数
     * @return {@link JsonFormatter} クラスの新しいインスタンス
     */
    public static Formatter ofIndentFactor(int indentFactor) {
        return new JsonFormatter(indentFactor);
    }

    @Override
    public String format(@NonNull final String json) {

        if (json.isEmpty()) {
            return "";
        }

        try {
            return new JSONObject(json).toString(this.indentFactor);
        } catch (JSONException e) {
            throw new FormatHandlingException(String.format(
                    "JSON string could not be converted to a JSON object. JSON string in case of a problem: %s", json));
        }
    }
}