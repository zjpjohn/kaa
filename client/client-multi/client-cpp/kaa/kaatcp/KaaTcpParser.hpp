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

#ifndef KAATCPPARSER_HPP_
#define KAATCPPARSER_HPP_

#include <boost/cstdint.hpp>
#include <boost/noncopyable.hpp>
#include <boost/shared_array.hpp>
#include "kaa/kaatcp/KaaTcpCommon.hpp"
#include <list>

namespace kaa {

typedef std::pair<KaaTcpMessageType, std::pair<boost::shared_array<char>, boost::uint32_t>> MessageRecord;
typedef std::list<MessageRecord> MessageRecordList;

enum class KaaTcpParserState : boost::uint8_t
{
    NONE = 0x00,
    PROCESSING_LENGTH = 0x01,
    PROCESSING_PAYLOAD = 0x02,
};

class KaaTcpParser : boost::noncopyable
{
public:
    KaaTcpParser() :
            state_(KaaTcpParserState::NONE), messageLength_(0), processedPayloadLength_(0)
          , lenghtMultiplier_(1), messageType_(KaaTcpMessageType::MESSAGE_UNKNOWN) { }
    ~KaaTcpParser() { }

    void parseBuffer(const char *buffer, boost::uint32_t size);

    boost::shared_array<char> getCurrentPayload() const { return messagePayload_; }
    boost::uint32_t getCurrentPayloadLength() const { return messageLength_; }
    KaaTcpMessageType getCurrentMessageType() const { return messageType_; }

    MessageRecordList releaseMessages();

    void resetParser();

private:
    void processByte(char byte);
    void retrieveMessageType(char byte);
    void onMessageDone();

private:

    KaaTcpParserState state_;
    boost::uint32_t messageLength_;
    boost::uint32_t processedPayloadLength_;
    boost::uint32_t lenghtMultiplier_;
    KaaTcpMessageType messageType_;
    boost::shared_array<char> messagePayload_;
    MessageRecordList messages_;
};

}


#endif /* KAATCPPARSER_HPP_ */
