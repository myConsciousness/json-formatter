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
     * 整形対象のJSON文字列
     */
    private String json;

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
     * {@link JsonFormatter} クラスのインスタンスを生成するためのビルダークラスを返却します。
     *
     * @return {@link JsonFormatter} クラスのインスタンスを生成するためのビルダークラス
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String format() {

        if (this.json.isEmpty()) {
            return "";
        }

        try {
            return new JSONObject(this.json).toString(this.indentFactor);
        } catch (JSONException e) {
            throw new FormatHandlingException(String.format(
                    "JSON string could not be converted to a JSON object. JSON string in case of a problem: %s",
                    this.json));
        }
    }

    /**
     * {@link JsonFormatter} クラスのインスタンスを生成するためのビルダークラスです。
     * <p>
     * 整形対象のJSON文字列の指定は {@link #of(String)} メソッドを呼び出して行ってください。 整形時のインデント数は
     * {@link #withIndentFactor(int)} メソッドを呼び出すことで指定できますが必須ではありません。
     * {@link #withIndentFactor(int)} メソッドが呼び出されなかった場合は初期値として {@code 0} が使用されます。
     * <p>
     * パラメータの設定メソッドを呼び出した後は {@link #build()} メソッドを呼び出すことで {@link JsonFormatter}
     * クラスの新しいインスタンスを取得することができます。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     *
     * @see #of(String)
     * @see #withIndentFactor(int)
     * @see #build()
     */
    @ToString
    @EqualsAndHashCode
    public static class Builder {

        /**
         * 整形対象のJSON文字列
         */
        private String json;

        /**
         * 整形処理時のインデント数
         */
        private int indentFactor;

        /**
         * デフォルトコンストラクタ
         */
        private Builder() {
        }

        /**
         * 整形対象のJSON文字列を指定します。
         * <p>
         * この {@link #of(String)} メソッドは自分自身のインスタンスを返却するため後続処理をメソッドチェーンで行うことができます。
         * <p>
         * {@link #withIndentFactor(int)}
         * メソッドを呼び出すことで整形処理時のインデント数を指定することができますが、インデント数の指定が必要ない場合は {@link #build()}
         * メソッドを呼び出すことで {@link JsonFormatter} クラスの新しいインスタンスを取得することができます。
         *
         * @param json 整形対象のJSON文字列
         * @return 自分自身のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public Builder of(@NonNull String json) {
            this.json = json;
            return this;
        }

        /**
         * 整形処理時のインデント数を指定します。
         * <p>
         * この {@link #withIndentFactor(int)}
         * メソッドは自分自身のインスタンスを返却するため後続処理をメソッドチェーンで行うことができます。
         * <p>
         * {@link JsonFormatter} クラスのインスタンスを生成する際にこの {@link #withIndentFactor(int)}
         * メソッドの呼び出しは必須ではありません。インデント数を指定する際には {@code 0} 以上の整数を指定してください。インデント数の指定後に
         * {@link JsonFormatter} クラスのインスタンスを取得する場合は {@link #build()} メソッドを呼び出してください。
         *
         * @param indentFactor 整形時のインデント数
         * @return
         */
        public Builder withIndentFactor(int indentFactor) {
            this.indentFactor = indentFactor;
            return this;
        }

        /**
         * {@link #of(String)} メソッドと {@link #withIndentFactor(int)} メソッドで指定された値を基に
         * {@link JsonFormatter} クラスの新しいインスタンスを生成し返却します。
         *
         * @return {@link JsonFormatter} クラスの新しいインスタンス
         */
        public Formatter build() {
            JsonFormatter formatter = new JsonFormatter();
            formatter.json = this.json;
            formatter.indentFactor = this.indentFactor;

            return formatter;
        }
    }
}