/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.milo.opcua.stack.core.types.builtin.OptionSetUInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

@EqualsAndHashCode(
    callSuper = true
)
@ToString
public class AccessLevelType extends OptionSetUInteger<AccessLevelType.Field> {
    public AccessLevelType(UByte value) {
        super(value);
    }

    public boolean getCurrentRead() {
        return get(Field.CurrentRead);
    }

    public boolean getCurrentWrite() {
        return get(Field.CurrentWrite);
    }

    public boolean getHistoryRead() {
        return get(Field.HistoryRead);
    }

    public boolean getHistoryWrite() {
        return get(Field.HistoryWrite);
    }

    public boolean getSemanticChange() {
        return get(Field.SemanticChange);
    }

    public boolean getStatusWrite() {
        return get(Field.StatusWrite);
    }

    public boolean getTimestampWrite() {
        return get(Field.TimestampWrite);
    }

    @Override
    public UByte getValue() {
        return (UByte) value;
    }

    @Override
    public Set<AccessLevelType.Field> toSet() {
        return Arrays.stream(Field.values())
            .filter(this::get)
            .collect(Collectors.toSet());
    }

    public static AccessLevelType of(AccessLevelType.Field... fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new AccessLevelType(UByte.valueOf(bits));
    }

    public static AccessLevelType of(Collection<AccessLevelType.Field> fields) {
        long bits = 0L;

        for (Field f : fields) {
            bits |= (1L << f.bitIndex);
        }

        return new AccessLevelType(UByte.valueOf(bits));
    }

    public enum Field implements OptionSetUInteger.BitIndex {
        CurrentRead(0),

        CurrentWrite(1),

        HistoryRead(2),

        HistoryWrite(3),

        SemanticChange(4),

        StatusWrite(5),

        TimestampWrite(6);

        private final int bitIndex;

        Field(int bitIndex) {
            this.bitIndex = bitIndex;
        }

        @Override
        public int getBitIndex() {
            return bitIndex;
        }
    }
}
