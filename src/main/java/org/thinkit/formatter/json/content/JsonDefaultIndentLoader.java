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

package org.thinkit.formatter.json.content;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.thinkit.api.catalog.Catalog;
import org.thinkit.formatter.common.catalog.IndentType;
import org.thinkit.formatter.json.content.entity.JsonDefaultIndent;
import org.thinkit.framework.content.Attribute;
import org.thinkit.framework.content.Condition;
import org.thinkit.framework.content.Content;
import org.thinkit.framework.content.annotation.ContentMapping;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「JSON既定インデント項目」の情報をロードするコンテンツクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
JsonDefaultIndentLoaderme = "newInstance")
@ContentMapping(content = "org/thinkit/formatter/json/JsonDefaultIndentItem")
public final class JsonDefaultIndentLoader implements Content<JsonDefaultIndent> {

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * インデントタイプ
         */
        INDENT_TYPE(Key.indentType),

        /**
         * インデント数
         */
        INDENT(Key.indent);

        /**
         * キー
         */
        private final Key key;

        /**
         * キー要素
         */
        private enum Key {
            indentType, indent;
        }

        @Override
        public String getString() {
            return this.key.name();
        }
    }

    @Override
    public JsonDefaultIndent execute() {

        final Map<String, String> content = this.loadContent(this).get(0);

        return JsonDefaultIndent.builder()
                .indentType(Catalog.getEnum(IndentType.class,
                        Integer.parseInt(content.get(ContentAttribute.INDENT_TYPE.getString()))))
                .indent(Integer.parseInt(content.get(ContentAttribute.INDENT.getString()))).build();
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.INDENT_TYPE, ContentAttribute.INDENT);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {
        return List.of();
    }
}
