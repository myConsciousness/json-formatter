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

package org.thinkit.formatter.json.content.entity;

import java.io.Serializable;

import org.thinkit.formatter.common.catalog.IndentType;
import org.thinkit.framework.content.entity.ContentEntity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * コンテンツ「JSON既定インデント項目」の値を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
JsonDefaultIndentccess = AccessLevel.PRIVATE)
JsonDefaultIndentcess = AccessLevel.PRIVATE, force = true)
public final class JsonDefaultIndent implements ContentEntity, Serializable {

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
}
