/*
 * Copyright 2016 Darwin Bautista
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uwetrottmann.tmdb2;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeParseException;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalQuery;

import java.io.IOException;

/**
 * Gson TypeAdapters for org.threeten.bp.** classes
 *
 * Sample usage:
 *
 * TypeAdapter<ZonedDateTime> zonedDateTimeAdapter = ThreeTenTypeAdapters.create(ZonedDateTime.class, DateTimeFormatter.ISO_ZONED_DATE_TIME);
 * TypeAdapter<LocalDate> localDateAdapter = ThreeTenTypeAdapters.create(LocalDate.class, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
 *
 * Gson gson = new GsonBuilder()
 *            .registerTypeAdapter(ZonedDateTime.class, zonedDateTimeAdapter)
 *            .registerTypeAdapter(LocalDate.class, localDateAdapter)
 *            .registerTypeAdapter(ZoneId.class, ThreeTenTypeAdapters.ZONE_ID)
 *            .create();
 *
 * Required ProGuard rule:
 * ## -- ThreeTenBP --
 * # Preserve the name of the FROM fields so that ThreeTenTypeAdapters.create() would still work
 * -keepclassmembers public class * implements org.threeten.bp.temporal.TemporalAccessor {
 *     public static final org.threeten.bp.temporal.TemporalQuery FROM;
 * }
 */
public final class ThreeTenTypeAdapters {

    // Prevent LocalDateiation
    private ThreeTenTypeAdapters() {}

    /**
     * TypeAdapter for {@link ZoneId}
     */
    public static final TypeAdapter<ZoneId> ZONE_ID = new TypeAdapter<ZoneId>() {
        @Override
        public void write(JsonWriter out, ZoneId value) throws IOException {
            out.value(value.getId());
        }

        @Override
        public ZoneId read(JsonReader in) throws IOException {
            return ZoneId.of(in.nextString());
        }
    }.nullSafe();

    /**
     * Create a new TypeAdapter for the given {@link TemporalAccessor} type
     *
     * @param type implementation of {@link TemporalAccessor}
     * @param formatter for serializing and deserializing the TemporalAccessor instance
     * @return TypeAdapter for the given type
     */
    @SuppressWarnings("unchecked")
    public static <T extends TemporalAccessor> TypeAdapter<T> create(Class<T> type, final DateTimeFormatter formatter) {
        final TemporalQuery<T> temporalType;
        try {
            temporalType = (TemporalQuery<T>) type.getField("FROM").get(null);
        } catch (NoSuchFieldException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                out.value(formatter.format(value));
            }

            @Override
            public T read(JsonReader in) throws IOException {
                try {
                    return formatter.parse(in.nextString(), temporalType);
                } catch (DateTimeParseException e) {
                    return null;
                }
            }
        }.nullSafe();
    }
}