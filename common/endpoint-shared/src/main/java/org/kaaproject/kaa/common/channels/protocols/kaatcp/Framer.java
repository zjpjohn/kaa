/*
 * Copyright 2014 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaaproject.kaa.common.channels.protocols.kaatcp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kaaproject.kaa.common.channels.protocols.kaatcp.messages.ConnAck;
import org.kaaproject.kaa.common.channels.protocols.kaatcp.messages.Connect;
import org.kaaproject.kaa.common.channels.protocols.kaatcp.messages.Disconnect;
import org.kaaproject.kaa.common.channels.protocols.kaatcp.messages.KaaSync;
import org.kaaproject.kaa.common.channels.protocols.kaatcp.messages.MessageType;
import org.kaaproject.kaa.common.channels.protocols.kaatcp.messages.MqttFrame;
import org.kaaproject.kaa.common.channels.protocols.kaatcp.messages.PingRequest;
import org.kaaproject.kaa.common.channels.protocols.kaatcp.messages.PingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kaatcp Framer Class.
 *
 * @author Andrey Panasenko
 *
 */
public class Framer {

    public static final Logger LOG = LoggerFactory //NOSONAR
            .getLogger(Framer.class);

    private final List<MqttFramelistener> listeners;

    private MqttFrame currentFrame;
    /**
     *
     */
    public Framer() {
        listeners = new ArrayList<>();

    }

    public void registerFrameListener(MqttFramelistener listener) {
        listeners.add(listener);
    }

    public int pushBytes(byte[] bytes) throws KaaTcpProtocolException {
        if(LOG.isTraceEnabled()){
            if(bytes != null){
                LOG.trace("Received bytes: {}", Arrays.toString(bytes));
            }
        }
        int used = 0;

        while (bytes.length > used) {
            if (currentFrame == null) {
                if ((bytes.length - used) >= 1) { // 1 bytes minimum header length
                    int intType = bytes[used] & 0xFF;
                    currentFrame = getFrameByType((byte) (intType >> 4));
                    ++used;
                } else {
                    break;
                }
            }
            used += currentFrame.push(bytes, used);
            if(currentFrame.decodeComplete()) {
                callListeners(currentFrame);
                currentFrame = null;
            }
        }
        return used;
    }

    /**
     * Notify all listeners on new Frame
     * @param frame
     */
    private void callListeners(MqttFrame frame) {
        for(MqttFramelistener listener : listeners) {
            listener.onMqttFrame(frame);
        }
    }

    /**
     * Creates specific Kaatcp message by MessageType
     * @param type - MessageType of mqttFrame
     * @return mqttFrame
     * @throws KaaTcpProtocolException if specified type is unsupported
     */
    private MqttFrame getFrameByType(byte type) throws KaaTcpProtocolException {
        MqttFrame frame = null;
        if (type == MessageType.CONNACK.getType()) {
            frame = new ConnAck();
        } else if (type == MessageType.CONNECT.getType()) {
            frame = new Connect();
        } else if (type == MessageType.DISCONNECT.getType()) {
            frame = new Disconnect();
        } else if (type == MessageType.KAASYNC.getType()) {
            frame = new KaaSync();
        } else if (type == MessageType.PINGREQ.getType()) {
            frame = new PingRequest();
        } else if (type == MessageType.PINGRESP.getType()) {
            frame = new PingResponse();
        } else {
            throw new KaaTcpProtocolException("Got incorrect messageType format " + type );
        }

        return frame;
    }

    public void flush() {
        currentFrame = null;
    }
}
