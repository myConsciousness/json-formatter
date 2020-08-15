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

package org.thinkit.formatter.content.json.rule;

import org.thinkit.formatter.content.json.JsonDefaultIndentItemLoader;
import org.thinkit.formatter.content.json.entity.JsonDefaultIndentItem;
import org.thinkit.framework.content.ContentInvoker;
import org.thinkit.framework.content.rule.Rule;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JSONのインデントに関する規定項目を取得する処理を定義したルールクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class JsonDefaultIndentItemCollector implements Rule<JsonDefaultIndentItem> {

    /**
     * デフォルトコンストラクタ
     */
    private JsonDefaultIndentItemCollector() {
    }

    /**
     * {@link JsonDefaultIndentItemCollector} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link JsonDefaultIndentItemCollector} クラスの新しいインスタンス
     */
    public static Rule<JsonDefaultIndentItem> of() {
        return new JsonDefaultIndentItemCollector();
    }

    @Override
    public JsonDefaultIndentItem execute() {
        return ContentInvoker.of(JsonDefaultIndentItemLoader.of()).invoke();
    }
}