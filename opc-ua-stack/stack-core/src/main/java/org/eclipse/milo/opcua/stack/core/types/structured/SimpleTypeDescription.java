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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SimpleTypeDescription extends DataTypeDescription implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15005");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15421");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15529");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15700");

    private final NodeId baseDataType;

    private final UByte builtInType;

    public SimpleTypeDescription(NodeId dataTypeId, QualifiedName name, NodeId baseDataType,
                                 UByte builtInType) {
        super(dataTypeId, name);
        this.baseDataType = baseDataType;
        this.builtInType = builtInType;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public NodeId getBaseDataType() {
        return baseDataType;
    }

    public UByte getBuiltInType() {
        return builtInType;
    }

    public static final class Codec extends GenericDataTypeCodec<SimpleTypeDescription> {
        @Override
        public Class<SimpleTypeDescription> getType() {
            return SimpleTypeDescription.class;
        }

        @Override
        public SimpleTypeDescription decode(SerializationContext context, UaDecoder decoder) {
            NodeId dataTypeId = decoder.readNodeId("DataTypeId");
            QualifiedName name = decoder.readQualifiedName("Name");
            NodeId baseDataType = decoder.readNodeId("BaseDataType");
            UByte builtInType = decoder.readByte("BuiltInType");
            return new SimpleTypeDescription(dataTypeId, name, baseDataType, builtInType);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SimpleTypeDescription value) {
            encoder.writeNodeId("DataTypeId", value.getDataTypeId());
            encoder.writeQualifiedName("Name", value.getName());
            encoder.writeNodeId("BaseDataType", value.getBaseDataType());
            encoder.writeByte("BuiltInType", value.getBuiltInType());
        }
    }
}
