/*
 * Copyright 2021 Kato Shinya.
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

import lombok.NonNull;

/**
 * 整形処理を行うフォーマッターを抽象化したインターフェースです。 {@link Formatter} インターフェースを実装する具象クラスは
 * {@link Formatter#format(String)} メソッドを必ず実装してください。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface Formatter {

    /**
     * 引数として渡された {@code unformatted} に格納された文字列を整形し返却します。
     *
     * @param rawJson 整形されていないJSON文字列
     * @return 整形された文字列
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public String format(@NonNull final String rawJson);
}
