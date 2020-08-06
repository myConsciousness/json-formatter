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

/**
 * {@link JsonFormatter} クラスをコマンドラインから実行する際のエントリーポイントです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class EntryJsonFormatter {

    /**
     * 与えられたコマンドライン引数から {@link JsonFormatter} クラスの処理を開始します。
     * <p>
     * コマンドライン引数には以下の値を指定してください。
     *
     * <ol>
     * <li>整形対象のJSON文字列（必須）</li>
     * <li>整形時のインデント数（任意）</li>
     * </ol>
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException(
                    "No argument was passed to start the JSON formatter. The JSON string to be formatted is a required.");
        }

        final String json = args[0];
        final int indentFactor = args.length > 1 ? Integer.parseInt(args[1]) : 0;

        System.out.println(JsonFormatter.ofIndentFactor(indentFactor).format(json));
    }
}