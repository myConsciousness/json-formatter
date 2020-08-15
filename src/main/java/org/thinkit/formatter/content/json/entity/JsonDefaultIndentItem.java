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

package org.thinkit.formatter.content.json.entity;

import java.io.Serializable;

import org.thinkit.common.Precondition;
import org.thinkit.common.exception.IllegalNumberFoundException;
import org.thinkit.formatter.common.catalog.IndentType;
import org.thinkit.framework.content.entity.ContentEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツ「JSON既定インデント項目」の値を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class JsonDefaultIndentItem implements ContentEntity, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 5408774375001484279L;

    /**
     * インデントタイプ
     */
    @Getter
    private IndentType indentType;

    /**
     * インデント数
     */
    @Getter
    private int indent;

    /**
     * デフォルトコンストラクタ
     */
    private JsonDefaultIndentItem() {
    }

    /**
     * コンストラクタ
     *
     * @param indentType インデントタイプ
     * @param indent     インデント数
     *
     * @exception NullPointerException        引数として {@code null} が渡された場合
     * @exception IllegalNumberFoundException 引数として渡された {@code indent} が負数の場合
     */
    private JsonDefaultIndentItem(@NonNull IndentType indentType, int indent) {
        Precondition.requirePositive(indent);
        this.indentType = indentType;
        this.indent = indent;
    }

    /**
     * コピーコンストラクタ
     *
     * @param jsonDefaultIndentItem DDL既定インデント項目
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private JsonDefaultIndentItem(@NonNull JsonDefaultIndentItem jsonDefaultIndentItem) {
        this.indentType = jsonDefaultIndentItem.getIndentType();
        this.indent = jsonDefaultIndentItem.getIndent();
    }

    /**
     * 引数として渡された値を基に {@link JsonDefaultIndentItem} の新しいインスタンスを生成し返却します。
     *
     * @param indentType インデントタイプ
     * @param indent     インデント
     * @return {@link JsonDefaultIndentItem} の新しいインスタンス
     *
     * @exception NullPointerException        引数として {@code null} が渡された場合
     * @exception IllegalNumberFoundException 引数として渡された {@code indent} が負数の場合
     */
    public static JsonDefaultIndentItem of(@NonNull IndentType indentType, int indent) {
        return new JsonDefaultIndentItem(indentType, indent);
    }

    /**
     * 引数として渡された {@code JsonDefaultIndentItem} オブジェクトの値を基に
     * {@link JsonDefaultIndentItem} の新しいインスタンスを生成し返却します。
     *
     * @param jsonDefaultIndentItem DDL既定インデント項目
     * @return {@link JsonDefaultIndentItem} の新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static JsonDefaultIndentItem of(@NonNull JsonDefaultIndentItem jsonDefaultIndentItem) {
        return new JsonDefaultIndentItem(jsonDefaultIndentItem);
    }
}