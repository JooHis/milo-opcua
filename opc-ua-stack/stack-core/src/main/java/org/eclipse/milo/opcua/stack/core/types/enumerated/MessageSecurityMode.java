/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.jetbrains.annotations.Nullable;

public enum MessageSecurityMode implements UaEnumeration {
    Invalid(0),

    None(1),

    Sign(2),

    SignAndEncrypt(3);

    private final int value;

    MessageSecurityMode(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static MessageSecurityMode from(int value) {
        switch (value) {
            case 0:
                return Invalid;
            case 1:
                return None;
            case 2:
                return Sign;
            case 3:
                return SignAndEncrypt;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=302");
    }

    public static class Codec extends GenericDataTypeCodec<MessageSecurityMode> {
        @Override
        public Class<MessageSecurityMode> getType() {
            return MessageSecurityMode.class;
        }

        @Override
        public MessageSecurityMode decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, MessageSecurityMode.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, MessageSecurityMode value) {
            encoder.writeEnum(null, value);
        }
    }
}
