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

package org.thinkit.formatter.json;

import org.thinkit.common.base.precondition.Preconditions;
import org.thinkit.common.exception.LogicException;
import org.thinkit.formatter.common.Indent;
import org.thinkit.formatter.common.Indentable;
import org.thinkit.formatter.common.Line;
import org.thinkit.formatter.common.Newline;
import org.thinkit.formatter.common.Tokenizable;
import org.thinkit.formatter.json.content.JsonDefaultIndentItemLoader;
import org.thinkit.formatter.json.content.entity.JsonDefaultIndentItem;
import org.thinkit.framework.content.ContentInvoker;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * {@link JsonTokenizer} クラスと連動してJSON文字列を生成するアペンダークラスです。
 * <p>
 * 生成した文字列は {@link #toString()} メソッドを使用することで取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE, staticName = "newInstance")
final class JsonAppender {

    /**
     * 整形済みのjson
     */
    private StringBuilder json;

    /**
     * JSONのトークナイザー
     */
    private Tokenizable jsonTokenizer;

    /**
     * インデント
     */
    private Indentable indent;

    /**
     * 改行
     */
    private Line newline;

    /**
     * {@link JsonAppender} クラスのインスタンスを生成する {@link Builder} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link Builder} クラスの新しいインスタンス
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * {@link JsonAppender} クラスのインスタンスを生成する処理を定義したビルダークラスです。
     *
     * @author Kato Shinya
     * @since 1.0.0
     */
    public static class Builder {

        /**
         * JSONトークナイザ
         */
        private Tokenizable jsonTokenizer;

        /**
         * インデント数
         */
        private int indent = -1;

        /**
         * デフォルトコンストラクタ
         */
        private Builder() {
        }

        /**
         * 連動する {@link JsonTokenizer} クラスを登録した {@link JsonAppender} クラスに登録します。
         *
         * @param JsonTokenizer {@link JsonAppender} クラスと連動するJSONのトークナイザー
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public Builder register(@NonNull Tokenizable jsonTokenizer) {
            this.jsonTokenizer = jsonTokenizer;
            return this;
        }

        /**
         * インデント数を設定します。
         *
         * @param indent インデント数
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public Builder withIndent(int indent) {
            this.indent = indent;
            return this;
        }

        /**
         * {@link #register(JsonTokenizer)} メソッドと {@link #withIndent(int)}
         * メソッドで設定された値を基に {@link JsonAppender} クラスの新しいインスタンスを生成し返却します。
         * <p>
         * {@link #register(JsonTokenizer)} メソッドが呼び出されていない場合、または
         * {@link #register(JsonTokenizer)} メソッドで設定された値が {@code null} の場合は
         * {@link NullPointerException} が実行時に必ず発生します。
         *
         * @return {@link JsonAppender} クラスの新しいインスタンス
         *
         * @throws LogicException {@link #register(JsonTokenizer)} メソッドが呼び出されていない場合、または
         *                        {@link #register(JsonTokenizer)} メソッドで設定された値が
         *                        {@code null} の場合
         */
        public JsonAppender build() {
            Preconditions.requireNonNull(this.jsonTokenizer);

            final JsonAppender appender = JsonAppender.newInstance();
            appender.json = new StringBuilder();
            appender.jsonTokenizer = this.jsonTokenizer;

            if (this.indent < 0) {
                final JsonDefaultIndentItem defaultIndentItem = ContentInvoker
                        .of(JsonDefaultIndentItemLoader.newInstance()).invoke();
                appender.indent = Indent.builder().withIndent(defaultIndentItem.getIndent())
                        .withIndentType(defaultIndentItem.getIndentType()).build();
            } else {
                appender.indent = Indent.builder().withIndent(this.indent).build();
            }

            appender.newline = Newline.of(appender.indent);

            return appender;
        }
    }

    /**
     * 登録した {@link JsonTokenizer} オブジェクトの現在位置にあるトークンを取得し文字列へ追加します。
     * <p>
     * この {@link JsonAppender#appendToken()}
     * メソッドは自分自身のインスタンスを返却するため、後続処理をメソッドチェーンの形式で行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public JsonAppender appendToken() {
        this.json.append(this.jsonTokenizer.getToken());
        return this;
    }

    /**
     * {@link Newline} クラスから改行コードを取得し文字列へ追加します。
     * <p>
     * この {@link JsonAppender#appendNewLine()}
     * メソッドは自分自身のインスタンスを返却するため、後続処理をメソッドチェーンの形式で行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public JsonAppender appendNewline() {
        this.json.append(this.newline.create());
        return this;
    }

    /**
     * 空白スペースを文字列へ追加します。
     * <p>
     * この {@link JsonAppender#appendNewLine()}
     * メソッドは自分自身のインスタンスを返却するため、後続処理をメソッドチェーンの形式で行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public JsonAppender appendSpace() {
        this.json.append(" ");
        return this;
    }

    /**
     * {@link Indent} クラスをインクリメントします。
     * <p>
     * この {@link JsonAppender#increment()}
     * メソッドは自分自身のインスタンスを返却するため、後続処理をメソッドチェーンの形式で行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public JsonAppender incrementIndent() {
        this.indent.increment();
        return this;
    }

    /**
     * {@link Indent} クラスをデクリメントします。
     * <p>
     * この {@link JsonAppender#increment()}
     * メソッドは自分自身のインスタンスを返却するため、後続処理をメソッドチェーンの形式で行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public JsonAppender decrementIndent() {
        this.indent.decrement();
        return this;
    }

    @Override
    public String toString() {
        return this.json.toString();
    }
}
